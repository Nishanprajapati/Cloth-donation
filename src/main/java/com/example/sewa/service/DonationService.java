package com.example.sewa.service;

import com.example.sewa.model.Donation;
import com.example.sewa.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {
    
    @Autowired
    private DonationRepository donationRepository;
    
    public Donation createDonation(Donation donation) {
        return donationRepository.save(donation);
    }
    
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }
    
    public List<Donation> getDonationsByUserId(Long userId) {
        return donationRepository.findByUserId(userId);
    }
    
    public Donation getDonationById(Long id) {
        return donationRepository.findById(id).orElse(null);
    }
}

