package com.twigg.backend.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class GoalSpendingProgressResponse {

    private BigDecimal targetAmount;

    private BigDecimal amountSpent;

    private BigDecimal amountRemaining;

    private BigDecimal percentageUsed;

    private Long daysRemaining;
}
