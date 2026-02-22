package com.twigg.backend.service;

import java.util.List;
import com.twigg.backend.dto.CreateTransactionRequest;
import com.twigg.backend.dto.TransactionResponse;

public interface TransactionService {

    // public List<TransactionResponse> createTransaction(CreateTransactionRequest request);

    // public List<TransactionResponse> getAllTransactions();

    public TransactionResponse createTransaction(CreateTransactionRequest request);

    public List<TransactionResponse> getAllTransactions();

}
