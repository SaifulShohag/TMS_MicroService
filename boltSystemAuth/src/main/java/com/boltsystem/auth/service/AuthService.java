package com.boltsystem.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.boltsystem.auth.config.JwtConfig;
import com.boltsystem.auth.entity.Role;
import com.boltsystem.auth.entity.User;
import com.boltsystem.auth.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtConfig jwtConfig;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User register( String username, String password, String role ) {
        if( userRepository.findByUsername(username).isPresent() ) {
            throw new RuntimeException("User already exist!");
        }

        User user = new User( username, encoder.encode( password ), Role.valueOf(role) );
        return userRepository.save( user );
    }

    public String login( String username, String password ) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));
        User user = userRepository.findByUsername(username).orElseThrow();
        return jwtConfig.generateToken(username, user.getRole().name());

    }

}
