package com.twigg.backend.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.twigg.backend.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    public Page<Transaction> findByUserIdAndReoccurring(Long userId, boolean reoccurring, Pageable pageable);

    public Page<Transaction> findByUserId(Long userId, Pageable pageable);
}
