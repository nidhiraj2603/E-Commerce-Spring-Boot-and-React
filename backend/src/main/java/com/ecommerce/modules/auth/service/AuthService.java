package com.ecommerce.modules.auth.service;

import com.ecommerce.modules.auth.dto.AuthResponse;
import com.ecommerce.modules.auth.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

}