package com.botelho.loester.api_blog.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.botelho.loester.api_blog.config.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final SecretKey secretKey;

    public JwtTokenProvider(JwtProperties jwtProperties) {

        this.jwtProperties = jwtProperties;

        String secret = jwtProperties.getSecret();

        this.secretKey = Keys.hmacShaKeyFor(
            secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    // ==================================================
    // ACCESS TOKEN
    // ==================================================

    public String createToken(String username, String role) {

        Date now = new Date();

        Date expiry = new Date(
            now.getTime() + jwtProperties.getExpirationMs()
        );

        return Jwts.builder()
            .subject(username)
            .claims(Map.of("role", role))
            .issuedAt(now)
            .expiration(expiry)
            .signWith(secretKey, Jwts.SIG.HS256)
            .compact();
    }

    // ==================================================
    // REFRESH TOKEN
    // ==================================================

    public String createRefreshToken(String username) {

        Date now = new Date();

        Date expiry = new Date(
            now.getTime() + jwtProperties.getRefreshExpirationMs()
        );

        return Jwts.builder()
            .subject(username)
            .issuedAt(now)
            .expiration(expiry)
            .signWith(secretKey, Jwts.SIG.HS256)
            .compact();
    }

    // ==================================================
    // VALIDATE TOKEN
    // ==================================================

    public boolean validateToken(String token) {

        if (token == null || token.isBlank()) {
            return false;
        }

        try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);

            return true;

        } catch (
            ExpiredJwtException |
            UnsupportedJwtException |
            MalformedJwtException |
            IllegalArgumentException e
        ) {
            return false;

        } catch (JwtException e) {
            return false;
        }
    }

    // ==================================================
    // GET USERNAME
    // ==================================================

    public String getUsername(String token) {

        Claims claims = Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();

        return claims.getSubject();
    }

    // ==================================================
    // RESOLVE TOKEN
    // ==================================================

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
}