package com.twigg.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.twigg.backend.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {



}
