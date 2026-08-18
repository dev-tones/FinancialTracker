package com.twigg.backend.service;

import java.math.BigDecimal;
import java.util.List;

import com.twigg.backend.dto.TransactionCreateRequest;
import com.twigg.backend.dto.TransactionResponse;
import com.twigg.backend.model.Transaction;

public interface TransactionService {

    // public List<TransactionResponse> createTransaction(CreateTransactionRequest request);

    // public List<TransactionResponse> getAllTransactions();

    public TransactionResponse createTransaction(TransactionCreateRequest request);

    public List<TransactionResponse> getAllTransactions(int page, int pageSize);

    public TransactionResponse getTransactionById(Long transactionId);

    public List<Transaction> getTransactionByUser();

    public BigDecimal getTotalTransactionAmount();
}
