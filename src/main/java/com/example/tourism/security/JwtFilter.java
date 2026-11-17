
package com.example.tourism.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Collections;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)
            throws ServletException, IOException {

        String token = request.getHeader("X-Auth-Token");

        if (token == null || token.isBlank()
                || !jwtUtil.validateToken(token)) {

            response.setStatus(401);
            response.setContentType("application/json");

            response.getWriter().write(
                "{\"message\":\"Unauthorized: Invalid token provided\"}"
            );

            return;
        }

        var authentication =
            new UsernamePasswordAuthenticationToken(
                "tourism-user",
                null,
                Collections.emptyList()
            );

        SecurityContextHolder.getContext()
            .setAuthentication(authentication);

        chain.doFilter(request, response);
    }
}
