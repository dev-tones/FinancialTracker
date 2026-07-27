package com.twigg.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twigg.backend.model.GoalSave;
import com.twigg.backend.model.User;

public interface GoalSavingRepository extends JpaRepository<GoalSave, Long>{

    List<GoalSave> findByUser(User user);
}
