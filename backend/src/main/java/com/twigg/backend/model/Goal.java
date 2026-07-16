package com.twigg.backend.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "goal")
public class Goal {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private BigDecimal amount;

    private OffsetDateTime startGoal;

    private OffsetDateTime endGoal;

    @Setter(AccessLevel.NONE)
    private OffsetDateTime updatedAt;
    
    @Setter(AccessLevel.NONE)
    private OffsetDateTime createdAt;

    public Goal(){

    }
}
