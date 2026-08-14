package com.twigg.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twigg.backend.model.Account;
import com.twigg.backend.model.User;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllAccountByUser(User user);
}
