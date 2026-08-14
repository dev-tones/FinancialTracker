package com.twigg.backend.service;

import com.twigg.backend.dto.AccountCreateRequest;
import com.twigg.backend.dto.AccountResponse;

public interface AccountService {
    public AccountResponse createAccount(AccountCreateRequest request);
    
} 
