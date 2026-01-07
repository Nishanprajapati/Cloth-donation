package com.example.sewa.controller;

import com.example.sewa.service.DonationService;
import com.example.sewa.service.RequestService;
import com.example.sewa.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    private DonationService donationService;
    
    @Autowired
    private RequestService requestService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }
        
        // Add statistics
        try {
            model.addAttribute("totalDonations", donationService.getAllDonations().size());
            model.addAttribute("totalRequests", requestService.getAllRequests().size());
            model.addAttribute("totalUsers", userService.getAllUsers().size());
        } catch (Exception e) {
            // If tables don't exist yet, set defaults
            model.addAttribute("totalDonations", 0);
            model.addAttribute("totalRequests", 0);
            model.addAttribute("totalUsers", 0);
        }
        
        return "index";
    }

    @GetMapping("/index.html")
    public String indexHtml(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }
        
        // Add statistics
        try {
            model.addAttribute("totalDonations", donationService.getAllDonations().size());
            model.addAttribute("totalRequests", requestService.getAllRequests().size());
            model.addAttribute("totalUsers", userService.getAllUsers().size());
        } catch (Exception e) {
            // If tables don't exist yet, set defaults
            model.addAttribute("totalDonations", 0);
            model.addAttribute("totalRequests", 0);
            model.addAttribute("totalUsers", 0);
        }
        
        return "index";
    }

    @GetMapping("/about.html")
    public String about(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }
        return "about";
    }

    @GetMapping("/donate.html")
    public String donate(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }
        return "donate";
    }

    @GetMapping("/request.html")
    public String request(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }
        return "request";
    }

    @GetMapping("/contact.html")
    public String contact(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }
        return "contact";
    }

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    @GetMapping("/signup.html")
    public String signup() {
        return "signup";
    }
}

