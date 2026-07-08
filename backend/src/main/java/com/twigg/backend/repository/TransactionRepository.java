package com.twigg.backend.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twigg.backend.model.Transaction;
import com.twigg.backend.model.User;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

List<Transaction> findByUser(User user);

}
