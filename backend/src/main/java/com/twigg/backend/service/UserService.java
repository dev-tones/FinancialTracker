package com.twigg.backend.service;
import java.util.List;
import com.twigg.backend.dto.UserResponse;
import com.twigg.backend.dto.CreateUserRequest;
import com.twigg.backend.dto.UpdateUserRequest;

public interface UserService {
    
    public UserResponse createUser(CreateUserRequest request);
    public List<UserResponse> getAllUsers(int page, int pageSize);
    public UserResponse findByEmail(String email);
    public boolean deleteUser(Long id);
    public UserResponse updateUser(Long id, UpdateUserRequest request);
}
