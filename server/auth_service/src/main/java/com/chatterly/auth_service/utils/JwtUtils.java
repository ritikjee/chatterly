package com.chatterly.auth_service.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.chatterly.auth_service.entity.User;
import com.chatterly.auth_service.repo.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtUtils {

    private String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret";
    private final UserRepository userRepository;

    public JwtUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(io.jsonwebtoken.security.Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private String getSessionId(String token) {
        return extractAllClaims(token).get("sid", String.class);
    }

    public String getUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        User user = userRepository.findByEmail(username);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)
                && getSessionId(token).equals(user.getSessionId()));
    }

    public String generateToken(UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername());

        Map<String, Object> claims = new HashMap<>();

        claims.put("sid", user.getSessionId());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

}
