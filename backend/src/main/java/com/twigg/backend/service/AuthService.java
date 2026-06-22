package com.twigg.backend.service;

import com.twigg.backend.dto.UserResponse;

public interface AuthService {
    public UserResponse findByEmail(String email);
    
}
