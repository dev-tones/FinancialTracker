package com.twigg.backend.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.twigg.backend.security.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
private final JwtAuthFilter jwtAuthFilter;
public SecurityConfig(JwtAuthFilter jwtAuthFilter){
    this.jwtAuthFilter = jwtAuthFilter;
}
    // Inside your SecurityConfig class
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        // ... other configurations
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ).authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/login").permitAll().requestMatchers("/auth/register").permitAll() // 
            .anyRequest().authenticated()
        ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        ;
    return http.csrf(csrf -> csrf.disable()).build();
    }
    /*
    Password encoder bean (uses BCrypt hashing)
    Critical for secure password storage
    */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
