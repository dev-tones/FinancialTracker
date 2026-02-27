package com.twigg.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(
    Long id,
    LocalDate date,
    BigDecimal amount,
    String type,
    Long categoryId,
    String description
) {}
