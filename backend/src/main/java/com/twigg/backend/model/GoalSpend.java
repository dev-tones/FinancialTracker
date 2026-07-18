package com.twigg.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "spending_goal")
public class GoalSpend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Column(name = "category_id")
    private Category category;

    @ManyToOne
    @Column(name = "user_id")
    private User user;

    private BigDecimal targetAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    @CreationTimestamp
    private OffsetDateTime createdDate;

    @UpdateTimestamp
    private OffsetDateTime updatedDate;


}
