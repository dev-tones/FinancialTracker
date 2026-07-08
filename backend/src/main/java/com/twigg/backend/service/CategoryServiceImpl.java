package com.twigg.backend.service;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.twigg.backend.dto.CategoryCreateRequest;
import com.twigg.backend.dto.CategoryResponse;
import com.twigg.backend.model.Category;
import com.twigg.backend.model.User;
import com.twigg.backend.repository.CategoryRepository;
import com.twigg.backend.repository.UserRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, UserRepository userRepository){
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }
    @Override
    public CategoryResponse createCategory(CategoryCreateRequest request){
        Category category = new Category();
        category.setName(request.getName());
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new NoSuchElementException("User not found: " + email));
        category.setUser(user);
        Category saved = categoryRepository.save(category);
        return mapToCategory(saved);
    }
    @Override
    public List<Category> getCategoriesForCurrentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("User not found: " + email));
        List<Category> userCategories = categoryRepository.findAllCategoryByUser(user);
        return userCategories;
    }
    @Override
    public List<CategoryResponse> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> responses = new ArrayList<>();
        for(Category x : categories){
            responses.add(mapToCategory(x));
        }
        return responses;
    }
    @Override
    public CategoryResponse getCategoryById(Long categoryId){
        Category categoryById = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new RuntimeException("Category not found"));
        return mapToCategory(categoryById);
    }

    private CategoryResponse mapToCategory(Category category){
        return new CategoryResponse(
            category.getId(),
            category.getName(),
            category.getUser()

        );
    }

}
