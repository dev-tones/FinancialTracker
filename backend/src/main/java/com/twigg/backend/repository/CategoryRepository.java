package com.twigg.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twigg.backend.model.Category;
import com.twigg.backend.model.User;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllCategoryByUser(User user);
}
