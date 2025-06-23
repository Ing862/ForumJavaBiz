package org.example.forumjavabiz.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.forumjavabiz.dao.UserDAOImpl;
import org.example.forumjavabiz.entity.User;

import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @EJB
    private UserDAOImpl userDAO;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // w produkcji -> haszuj!

        userDAO.register(user);

        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("register.jsp");
    }
}
