package com.russai.russai.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AccessKeyFilter extends OncePerRequestFilter {

    private final String accessKey = System.getenv("ACCESS_KEY");

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Only guard the API. The page and all static assets pass straight through.
        return !request.getRequestURI().startsWith("/api/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // If ACCESS_KEY is not set on the server, nothing is locked (safe for local dev
        // and for deploying this file before the passcode is turned on).
        if (accessKey == null || accessKey.isBlank()) {
            chain.doFilter(request, response);
            return;
        }

        String provided = request.getHeader("X-Access-Key");
        if (accessKey.equals(provided)) {
            chain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"unauthorized\"}");
        }
    }
}