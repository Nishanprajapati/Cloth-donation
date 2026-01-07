package com.example.sewa.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, 
                           HttpServletResponse response, 
                           Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("userId") == null) {
            // Store the requested URL to redirect after login
            String requestedUrl = request.getRequestURI();
            session = request.getSession(true);
            session.setAttribute("redirectUrl", requestedUrl);
            
            // Return JSON response for AJAX requests or redirect for normal requests
            String acceptHeader = request.getHeader("Accept");
            if (acceptHeader != null && acceptHeader.contains("application/json")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Please login before accessing this page\"}");
                return false;
            }
            
            response.sendRedirect("/login.html?error=Please login before accessing this page");
            return false;
        }
        
        return true;
    }
}

