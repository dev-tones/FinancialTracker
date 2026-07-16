package com.twigg.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twigg.backend.model.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {

}
