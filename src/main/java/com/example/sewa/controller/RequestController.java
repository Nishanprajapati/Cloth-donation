package com.example.sewa.controller;

import com.example.sewa.model.Request;
import com.example.sewa.service.RequestService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RequestController {
    
    @Autowired
    private RequestService requestService;
    
    @PostMapping("/request")
    public String submitRequest(@RequestParam String fullName,
                               @RequestParam String address,
                               @RequestParam String dropOffLocation,
                               @RequestParam String phone,
                               @RequestParam String email,
                               @RequestParam String season,
                               @RequestParam String gender,
                               @RequestParam String clothesType,
                               @RequestParam Integer quantity,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        try {
            Request request = new Request();
            request.setFullName(fullName);
            request.setAddress(address);
            request.setDropOffLocation(dropOffLocation);
            request.setPhone(phone);
            request.setEmail(email);
            request.setSeason(season);
            request.setGender(gender);
            request.setClothesType(clothesType);
            request.setQuantity(quantity);
            
            // Link to user if logged in
            Long userId = (Long) session.getAttribute("userId");
            if (userId != null) {
                request.setUserId(userId);
            }
            
            requestService.createRequest(request);
            return "redirect:/request.html?success=" + java.net.URLEncoder.encode("Thank you! Your request has been submitted successfully.", java.nio.charset.StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "redirect:/request.html?error=" + java.net.URLEncoder.encode("Failed to submit request: " + e.getMessage(), java.nio.charset.StandardCharsets.UTF_8);
        }
    }
    
    @GetMapping("/requests")
    public String viewRequests() {
        // This can be used to view all requests (admin view)
        return "requests";
    }
}

