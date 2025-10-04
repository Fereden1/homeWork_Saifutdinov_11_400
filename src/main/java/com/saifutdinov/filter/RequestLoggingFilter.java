package com.saifutdinov.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@WebFilter(urlPatterns = "/*")
public class RequestLoggingFilter extends HttpFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);
    private ServletContext context;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        Map<String, String[]> queryParams = req.getParameterMap();
        if (queryParams != null && !queryParams.isEmpty()) {
            String formattedParams = formatParams(queryParams);

            context.log("Client " + req.getRemoteAddr() + " -> params: " + formattedParams);
            logger.info("{} sent params {}", req.getRemoteAddr(), formattedParams);
        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();
        context.log("RequestLoggingFilter successfully initialized");
    }

    private String formatParams(Map<String, String[]> params) {
        return params.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + Arrays.toString(entry.getValue()))
                .collect(Collectors.joining(", ", "{", "}"));
    }
}
