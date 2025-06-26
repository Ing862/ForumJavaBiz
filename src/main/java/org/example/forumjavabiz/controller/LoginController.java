package org.example.forumjavabiz.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.forumjavabiz.dao.UserDAO;
import org.example.forumjavabiz.entity.User;

import java.io.IOException;

@WebServlet(name = "loginServlet",
            urlPatterns = "/user/login")
public class LoginController extends HttpServlet {
    @EJB
    private UserDAO userDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDAO.findByUsernameAndPassword(username, password); // Sprawdza poprawność nazwa użytkownika + hasło

        if (user != null) { // Logowanie poprawne
            request.getSession().setAttribute("loggedUser", user); // Tworzy sesje użytkownika
            response.sendRedirect(request.getContextPath() + "/index.jsp"); // Przekierowuje na strone index
        } else { // Błąd logowania
            request.setAttribute("loginError", "Invalid login or password");
            request.setAttribute("registerError", "Username already exists.");
            request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
    }
}

