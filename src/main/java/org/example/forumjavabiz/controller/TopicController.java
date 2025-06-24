package org.example.forumjavabiz.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.forumjavabiz.dao.TopicDAO;
import org.example.forumjavabiz.entity.Topic;
import org.example.forumjavabiz.entity.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet (name = "TopicController", urlPatterns = {
        "/topic/list",
        "/topic/edit/*",
        "/topic/remove/*",
        "/topic/view/*"
})
public class TopicController extends HttpServlet {
    @EJB
    private TopicDAO topicDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String pathInfo = request.getPathInfo();

        switch (path) {
            case "/topic/list": // Wyświetlenie listy tematów
                handleTopicList(request, response);
                break;
            case "/topic/edit": // Tworzenie lub edycja tematu (formularz)
                handleGetEditTopicGet(request, response);
                break;
            case "/topic/remove": // Usunięcie tematu
                handleTopicRemove(request, response);
                break;
            case "/topic/view": // Wyświetlenie konkretnego tematu
                if (pathInfo != null && pathInfo.length() > 1) {
                    handleTopicView(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Topic ID is null.");
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void handleTopicList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Topic> topics = topicDAO.findAll();
        request.setAttribute("postList", topics);
        // TODO: Przekierowanie do listy tematów
        //request.getRequestDispatcher("/WEB-INF/views/post/post_list.jsp").forward(request, response);
    }

    private void handleTopicView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // np. "/5"

        try {
            Long postId = Long.parseLong(pathInfo.substring(1));
            Topic topic = topicDAO.findById(postId);

            if (topic == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Topic was not found");
                return;
            }

            request.setAttribute("topic", topic);
            // TODO: Przekierowanie na widok tematu
            //request.getRequestDispatcher("/WEB-INF/views/post/post_view.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Wrong topic ID.");
        }
    }

    private void handleGetEditTopicGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo(); // np. "/5" lub null (nowy temat)
        Topic topic = null;

        if (pathInfo != null && pathInfo.length() > 1) {
            Long id = parseId(pathInfo);
            if (id != null) {
                topic = topicDAO.findById(id);
                if (topic == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Topic was not found.");
                    return;
                }
            }
        }

        if (topic != null) {
            request.setAttribute("id", topic.getId());
            request.setAttribute("title", topic.getTitle());
            request.setAttribute("description", topic.getDescription());
            request.setAttribute("author", topic.getAuthor());
        }

        // TODO: Przekierowanie na form tematu
//        request.getRequestDispatcher("/WEB-INF/views/post/post_form.jsp").forward(request, response);
    }

    private void handleTopicRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        Long id = parseId(pathInfo);
        if (id == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Wrong topic ID.");
            return;
        }

        Topic topic = topicDAO.findById(id);
        if (topic == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Topic was not found.");
            return;
        }

        topicDAO.deleteTopic(topic);
        // TODO: Przekierowanie na listę tematów
        //response.sendRedirect(request.getContextPath() + "/post/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if ("/topic/edit".equals(path)) {
            handleTopicEditPost(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void handleTopicEditPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        Long id = parseId(pathInfo);

        Map<String, String> fieldToError = new HashMap<>();
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");

        Topic topic = parseTopic(request.getParameterMap(), fieldToError, loggedUser);

        if (!fieldToError.isEmpty()) {
            request.setAttribute("errors", fieldToError);
            request.setAttribute("title", request.getParameter("title"));
            request.setAttribute("description", request.getParameter("description"));
            // TODO: Przekierowanie na form tematu
            //request.getRequestDispatcher("/WEB-INF/views/post/post_form.jsp").forward(request, response);
            return;
        }

        if (topic == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid topic data.");
            return;
        }

        if (id != null) {
            // Edycja istniejącego tematu
            Topic existingTopic = topicDAO.findById(id);
            if (existingTopic == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Topic was not found.");
                return;
            }
            existingTopic.setTitle(topic.getTitle());
            existingTopic.setDescription(topic.getDescription());
            topicDAO.saveOrUpdate(existingTopic);
        } else {
            topicDAO.saveOrUpdate(topic);
        }
        // TODO: Przekierowanie na listę tematów
        //response.sendRedirect(request.getContextPath() + "/post/list");
    }

    private Topic parseTopic(Map<String, String[]> paramToValue, Map<String, String> fieldToError, User author) {
        String[] titleArr = paramToValue.get("title");
        String[] descriptionArr = paramToValue.get("description");

        String title = (titleArr != null && titleArr.length > 0) ? titleArr[0].trim() : null;
        String description = (descriptionArr != null && descriptionArr.length > 0) ? descriptionArr[0].trim() : null;

        if (title == null || title.isEmpty()) {
            fieldToError.put("title", "Title is required.");
        }
        if (description == null || description.isEmpty()) {
            fieldToError.put("content", "Description is required.");
        }
        if (author == null) {
            fieldToError.put("author", "You must be logged in to create a topic.");
        }

        if (!fieldToError.isEmpty()) {
            return null;
        }

        return new Topic(title, description, author);
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
