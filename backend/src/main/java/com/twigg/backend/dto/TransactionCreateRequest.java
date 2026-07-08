package com.twigg.backend.dto;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    private Long categoryId;
    private String description;
    private Boolean reoccurring;
}
