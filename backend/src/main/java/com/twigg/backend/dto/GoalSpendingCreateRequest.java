package com.twigg.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.twigg.backend.model.Category;
import com.twigg.backend.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoalSpendingCreateRequest {

    private Category category;
    private User user;
    private BigDecimal targetAmount;
    private LocalDate startDate;
    private LocalDate endDate;

}
