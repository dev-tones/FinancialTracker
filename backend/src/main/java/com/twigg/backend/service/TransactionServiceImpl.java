package com.twigg.backend.service;

import com.twigg.backend.repository.TransactionRepository;
import com.twigg.backend.dto.TransactionCreateRequest;
import com.twigg.backend.dto.TransactionResponse;
import com.twigg.backend.model.Transaction;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
//import java.util.stream.Collectors;
// import java.time.LocalDate;
// import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @Override
    public TransactionResponse createTransaction(TransactionCreateRequest request){
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
        // Hibernate handles setting the timestamps with the model annotations

        // 4. Save
        Transaction saved = transactionRepository.save(tx);

        // 5. Map to response
        return mapToResponse(saved);
    }


    @Override
    public List<TransactionResponse> getAllTransactions(int page, int pageSize){

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Transaction> transactions = transactionRepository.findAll(pageable);
        return transactions
        .stream()
        .map(this::mapToResponse)
        .toList();
    }

    @Override
    public TransactionResponse getTransactionById(Long transactionId){
        Transaction transactionById = transactionRepository.findById(transactionId)
            .orElseThrow(() -> new RuntimeException("Transaction not found"));
            return mapToResponse(transactionById);
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
    

