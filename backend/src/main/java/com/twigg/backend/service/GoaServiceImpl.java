package com.twigg.backend.service;

import java.util.ArrayList;
import java.util.List;

import com.twigg.backend.dto.GoalSavingCreateRequest;
import com.twigg.backend.dto.GoalSavingResponse;
import com.twigg.backend.dto.GoalSpendingCreateRequest;
import com.twigg.backend.dto.GoalSpendingResponse;
import com.twigg.backend.repository.CategoryRepository;
import com.twigg.backend.repository.GoalSavingRepository;
import com.twigg.backend.repository.GoalSpendingRepository;
import com.twigg.backend.repository.UserRepository;

public class GoaServiceImpl implements GoalService {

    private GoalSavingRepository goalSavingRepository;
    private GoalSpendingRepository goalSpendingRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;

    public GoaServiceImpl(GoalSavingRepository goalSavingRepository, GoalSpendingRepository goalSpendingRepository, UserRepository userRepository, CategoryRepository categoryRepository){
        this.goalSavingRepository = goalSavingRepository;
        this.goalSpendingRepository = goalSpendingRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GoalSavingResponse createSavingsGoal(GoalSavingCreateRequest request){
        return new GoalSavingResponse();
    }

    @Override
    public GoalSpendingResponse createSpendingGoal(GoalSpendingCreateRequest request){
        return new GoalSpendingResponse();
    }

    @Override
    public List<GoalSavingResponse> getAllSavingsGoalsByUser(Long userId){
        return new ArrayList<>();
    }

    @Override
    public List<GoalSpendingResponse> getAllSpendingGoalsByUser(Long userId){
        return new ArrayList<>();
    }

    @Override
    public GoalSavingResponse savingProgress(Long savingId){
        return new GoalSavingResponse();
    }

    @Override
    public GoalSpendingResponse spendingProgress(Long spendingId){
        return new GoalSpendingResponse();
    }
}
