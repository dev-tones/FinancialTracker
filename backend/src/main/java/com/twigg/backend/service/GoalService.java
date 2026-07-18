package com.twigg.backend.service;

import java.util.List;

import com.twigg.backend.dto.GoalSavingCreateRequest;
import com.twigg.backend.dto.GoalSavingResponse;
import com.twigg.backend.dto.GoalSpendingCreateRequest;
import com.twigg.backend.dto.GoalSpendingResponse;

public interface GoalService {
    public GoalSavingResponse createSavingsGoal(GoalSavingCreateRequest request);
    public GoalSpendingResponse createSpendingGoal(GoalSpendingCreateRequest request);
    public List<GoalSavingResponse> getAllSavingsGoalsByUser(Long userId);
    public List<GoalSpendingResponse> getAllSpendingGoalsByUser(Long userId);
    public GoalSavingResponse savingProgress(Long savingId);
    public GoalSpendingResponse spendingProgress(Long spendingId);
}
