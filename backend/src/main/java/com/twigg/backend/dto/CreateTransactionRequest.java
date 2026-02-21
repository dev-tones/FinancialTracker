package com.twigg.backend.dto;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateTransactionRequest {
    private LocalDate date;
    private BigDecimal amount;
    private String type;
    private Long categoryId;
    private String description;

    public CreateTransactionRequest() {
    }
    public LocalDate getDate(){
        return date;
    }
    public void setDate(LocalDate date){
        this.date = date;
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
    public Long getCategoryId(){
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
}
