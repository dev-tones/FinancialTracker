package com.twigg.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twigg.backend.model.GoalSpend;
import com.twigg.backend.model.User;

public interface GoalSpendingRepository extends JpaRepository<GoalSpend, Long> {

    List<GoalSpend> findByUser(User user);
}
