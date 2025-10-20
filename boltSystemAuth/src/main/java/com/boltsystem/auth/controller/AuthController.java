package com.boltsystem.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boltsystem.auth.dto.LoginRequest;
import com.boltsystem.auth.dto.LoginResponse;
import com.boltsystem.auth.dto.RegisterRequest;
import com.boltsystem.auth.entity.User;
import com.boltsystem.auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request ) {
        return ResponseEntity.ok( authService.register( request.getUsername(), request.getPassword(), request.getRole()) );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login( request.getUsername(), request.getPassword() );
        return ResponseEntity.ok( new LoginResponse(token));
    } 

}
