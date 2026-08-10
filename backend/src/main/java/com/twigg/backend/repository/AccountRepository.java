package com.twigg.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twigg.backend.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
