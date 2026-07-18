package com.twigg.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.twigg.backend.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class GoalSavingCreateRequest {
    private User user;
    private String name;
    private BigDecimal targetAmount;
    private BigDecimal currentAmount;
    private LocalDate targetDate;
}
