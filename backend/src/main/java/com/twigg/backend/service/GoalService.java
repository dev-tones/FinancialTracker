package com.twigg.backend.service;

import java.util.List;

import com.twigg.backend.dto.GoalSavingCreateRequest;
import com.twigg.backend.dto.GoalSavingProgressResponse;
import com.twigg.backend.dto.GoalSavingResponse;
import com.twigg.backend.dto.GoalSpendingCreateRequest;
import com.twigg.backend.dto.GoalSpendingProgressResponse;
import com.twigg.backend.dto.GoalSpendingResponse;
import com.twigg.backend.model.GoalSave;
import com.twigg.backend.model.GoalSpend;

public interface GoalService {
    public GoalSavingResponse createSavingsGoal(GoalSavingCreateRequest request);
    public GoalSpendingResponse createSpendingGoal(GoalSpendingCreateRequest request);
    public List<GoalSavingResponse> getAllSavingsGoalsByUser(Long userId);
    public List<GoalSpendingResponse> getAllSpendingGoalsByUser(Long userId);
    public GoalSavingProgressResponse savingProgress(GoalSave goal);
    public GoalSpendingProgressResponse spendingProgress(GoalSpend goal);
}
