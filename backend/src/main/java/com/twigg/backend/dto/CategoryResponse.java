package com.twigg.backend.dto;

import com.twigg.backend.model.User;

public record CategoryResponse(
Long id, 
String name, 
User user
) {}
