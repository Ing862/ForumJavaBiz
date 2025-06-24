package org.example.forumjavabiz.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.forumjavabiz.dao.PostDAO;
import org.example.forumjavabiz.entity.Post;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PostController", urlPatterns = {
        "/post/list",
        "/post/edit/*",
        "book/remove/*",
        "/post/view/*"
})
public class PostController extends HttpServlet {
    @EJB
    private PostDAO postDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String pathInfo = request.getPathInfo();

        switch (path) {
            case "/post/list": // Wyświetlenie listy postów
                handlePostList(request, response);
                break;
            case "/post/edit":
                break;
            case "/book/remove":
                break;
            case "/post/view": // Wyświetlenie konkretnego posta
                if (pathInfo != null) {
                    handlePostView(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Brak ID posta.");
                }
                break;
        }
    }

    private void handlePostList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> posts = postDAO.findAll();
        request.setAttribute("postList", posts);
        request.getRequestDispatcher("/WEB-INF/views/post/post_list.jsp").forward(request, response);
    }

    private void handlePostView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // np. "/5"

        if (pathInfo == null || pathInfo.length() <= 1) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nieprawidłowy identyfikator posta.");
            return;
        }

        try {
            Long postId = Long.parseLong(pathInfo.substring(1));
            Post post = postDAO.findById(postId);

            if (post == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post nie został znaleziony.");
                return;
            }

            request.setAttribute("post", post);
            request.getRequestDispatcher("/WEB-INF/views/post/post_view.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nieprawidłowe ID posta.");
        }
    }
}
