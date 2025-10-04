package com.saifutdinov.servlet;

import com.saifutdinov.dto.UserDto;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Main", urlPatterns = "/main")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String userFromSession = (String) request.getSession().getAttribute("user");

        String userFromCookie = "";
        String sessionIdentifier = request.getSession().getId();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("user".equalsIgnoreCase(name)) {
                    userFromCookie = cookie.getValue();
                } else if ("jsessionid".equalsIgnoreCase(name)) {
                    sessionIdentifier = cookie.getValue();
                }
            }
        }

        request.setAttribute("cookieUser", userFromCookie);
        request.setAttribute("sessionId", sessionIdentifier);
        request.setAttribute("sessionUser", userFromSession);

        request.getRequestDispatcher("main.ftl").forward(request, response);
    }

}