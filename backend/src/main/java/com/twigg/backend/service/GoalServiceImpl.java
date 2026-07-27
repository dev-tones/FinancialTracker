package com.twigg.backend.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.twigg.backend.dto.GoalSavingCreateRequest;
import com.twigg.backend.dto.GoalSavingProgressResponse;
import com.twigg.backend.dto.GoalSavingResponse;
import com.twigg.backend.dto.GoalSpendingCreateRequest;
import com.twigg.backend.dto.GoalSpendingProgressResponse;
import com.twigg.backend.dto.GoalSpendingResponse;
import com.twigg.backend.model.Category;
import com.twigg.backend.model.GoalSave;
import com.twigg.backend.model.GoalSpend;
import com.twigg.backend.model.Transaction;
import com.twigg.backend.model.User;
import com.twigg.backend.repository.CategoryRepository;
import com.twigg.backend.repository.GoalSavingRepository;
import com.twigg.backend.repository.GoalSpendingRepository;
import com.twigg.backend.repository.TransactionRepository;
import com.twigg.backend.repository.UserRepository;

@Service
public class GoalServiceImpl implements GoalService {

    private GoalSavingRepository goalSavingRepository;
    private GoalSpendingRepository goalSpendingRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private TransactionRepository transactionRepository;

    public GoalServiceImpl(GoalSavingRepository goalSavingRepository, GoalSpendingRepository goalSpendingRepository, UserRepository userRepository, CategoryRepository categoryRepository, TransactionRepository transactionRepository){
        this.goalSavingRepository = goalSavingRepository;
        this.goalSpendingRepository = goalSpendingRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public GoalSpendingResponse mapToSpendResponse(GoalSpend request){
        return new GoalSpendingResponse(
            request.getCategory(),
            request.getUser(),
            request.getTargetAmount(),
            request.getStartDate(),
            request.getEndDate(),
            request.getCreatedDate(),
            request.getUpdatedDate()
        );     
    }

    public GoalSavingResponse mapToSaveResponse(GoalSave request){

        return new GoalSavingResponse(
            request.getUser(),
            request.getName(),
            request.getTargetAmount(),
            request.getCurrentAmount(),
            request.getTargetDate()
        );
    }

    @Override
    public GoalSavingResponse createSavingsGoal(GoalSavingCreateRequest request){

        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new NoSuchElementException("User not found: " + request.getUserId()));

        GoalSave goal = new GoalSave();
        goal.setUser(user);
        goal.setName(request.getName());
        goal.setTargetAmount(request.getTargetAmount());
        goal.setCurrentAmount(request.getCurrentAmount());
        goal.setTargetDate(request.getTargetDate());

        goalSavingRepository.save(goal);

        return mapToSaveResponse(goal);
    }

    @Override
    public GoalSpendingResponse createSpendingGoal(GoalSpendingCreateRequest request){

        Category category = categoryRepository.findById(request.getCategoryId()).
        orElseThrow(() -> new NoSuchElementException("Category not found: " + request.getCategoryId()));
        // String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new NoSuchElementException("User not found: " + request.getUserId()));

        GoalSpend goal = new GoalSpend();
        goal.setCategory(category);
        goal.setUser(user);
        goal.setTargetAmount(request.getTargetAmount());
        goal.setStartDate(request.getStartDate());
        goal.setEndDate(request.getEndDate());
        goalSpendingRepository.save(goal);

        return mapToSpendResponse(goal);
    }

    @Override
    public List<GoalSavingResponse> getAllSavingsGoalsByUser(Long userId){

        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found: " + userId));
        List<GoalSavingResponse> gresponse = new ArrayList<>();
        List<GoalSave> g = goalSavingRepository.findByUser(user);
        for (GoalSave goalSave : g) {
            gresponse.add(mapToSaveResponse(goalSave));
        }

        return gresponse;
    }

    @Override
    public List<GoalSpendingResponse> getAllSpendingGoalsByUser(Long userId){
        List<GoalSpendingResponse> gresponse = new ArrayList<>();
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found: " + userId));
        List<GoalSpend> g = goalSpendingRepository.findByUser(user);
        for (GoalSpend goalSpend : g) {
            gresponse.add(mapToSpendResponse(goalSpend));
        }
        return gresponse;
    }

    @Override
    public GoalSavingProgressResponse savingProgress(GoalSave goal){
        GoalSavingProgressResponse progressResponse = new GoalSavingProgressResponse();

        BigDecimal amountRemaining = goal.getTargetAmount();
        amountRemaining = amountRemaining.subtract(goal.getCurrentAmount());
        final BigDecimal ONEHUNDRED = BigDecimal.valueOf(100.00);
        progressResponse.setAmountRemaining(amountRemaining);
        progressResponse.setName(goal.getName());
        progressResponse.setTargetAmount(goal.getTargetAmount());
        progressResponse.setAmountSaved(goal.getCurrentAmount());
        BigDecimal percentComplete;
        if (goal.getTargetAmount().compareTo(BigDecimal.ZERO) == 0) {
            percentComplete = BigDecimal.ZERO; // or however you want to represent "undefined"
        } else {
            percentComplete = goal.getCurrentAmount()
                .divide(goal.getTargetAmount(), 4, RoundingMode.HALF_UP)
                .multiply(ONEHUNDRED);
        }
        progressResponse.setPercentComplete(percentComplete);
        long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), goal.getTargetDate());
        progressResponse.setDaysRemaining(daysRemaining);
        return progressResponse;
    }

    @Override
    public GoalSpendingProgressResponse spendingProgress(GoalSpend goal){

        final BigDecimal ONEHUNDRED = BigDecimal.valueOf(100.00);
        GoalSpendingProgressResponse progress = new GoalSpendingProgressResponse();

        Category category = goal.getCategory();

        List<Transaction> tx = transactionRepository.findByUserAndCategoryAndDateBetween(goal.getUser(), category, goal.getStartDate(), goal.getEndDate());
        BigDecimal amountSpent = BigDecimal.ZERO;
        for (Transaction transaction : tx) {
            amountSpent = amountSpent.add(transaction.getAmount());
        }
        progress.setAmountSpent(amountSpent);
        progress.setTargetAmount(goal.getTargetAmount());
        progress.setAmountRemaining(goal.getTargetAmount().subtract(amountSpent));
        progress.setPercentageUsed(amountSpent.divide(goal.getTargetAmount(), 2, RoundingMode.HALF_UP).multiply(ONEHUNDRED));
        long daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), goal.getEndDate());
        progress.setDaysRemaining(daysRemaining);
        return progress;
    }
    
}
