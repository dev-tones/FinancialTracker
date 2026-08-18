package com.twigg.backend.repository;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.twigg.backend.model.Category;
import com.twigg.backend.model.Transaction;
import com.twigg.backend.model.User;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

List<Transaction> findByUser(User user);

List<Transaction> findByCategory(Category category);

List<Transaction> findByUserAndCategoryAndDateBetween(
    User user,
    Category category,
    LocalDate startDate,
    LocalDate endDate
);

@Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.user = :user")
BigDecimal sumAmountByUser(@Param("user") User user);

}
