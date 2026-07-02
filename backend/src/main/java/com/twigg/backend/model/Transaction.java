package com.twigg.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean reoccurring;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    public Transaction(){
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public BigDecimal getAmount(){
        return amount;
    }
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public Boolean getReoccurring(){
        return reoccurring;
    }
    public void setReoccurring(Boolean reoccurring){
        this.reoccurring = reoccurring;
    }
    public OffsetDateTime getCreatedAt(){
        return createdAt;
    }
    public OffsetDateTime getUpdatedAt(){
        return updatedAt;
    }
}
