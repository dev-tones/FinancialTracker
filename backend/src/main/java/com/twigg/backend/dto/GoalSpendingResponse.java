package com.twigg.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import com.twigg.backend.model.Category;
import com.twigg.backend.model.User;

public record GoalSpendingResponse(
    Category categoryId,
    User user,
    BigDecimal targetAmount,
    LocalDate startDate,
    LocalDate endDate,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {}
