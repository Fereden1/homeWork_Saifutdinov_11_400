package com.saifutdinov.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/user", filterName = "Authorisation")
public class AuthorisationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String sessionUser = (String) req.getSession().getAttribute("user");
        if (!"admin".equals(sessionUser)) {
            res.sendRedirect("access_denied.ftl");
            return;
        }
        chain.doFilter(req,res);
    }
}