package com.twigg.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.twigg.backend.model.Category;

public record TransactionResponse(
    Long id,
    LocalDate date,
    BigDecimal amount,
    String type,
    Category category,
    String description,
    boolean reoccurring
) {}
