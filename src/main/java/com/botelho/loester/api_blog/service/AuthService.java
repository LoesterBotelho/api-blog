package com.botelho.loester.api_blog.service;

import com.botelho.loester.api_blog.dto.request.LoginRequest;
import com.botelho.loester.api_blog.dto.request.RefreshTokenRequest;
import com.botelho.loester.api_blog.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse refreshToken(RefreshTokenRequest request);
}
