package com.twigg.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.twigg.backend.model.Category;
import com.twigg.backend.model.User;
public record TransactionResponse(
    Long id,
    User user,
    LocalDate date,
    BigDecimal amount,
    String type,
    Category category,
    String description,
    Boolean reoccurring
) {}
