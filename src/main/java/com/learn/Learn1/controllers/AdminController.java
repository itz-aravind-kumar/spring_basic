package com.learn.Learn1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public ResponseEntity<String> getAdminInfo() {
        return ResponseEntity.ok("Welcome, Admin! This endpoint is protected and only accessible to users with the ADMIN role.");
    }
}
