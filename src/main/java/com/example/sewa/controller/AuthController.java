package com.example.sewa.controller;

import com.example.sewa.model.User;
import com.example.sewa.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public String register(@RequestParam String username,
                          @RequestParam String email,
                          @RequestParam String password,
                          @RequestParam String confirmPassword,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        try {
            if (!password.equals(confirmPassword)) {
                return "redirect:/signup.html?error=" + java.net.URLEncoder.encode("Passwords do not match", java.nio.charset.StandardCharsets.UTF_8);
            }
            
            User user = userService.registerUser(username, email, password);
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());
            return "redirect:/index.html";
        } catch (Exception e) {
            return "redirect:/signup.html?error=" + java.net.URLEncoder.encode(e.getMessage(), java.nio.charset.StandardCharsets.UTF_8);
        }
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String email,
                       @RequestParam String password,
                       HttpSession session,
                       RedirectAttributes redirectAttributes) {
        try {
            User user = userService.loginUser(email, password);
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());
            return "redirect:/index.html";
        } catch (Exception e) {
            return "redirect:/login.html?error=" + java.net.URLEncoder.encode(e.getMessage(), java.nio.charset.StandardCharsets.UTF_8);
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index.html";
    }
}

