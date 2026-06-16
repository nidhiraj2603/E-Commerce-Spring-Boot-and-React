package com.ecommerce.modules.auth.service;

import com.ecommerce.modules.auth.dto.AuthResponse;
import com.ecommerce.modules.auth.dto.LoginRequest;
import com.ecommerce.modules.auth.dto.RefreshTokenRequest;
import com.ecommerce.modules.auth.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse refreshToken(RefreshTokenRequest request);

    void logout(String email);

}