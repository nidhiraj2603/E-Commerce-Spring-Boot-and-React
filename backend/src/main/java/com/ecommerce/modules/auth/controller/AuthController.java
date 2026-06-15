package com.ecommerce.modules.auth.controller;

import com.ecommerce.modules.auth.dto.AuthResponse;
import com.ecommerce.modules.auth.dto.LoginRequest;
import com.ecommerce.modules.auth.dto.RegisterRequest;
import com.ecommerce.modules.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }
    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {
    
        return authService.login(request);
    }
}
