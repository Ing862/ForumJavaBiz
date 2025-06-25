package org.example.forumjavabiz.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.forumjavabiz.dao.PostDAO;
import org.example.forumjavabiz.entity.Post;
import org.example.forumjavabiz.entity.Topic;
import org.example.forumjavabiz.entity.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "PostController", urlPatterns = {
        "/post/list",
        "/post/edit/*",
        "/post/remove/*",
        "/post/view/*"
})
public class PostController extends HttpServlet {
    @EJB
    private PostDAO postDAO;

    // TODO: Ujednolicić język w tym pliku -> polski na angielski
    // TODO: Dodać ograniczenia do dodawania postów dla zalogowanych użytkowników
    // TODO: Dodać ograniczenia dla edycji i usuwania postów
    // TODO: Ustalić co się dzieje jeżeli post jest usunięty
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String pathInfo = request.getPathInfo();

        switch (path) {
            case "/post/list": // Wyświetlenie listy postów
                handlePostList(request, response);
                break;
            case "/post/edit": // Tworzenie lub edycja posta (formularz)
                handleGetEditGet(request, response);
                break;
            case "/post/remove": // Usunięcie posta
                handlePostRemove(request, response);
                break;
            case "/post/view": // Wyświetlenie konkretnego posta
                if (pathInfo != null && pathInfo.length() > 1) {
                    handlePostView(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Brak ID posta.");
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void handlePostList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> posts = postDAO.findAll();
        request.setAttribute("postList", posts);
        request.getRequestDispatcher("/WEB-INF/views/post/post_list.jsp").forward(request, response);
    }

    private void handlePostView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // np. "/5"

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
        String pathInfo = request.getPathInfo(); // np. "/5" lub null (nowy post)
        Post post = null;

        if (pathInfo != null && pathInfo.length() > 1) {
            Long id = parseId(pathInfo);
            if (id != null) {
                post = postDAO.findById(id);
                if (post == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post nie został znaleziony.");
                    return;
                }
            }
        }

        if (post != null) {
            request.setAttribute("id", post.getId());
            request.setAttribute("title", post.getTitle());
            request.setAttribute("content", post.getContent());
            request.setAttribute("author", post.getAuthor());
        }

        request.getRequestDispatcher("/WEB-INF/views/post/post_form.jsp").forward(request, response);
    }

    private void handlePostRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        Long id = parseId(pathInfo);
        if (id == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nieprawidłowe ID posta do usunięcia.");
            return;
        }

        Post post = postDAO.findById(id);
        if (post == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post nie został znaleziony.");
            return;
        }

        postDAO.delete(post);
        response.sendRedirect(request.getContextPath() + "/post/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if ("/post/edit".equals(path)) {
            handlePostEditPost(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void handlePostEditPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        Long id = parseId(pathInfo);

        Map<String, String> fieldToError = new HashMap<>();
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");

        Post post = parsePost(request.getParameterMap(), fieldToError, loggedUser);

        if (!fieldToError.isEmpty()) {
            request.setAttribute("errors", fieldToError);
            request.setAttribute("title", request.getParameter("title"));
            request.setAttribute("content", request.getParameter("content"));
            request.getRequestDispatcher("/WEB-INF/views/post/post_form.jsp").forward(request, response);
            return;
        }

        if (post == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Niepoprawne dane posta.");
            return;
        }

        // TODO: Ogarnięcie dodawania i edytowania bo ten if nie ma sensu :)
        if (id != null) {
            // Edycja istniejącego posta
            Post existingPost = postDAO.findById(id);
            if (existingPost == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post nie został znaleziony.");
                return;
            }
            existingPost.setTitle(post.getTitle());
            existingPost.setContent(post.getContent());
            post = existingPost;
        } else {
            // Nowy post - autor jest już ustawiony w parsePost
        }

        postDAO.saveOrUpdate(post);
        response.sendRedirect(request.getContextPath() + "/post/list");
    }

    private Post parsePost(Map<String, String[]> paramToValue, Map<String, String> fieldToError, User author) {
        String[] titleArr = paramToValue.get("title");
        String[] contentArr = paramToValue.get("content");

        String title = (titleArr != null && titleArr.length > 0) ? titleArr[0].trim() : null;
        String content = (contentArr != null && contentArr.length > 0) ? contentArr[0].trim() : null;

        Topic topic = new Topic();

        if (title == null || title.isEmpty()) {
            fieldToError.put("title", "Tytuł jest wymagany.");
        }
        if (content == null || content.isEmpty()) {
            fieldToError.put("content", "Treść jest wymagana.");
        }
        if (author == null) {
            fieldToError.put("author", "Musisz być zalogowany, aby dodać posta.");
        }

        if (!fieldToError.isEmpty()) {
            return null;
        }

        return new Post(title, content, author, topic);
    }

    private Long parseId(String pathInfo) {
        if (pathInfo != null && pathInfo.length() > 1) {
            try {
                return Long.parseLong(pathInfo.substring(1));
            } catch (NumberFormatException e) {
                // log?
            }
        }
        return null;
    }
}
