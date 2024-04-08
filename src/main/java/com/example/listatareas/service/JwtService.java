package com.example.listatareas.service;

import com.example.listatareas.config.security.SecurityProperties;
import com.example.listatareas.models.User;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Autowired
    private SecurityProperties securityProperties;

    public String generateToken(User user, Map<String, Object> extraClaims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (securityProperties.getExpirationTime() * 60 * 1000));

        return Jwts.builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(generateKey())
                .compact();
    }

    private Key generateKey() {
        return Keys.hmacShaKeyFor(securityProperties.getSecretKey().getBytes());
    }
}
