package com.twigg.backend.service;

import com.twigg.backend.repository.TransactionRepository;
import com.twigg.backend.dto.CreateTransactionRequest;
import com.twigg.backend.dto.TransactionResponse;
import com.twigg.backend.model.Transaction;
import org.springframework.stereotype.Service;
//import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @Override
    public TransactionResponse createTransaction(CreateTransactionRequest request){
        // 1. Create entity
        Transaction tx = new Transaction();

        // 2. Copy DTO -> entity
        //tx.getId();
        tx.setAmount(request.getAmount());
        tx.setDate(request.getDate());
        tx.setType(request.getType());
        tx.setCategoryId(request.getCategoryId());
        tx.setDescription(request.getDescription());

        // 3. Set timestamps
        tx.setCreatedAt(LocalDate.now());
        tx.setUpdatedAt(LocalDate.now());

        // 4. Save
        Transaction saved = transactionRepository.save(tx);

        // 5. Map to response
        return mapToResponse(saved);
    }


    @Override
    public List<TransactionResponse> getAllTransactions(){

        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionResponse> responses = new ArrayList<>();

        for (Transaction tx : transactions){
            responses.add(mapToResponse(tx));
        }
        return responses;
    }

    //Take a transaction copy fields into a TransactionResponse
    //helper method
    private TransactionResponse mapToResponse(Transaction transaction){
        //create entity

        //.        -------CHANGED DUE TO RESPONSE RECORD-----
        // TransactionResponse response = new TransactionResponse();
        // response.setId(transaction.getId());
        // response.setAmount(transaction.getAmount());
        // response.setType(transaction.getType());
        // response.setDate(transaction.getDate());
        // response.setCategoryId(transaction.getCategoryId());
        // response.setDescription(transaction.getDescription());
        // return response;
        return new TransactionResponse(
            transaction.getId(),
            transaction.getDate(),
            transaction.getAmount(),
            transaction.getType(),
            transaction.getCategoryId(),
            transaction.getDescription()
        );


    }
}
    

