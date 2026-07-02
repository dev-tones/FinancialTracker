package com.twigg.backend.service;

import java.util.List;


import com.twigg.backend.dto.TransactionCreateRequest;
import com.twigg.backend.dto.TransactionResponse;

public interface TransactionService {

    // public List<TransactionResponse> createTransaction(CreateTransactionRequest request);

    // public List<TransactionResponse> getAllTransactions();

    public TransactionResponse createTransaction(TransactionCreateRequest request);

    public List<TransactionResponse> getAllTransactions(int page, int pageSize);

    public TransactionResponse getTransactionById(Long transactionId);

    public List<TransactionResponse> getReoccurring(Long userId, int page, int pageSize);

    public List<TransactionResponse> getTransactionByUserId(Long userId, int page, int pageSize);
}
