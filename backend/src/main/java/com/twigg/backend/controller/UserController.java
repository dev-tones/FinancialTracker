package com.twigg.backend.controller;

import com.twigg.backend.model.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/profile")
    public UserResponse getUserProfile() {
        return new UserResponse("Gemini Learner", "Full Stack Dev", 2);
    }
}
