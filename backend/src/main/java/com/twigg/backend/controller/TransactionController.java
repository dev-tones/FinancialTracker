package com.twigg.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.twigg.backend.service.TransactionService;
import com.twigg.backend.dto.CreateTransactionRequest;
import com.twigg.backend.dto.TransactionResponse;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping
    public TransactionResponse createTransaction(@RequestBody CreateTransactionRequest request) {
        TransactionResponse response = transactionService.createTransaction(request);
        return response;
    }
    @GetMapping
    public List<TransactionResponse> getAllTransactions() {
        List<TransactionResponse> transactionResponse = transactionService.getAllTransactions();
        return transactionResponse;
    }
    

}
    




