package com.twigg.backend.dto;

import java.math.BigDecimal;

import com.twigg.backend.model.User;

public record AccountResponse(
    Long id,
    String name,
    User user,
    BigDecimal balance
) {}
