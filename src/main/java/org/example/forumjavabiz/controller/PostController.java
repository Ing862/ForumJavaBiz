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

    // TODO: Dodać ograniczenia do dodawania postów dla zalogowanych użytkowników
    // TODO: Dodać ograniczenia dla edycji i usuwania postów
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
                if (pathInfo != null && pathInfo.length() > 1) {
                    handlePostRemoveConfirm(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Post ID is null.");
                }
                break;
            case "/post/view": // Wyświetlenie konkretnego posta
                if (pathInfo != null && pathInfo.length() > 1) {
                    handlePostView(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Post ID is null.");
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
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post was not found.");
                return;
            }

            request.setAttribute("post", post);
            request.getRequestDispatcher("/WEB-INF/views/post/post_view.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid post ID.");
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
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post was not found.");
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
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid post ID for deletion.");
            return;
        }

        Post post = postDAO.findById(id);
        if (post == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post was not found.");
            return;
        }

        postDAO.delete(post);
        response.sendRedirect(request.getContextPath() + "/post/list");
    }

    private void handlePostRemoveConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // np. "/5"
        Long id = parseId(pathInfo);

        if (id == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Wrong post ID.");
            return;
        }

        Post post = postDAO.findById(id);
        if (post == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post was not found.");
            return;
        }

        request.setAttribute("post", post);
        request.getRequestDispatcher("/WEB-INF/views/post/post_remove_confirm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if ("/topic/remove".equals(path)) {
            handlePostRemove(request, response);
        } else if ("/topic/edit".equals(path)) {
            handlePostEditPost(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void handlePostEditPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        Long id = null;
        if (idParam != null && !idParam.isEmpty()) {
            try {
                id = Long.parseLong(idParam);
            } catch (NumberFormatException e) {
                // log?
            }
        }

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
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid post data.");
            return;
        }

        if (id != null) {
            // Edycja istniejącego posta
            Post existingPost = postDAO.findById(id);
            if (existingPost == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post was not found.");
                return;
            }
            existingPost.setTitle(post.getTitle());
            existingPost.setContent(post.getContent());
            postDAO.saveOrUpdate(existingPost);
        } else {
            postDAO.saveOrUpdate(post);
        }
        response.sendRedirect(request.getContextPath() + "/post/list");
    }

    private Post parsePost(Map<String, String[]> paramToValue, Map<String, String> fieldToError, User author) {
        String[] titleArr = paramToValue.get("title");
        String[] contentArr = paramToValue.get("content");

        String title = (titleArr != null && titleArr.length > 0) ? titleArr[0].trim() : null;
        String content = (contentArr != null && contentArr.length > 0) ? contentArr[0].trim() : null;

        Topic topic = new Topic();

        if (title == null || title.isEmpty()) {
            fieldToError.put("title", "Title is required.");
        }
        if (content == null || content.isEmpty()) {
            fieldToError.put("content", "Content is required.");
        }
        if (author == null) {
            fieldToError.put("author", "You must be logged in.");
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
