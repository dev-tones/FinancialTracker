package com.twigg.backend.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.twigg.backend.model.User;
import java.util.List;



public interface UserRepository extends JpaRepository <User, Long> {
    
    Optional<User> findByEmail(String email);

    List<User> findByUserRole(String userRole);
}