package com.twigg.backend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


public interface JwtService {

    @Value("${jwt.secret}")
    String JWT_SECRET;
    @Value("${jwt.expiration}")
    String EXPIRATION;

    public String generateToken(String email);
    

}
