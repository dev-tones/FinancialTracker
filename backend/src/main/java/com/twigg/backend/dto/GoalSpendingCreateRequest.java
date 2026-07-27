package com.twigg.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoalSpendingCreateRequest {

    private Long categoryId;
    private Long userId;
    private BigDecimal targetAmount;
    private LocalDate startDate;
    private LocalDate endDate;
}
