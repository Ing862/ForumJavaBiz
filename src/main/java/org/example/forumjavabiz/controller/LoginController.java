package org.example.forumjavabiz.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.forumjavabiz.dao.UserDAO;
import org.example.forumjavabiz.entity.User;

import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginController extends HttpServlet {
    @EJB
    private UserDAO userDAO;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userDAO.findByUsernameAndPassword(username, password);

        if (user != null) {
            req.getSession().setAttribute("user", user);
            try {
                resp.sendRedirect("index.jsp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            resp.sendRedirect("login.jsp?error=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.jsp");
    }
}

