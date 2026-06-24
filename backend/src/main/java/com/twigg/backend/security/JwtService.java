package com.twigg.backend.security;


import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private long expiration;
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
    return Keys.hmacShaKeyFor(keyBytes);
}

    public String generateToken(String email){
        String jwt = Jwts.builder()
        .setSubject(email)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSigningKey())
        .compact();
        return jwt;
    }
    public String extractEmail(String token){
        Key key = getSigningKey();
        Claims claims = Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String token){
        Key key = getSigningKey();
        try {
            Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("❌ Token expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("❌ Unsupported JWT: " + e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("❌ Malformed JWT: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("❌ Token is null or empty: " + e.getMessage());
        }
        return false;  
    }
}
