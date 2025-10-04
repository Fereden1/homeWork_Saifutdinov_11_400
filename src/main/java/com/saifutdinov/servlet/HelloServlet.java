package com.saifutdinov.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.stream.Collectors;


@WebServlet(name = "Hello", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("Hi! Response to your GET request.");
        out.println("Request parameters:");

        Map<String, String[]> queryParams = req.getParameterMap();
        if (queryParams.isEmpty()) {
            out.println("  (none provided)");
        } else {
            for (Map.Entry<String, String[]> entry : queryParams.entrySet()) {
                out.println("  " + entry.getKey() + " = " + String.join(", ", entry.getValue()));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        out.println("Received a POST request.");
        out.println("Body content: " + (requestBody.isEmpty() ? "(empty)" : requestBody));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        out.println("Received a PUT request.");
        out.println("Body content: " + (requestBody.isEmpty() ? "(empty)" : requestBody));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        out.println("Received a DELETE request.");
        out.println("Body content: " + (requestBody.isEmpty() ? "(empty)" : requestBody));
    }
}