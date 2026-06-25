package com.twigg.backend.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.twigg.backend.model.User;
import com.twigg.backend.repository.UserRepository;
import com.twigg.backend.security.JwtService;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtService jwtService){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }
    // public String authenticate(String email, String password){
    //     Optional<User> u = userRepository.findByEmail(email);
    //     try {
    //       User user = u.get();
    //         if(!passwordEncoder.matches(password, user.getPasswordHash())){
    //             throw new BadCredentialsException("Password is incorrect");
    //         }
    //         String token = jwtService.generateToken(user.getEmail());
    //         return token;
    //       } catch (NoSuchElementException e) {
    //         throw new UsernameNotFoundException("User not found.");
    //         } 
    // }

    // chaining orElseThrow(()
    public String authenticate(String email, String password) {
        // 1. Fetch the user or throw the exception immediately if missing
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        // 2. Validate the password
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new BadCredentialsException("Password is incorrect");
        }
        // 3. Generate and return the token
        return jwtService.generateToken(user.getEmail());
    }
}