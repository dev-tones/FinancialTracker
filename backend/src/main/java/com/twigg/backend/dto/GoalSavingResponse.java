package com.twigg.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.twigg.backend.model.User;

public record GoalSavingResponse(    
    User user,
    String name,
    BigDecimal targetAmount,
    BigDecimal currentAmount,
    LocalDate targetDate
) {}
