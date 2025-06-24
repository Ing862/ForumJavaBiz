package org.example.forumjavabiz.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.forumjavabiz.dao.PostDAO;
import org.example.forumjavabiz.entity.Post;
import org.example.forumjavabiz.entity.User;

import java.awt.print.Book;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "PostController", urlPatterns = {
        "/post/add/*",
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
            case "/post/edit": // Tworzenie lub edycja posta
                handleGetEditGet(request, response);
                break;
            case "/post/remove": // Usunięcie posta
                handlePostRemove(request, response);
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

    private void handleGetEditGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String pathInfo = request.getPathInfo();

        Long id = parseId(pathInfo);
        Post post = postDAO.findById(id);

        if(post != null) {
            request.setAttribute("title", post.getTitle());
            request.setAttribute("content", post.getContent());
            request.setAttribute("author", post.getAuthor());
        }

        request.getRequestDispatcher("/WEB-INF/views/post/post_form.jsp").forward(request, response);
    }

    private void handlePostRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String pathInfo = request.getPathInfo();

        Long id = parseId(pathInfo);
        Post post = postDAO.findById(id);

        // dodać potwierdzenie usunięcia?

        if(post != null) {
            postDAO.delete(post);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/post/add")) {
            handlePostEditPost(request, response);
        }
    }

    private void handlePostEditPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String pathInfo = request.getPathInfo();
        Long id = parseId(pathInfo);

        Map<String, String> fieldToError = new HashMap<>(); // Hashmap do komunikató o błędach
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        Post post = parsePost(request.getParameterMap(), fieldToError, loggedUser);

        if (!fieldToError.isEmpty()) {
            request.setAttribute("errors", fieldToError);
            request.setAttribute("title", request.getParameter("title"));
            request.setAttribute("author", request.getParameter("author"));
            request.setAttribute("content", request.getParameter("content"));
            request.getRequestDispatcher("/WEB-INF/views/post/post_form.jsp").forward(request, response);
            return;
        }

        post.setId(id);
        postDAO.save(post); // na wykładzie jest save or update
        response.sendRedirect(request.getContextPath() + "/post/list");
    }




    private Post parsePost(Map<String, String[]> paramToValue, Map<String, String> fieldToError, User author) {
        String  title = paramToValue.get("title")[0];
        String content = paramToValue.get("content")[0];

        if (title == null) {
            fieldToError.put("title", "Title is required.");
        }
        if (content == null) {
            fieldToError.put("content", "Content is required.");
        }
        if (author == null) {
            fieldToError.put("author", "User must be logged in.");
        }

        //
        return fieldToError.isEmpty() ? new Post(title, content, author) : null;

    }

    private Long parseId(String pathInfo) {
        if (pathInfo != null && pathInfo.length() > 1) {
            try {
                return Long.parseLong(pathInfo.substring(1));
            } catch (NumberFormatException e) {
//                log.warning("Invalid ID format: " + pathInfo);
            }
        }
        return null;
    }

}
