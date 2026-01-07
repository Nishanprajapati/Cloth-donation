package com.example.sewa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "full_name", nullable = false)
    private String fullName;
    
    @Column(nullable = false)
    private String address;
    
    @Column(name = "drop_off_location", nullable = false)
    private String dropOffLocation;
    
    @Column(nullable = false)
    private String phone;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String season; // Spring, Summer, Winter, Autumn
    
    @Column(nullable = false)
    private String gender; // Male, Female, Unisex
    
    @Column(name = "clothes_type")
    private String clothesType; // Type of clothes requested
    
    @Column(nullable = false)
    private Integer quantity;
    
    @Column(name = "user_id")
    private Long userId; // Link to user who made the request
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

