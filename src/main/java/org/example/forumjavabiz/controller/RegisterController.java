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

@WebServlet(name = "registerServlet", urlPatterns = "/user/register")
public class RegisterController extends HttpServlet {
    @EJB
    private UserDAO userDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // dodać haszowanie
        user.setRole("USER");

        if (!userDAO.usernameExists(username)) {
            userDAO.register(user);
            request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);

        } else {
            // komunikat: użytkownik już istnieje
            request.setAttribute("registerError", "Username already exists.");
            request.getRequestDispatcher("/WEB-INF/views/user/register.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/user/register.jsp").forward(request, response);

    }
}
