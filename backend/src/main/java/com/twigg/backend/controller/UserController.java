package com.twigg.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.twigg.backend.dto.UserCreateRequest;
import com.twigg.backend.dto.UserResponse;
import com.twigg.backend.dto.UserUpdateRequest;
import com.twigg.backend.service.UserService;

import jakarta.validation.Valid;





@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    } 
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody @Valid UserCreateRequest request) {
        UserResponse response = userService.createUser(request);
        return response;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUser(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int pageSize) {
        List<UserResponse> response = userService.getAllUsers(page, pageSize);
        return response;
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findByEmail(@RequestParam String email){
        UserResponse response = userService.findByEmail(email);
        return response;
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        UserResponse response = userService.updateUser(id, request);
        return response;
    }
}   