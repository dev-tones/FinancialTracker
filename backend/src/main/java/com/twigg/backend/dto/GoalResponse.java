package com.twigg.backend.dto;

import java.time.OffsetDateTime;

import com.twigg.backend.model.Category;
import com.twigg.backend.model.User;

public record GoalResponse(
    Long id,
    String name,
    User user,
    Category category,
    OffsetDateTime startGoal,
    OffsetDateTime endGoal
) {}
