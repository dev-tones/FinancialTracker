package com.twigg.backend.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.twigg.backend.dto.TransactionCreateRequest;
import com.twigg.backend.dto.TransactionResponse;
import com.twigg.backend.model.Category;
import com.twigg.backend.model.Transaction;
import com.twigg.backend.repository.CategoryRepository;
import com.twigg.backend.repository.TransactionRepository;
import com.twigg.backend.repository.UserRepository;
import com.twigg.backend.security.SecurityService;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final SecurityService securityService;
    public TransactionServiceImpl(SecurityService securityService, TransactionRepository transactionRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.securityService = securityService;
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public TransactionResponse createTransaction(TransactionCreateRequest request){
        Transaction tx = new Transaction();
        tx.setUser(securityService.getCurrentUser());
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new NoSuchElementException("Category not found: " + request.getCategoryId()));
        tx.setDate(request.getDate());
        tx.setAmount(request.getAmount());
        tx.setType(request.getType());
        tx.setCategory(category);
        tx.setDescription(request.getDescription());
        tx.setReoccurring(request.getReoccurring());
        Transaction saved = transactionRepository.save(tx);
        return mapToResponse(saved);
    }

    @Override
    public List<Transaction> getTransactionByUser(){
        List<Transaction> tx = transactionRepository.findByUser(securityService.getCurrentUser());
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
    @Transactional(readOnly = true)
    public BigDecimal getTotalTransactionAmount(){
        return transactionRepository.sumAmountByUser(securityService.getCurrentUser());
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
    

