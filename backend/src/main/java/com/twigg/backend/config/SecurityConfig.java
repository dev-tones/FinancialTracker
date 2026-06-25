package com.twigg.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Inside your SecurityConfig class
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        // ... other configurations
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/v1/transactions", "/api/v1/auth/welcome", "/api/v1/auth/createUser", "/api/v1/auth/generateToken").permitAll() // <--- Add this temporarily
            .anyRequest().authenticated()
        );
    return http.build();
}
    /*
    Password encoder bean (uses BCrypt hashing)
    Critical for secure password storage
    */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
