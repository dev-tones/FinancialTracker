package com.twigg.backend.dto;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.twigg.backend.model.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionCreateRequest {
    private LocalDate date;
    private BigDecimal amount;
    private String type;
    private Category category;
    private String description;
    private boolean reoccurring;
}
