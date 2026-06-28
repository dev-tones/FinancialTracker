package com.twigg.backend.security;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.twigg.backend.model.User;
import com.twigg.backend.repository.UserRepository;
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        User u = user.get();
        return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPasswordHash(), Collections.emptyList());
    }
}
