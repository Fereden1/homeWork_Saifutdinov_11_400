package com.saifutdinov.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Logout", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        invalidateSessionAndCookies(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        invalidateSessionAndCookies(req, resp);
    }

    private void invalidateSessionAndCookies(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] allCookies = request.getCookies();
        if (allCookies != null) {
            for (Cookie cookie : allCookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        HttpSession httpSession = request.getSession(false); // false → чтобы не создавать новую
        if (httpSession != null) {
            httpSession.invalidate();
        }

        response.sendRedirect("index.ftl");
    }
}