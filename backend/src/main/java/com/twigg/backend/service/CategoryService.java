package com.twigg.backend.service;

import java.util.List;
import com.twigg.backend.dto.CategoryCreateRequest;
import com.twigg.backend.dto.CategoryResponse;


public interface CategoryService {

    public CategoryResponse createCategory(CategoryCreateRequest request);
    public List<CategoryResponse> getAllCategories();
    public CategoryResponse getCategoryById(Long id);


}
