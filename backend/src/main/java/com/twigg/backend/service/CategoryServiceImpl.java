package com.twigg.backend.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.twigg.backend.dto.CategoryResponse;
import com.twigg.backend.dto.CreateCategoryRequest;
import com.twigg.backend.model.Category;
import com.twigg.backend.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public CategoryResponse createCategory(CreateCategoryRequest request){
        Category category = new Category();
        category.setName(request.getName());
        category.setType(request.getType());
        Category saved = categoryRepository.save(category);
        return mapToCategory(saved);
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

    private CategoryResponse mapToCategory(Category category){
        return new CategoryResponse(
            category.getId(),
            category.getName(),
            category.getType()
        );
    }

}
