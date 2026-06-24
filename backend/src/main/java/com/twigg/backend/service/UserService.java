package com.twigg.backend.service;
import java.util.List;
import com.twigg.backend.dto.UserResponse;
import com.twigg.backend.dto.UserCreateRequest;
import com.twigg.backend.dto.UserUpdateRequest;

public interface UserService {
    
    public UserResponse createUser(UserCreateRequest request);
    public List<UserResponse> getAllUsers(int page, int pageSize);
    public UserResponse findByEmail(String email);
    public boolean deleteUser(Long id);
    public UserResponse updateUser(Long id, UserUpdateRequest request);
    public List<UserResponse> findByUserRole(String userRole);
}
