package com.example.sewa.service;

import com.example.sewa.model.Request;
import com.example.sewa.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    
    @Autowired
    private RequestRepository requestRepository;
    
    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }
    
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }
    
    public List<Request> getRequestsByUserId(Long userId) {
        return requestRepository.findByUserId(userId);
    }
    
    public Request getRequestById(Long id) {
        return requestRepository.findById(id).orElse(null);
    }
}

