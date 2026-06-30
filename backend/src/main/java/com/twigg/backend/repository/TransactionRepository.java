package com.twigg.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twigg.backend.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    public List<Transaction> findByReoccurring(boolean reoccurring);

}
