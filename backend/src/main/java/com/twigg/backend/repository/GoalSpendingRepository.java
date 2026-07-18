package com.twigg.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twigg.backend.model.GoalSpend;

public interface GoalSpendingRepository extends JpaRepository<GoalSpend, Long> {

}
