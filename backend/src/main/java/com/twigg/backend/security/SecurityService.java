package com.twigg.backend.security;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.twigg.backend.model.User;
import com.twigg.backend.repository.UserRepository;

@Service
public class SecurityService {
    private final UserRepository userRepository;
    public SecurityService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getCurrentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalStateException("Authenticated user not in DB: " + email));
    }
}
