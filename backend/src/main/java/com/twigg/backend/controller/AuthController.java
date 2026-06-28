package com.twigg.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.twigg.backend.dto.LoginRequest;
import com.twigg.backend.dto.UserCreateRequest;
import com.twigg.backend.dto.UserResponse;
import com.twigg.backend.service.AuthService;
import com.twigg.backend.service.UserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    public AuthController(AuthService authService, UserService userService){
        this.authService = authService;
        this.userService = userService;
    }
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String authenticate(@RequestBody LoginRequest loginRequest){
        return authService.authenticate(loginRequest.email(), loginRequest.password());
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody @Valid UserCreateRequest request) {
        UserResponse response = userService.createUser(request);
        return response;
    }
}
