package com.twigg.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.twigg.backend.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
