package com.twigg.backend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.twigg.backend.dto.TransactionCreateRequest;
import com.twigg.backend.dto.TransactionResponse;
import com.twigg.backend.model.Category;
import com.twigg.backend.model.Transaction;
import com.twigg.backend.model.User;
import com.twigg.backend.repository.CategoryRepository;
import com.twigg.backend.repository.TransactionRepository;
import com.twigg.backend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public TransactionResponse createTransaction(TransactionCreateRequest request){
        // 1. Create entity
        Transaction tx = new Transaction();

        //find user
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("User not found: " + email));
        tx.setUser(user);

        //find categoryId
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new NoSuchElementException("Category not found: " + request.getCategoryId()));
        // 2. Copy DTO -> entity
        //tx.getId();
        tx.setDate(request.getDate());
        tx.setAmount(request.getAmount());
        tx.setType(request.getType());
        tx.setCategory(category);
        tx.setDescription(request.getDescription());
        tx.setReoccurring(request.getReoccurring());

        // 3. Set timestamps
        // Hibernate handles setting the timestamps with the model annotations

        // 4. Save
        Transaction saved = transactionRepository.save(tx);

        // 5. Map to response
        return mapToResponse(saved);
    }

    @Override
    public List<Transaction> getTransactionByUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("User not found: " + email));
        List<Transaction> tx = transactionRepository.findByUser(user);
        return tx;
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
            transaction.getUser(),
            transaction.getDate(),
            transaction.getAmount(),
            transaction.getType(),
            transaction.getCategory(),
            transaction.getDescription(),
            transaction.getReoccurring()
        );
    }
}
    

