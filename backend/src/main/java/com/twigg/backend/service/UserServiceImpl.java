package com.twigg.backend.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.twigg.backend.dto.CreateUserRequest;
import com.twigg.backend.dto.UserResponse;
import com.twigg.backend.model.User;
import com.twigg.backend.repository.UserRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserServiceImpl {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public UserResponse createUser(CreateUserRequest request){
        User u = new User();
        u.setUserName(request.getUserName());
        u.setFirstName(request.getFirstName());
        u.setLastName(request.getLastName());
        u.setEmail(request.getEmail());
        u.setPhone(request.getPhone());
        u.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        userRepository.save(u);
        return mapToResponse(u);
    }

    public List<UserResponse> getAllUsers(int page, int pageSize){
        List<User> u = new ArrayList<>();
        List<UserResponse> allUsers = new ArrayList<>(); 
        
    }

    public UserResponse mapToResponse(User user){
        return new UserResponse(
            user.getId(),
            user.getUserName(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPhone(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    } 
}
