package com.example.account.controllers;

import com.example.account.dtos.SignupRequest;
import com.example.account.dtos.SignUpResponse;
import com.example.account.entities.UserInformation;
import com.example.account.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequest request) {
        if (signUpService.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        if (signUpService.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already registered");
        }

        signUpService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }
    @GetMapping("/lists")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SignUpResponse>> getAllUsers() {
        List<SignUpResponse> users = signUpService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/byId/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserInformation> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(signUpService.getUserById(id));
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<UserInformation> updateUser(@PathVariable Long id, @RequestBody UserInformation user) {
        return ResponseEntity.ok(signUpService.updateUser(id, user));
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        signUpService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

