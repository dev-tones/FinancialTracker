package com.twigg.backend.dto;

import java.time.OffsetDateTime;

public record UserResponse (
    
    Long id,
    String userRole,
    String userName,
    String firstName,
    String lastName,
    String email,
    String phone,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt

){}
