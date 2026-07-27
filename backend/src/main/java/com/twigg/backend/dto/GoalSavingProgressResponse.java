package com.twigg.backend.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoalSavingProgressResponse {

    private String name;

    private BigDecimal amountSaved;

    private BigDecimal amountRemaining;

    private BigDecimal targetAmount;

    private BigDecimal percentComplete;

    private Long daysRemaining;

}
