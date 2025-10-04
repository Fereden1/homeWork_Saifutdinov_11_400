package com.saifutdinov.servlet;

import com.saifutdinov.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignUp", urlPatterns = "/sign_up")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("sign_up.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("lastname");
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");

        boolean created = UserServiceImpl.signUp(userLogin, userPassword, firstName, lastName);

        if (created) {
            response.sendRedirect("registered.ftl");
        } else {
            response.sendRedirect("user_already_exists.ftl");
        }
    }
}