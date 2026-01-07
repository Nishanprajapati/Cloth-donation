package com.example.sewa.controller;

import com.example.sewa.model.Donation;
import com.example.sewa.service.DonationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DonationController {
    
    @Autowired
    private DonationService donationService;
    
    @PostMapping("/donate")
    public String submitDonation(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String phone,
                                 @RequestParam String clothesType,
                                 @RequestParam Integer quantity,
                                 @RequestParam(required = false) String notes,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        try {
            Donation donation = new Donation();
            donation.setName(name);
            donation.setEmail(email);
            donation.setPhone(phone);
            donation.setClothesType(clothesType);
            donation.setQuantity(quantity);
            donation.setNotes(notes != null ? notes : "");
            
            // Link to user if logged in
            Long userId = (Long) session.getAttribute("userId");
            if (userId != null) {
                donation.setUserId(userId);
            }
            
            donationService.createDonation(donation);
            return "redirect:/donate.html?success=" + java.net.URLEncoder.encode("Thank you! Your donation has been submitted successfully.", java.nio.charset.StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "redirect:/donate.html?error=" + java.net.URLEncoder.encode("Failed to submit donation: " + e.getMessage(), java.nio.charset.StandardCharsets.UTF_8);
        }
    }
    
    @GetMapping("/donations")
    public String viewDonations() {
        // This can be used to view all donations (admin view)
        return "donations";
    }
}

