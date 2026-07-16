package com.twigg.backend.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.twigg.backend.model.Category;
import com.twigg.backend.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoalCreateRequest {
    private String name;
    private User user;
    private Category category;
    private BigDecimal amount;
    private OffsetDateTime startGoal;
    private OffsetDateTime endGoal;
}
