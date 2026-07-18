package com.twigg.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twigg.backend.model.GoalSave;

public interface GoalSavingRepository extends JpaRepository<GoalSave, Long>{

}
