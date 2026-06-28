package com.twigg.backend.service;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.twigg.backend.dto.UserCreateRequest;
import com.twigg.backend.dto.UserResponse;
import com.twigg.backend.dto.UserUpdateRequest;
import com.twigg.backend.model.User;
import com.twigg.backend.model.UserRole;
import com.twigg.backend.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public UserResponse createUser(UserCreateRequest request){
        User u = new User();
        u.setUserName(request.getUserName());
        u.setUserRole(UserRole.USER);
        u.setFirstName(request.getFirstName());
        u.setLastName(request.getLastName());
        u.setEmail(request.getEmail());
        u.setPhone(request.getPhone());
        u.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        userRepository.save(u);
        return mapToResponse(u);
    }

    public List<UserResponse> getAllUsers(int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<User> uPage = userRepository.findAll(pageable);
        return uPage.stream().map(this::mapToResponse).toList();
    }

    public UserResponse findByEmail(String email){
        User u = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException(email)); 
        return mapToResponse(u);
    }

    public boolean deleteUser(Long id){
        User u = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found."));
        userRepository.delete(u);
        return true;
    }

    public UserResponse updateUser(Long id, UserUpdateRequest request){
        User u = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found. "));
        u.setUserName(request.getUserName());
        u.setFirstName(request.getFirstName());
        u.setLastName(request.getLastName());
        u.setEmail(request.getEmail());
        u.setPhone(request.getPhone());
        if(request.getPassword() != null){
            u.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        }
        userRepository.save(u);
        return mapToResponse(u);
    }
    public List<UserResponse> findByUserRole(String userRole){
        List<User> u = userRepository.findByUserRole(userRole);
        List<UserResponse> ur = new ArrayList<>();
        for (User user : u) {
            ur.add(mapToResponse(user));
        }
        return ur;
    }

    public UserResponse mapToResponse(User user){
        return new UserResponse(
            user.getId(),
            user.getUserRole(),
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
