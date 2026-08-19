package com.twigg.backend.dto;

import java.time.OffsetDateTime;

import com.twigg.backend.model.UserRole;

public record UserResponse (
    
    Long id,
    UserRole userRole,
    String firstName,
    String lastName,
    String email,
    String phone,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt

){}
