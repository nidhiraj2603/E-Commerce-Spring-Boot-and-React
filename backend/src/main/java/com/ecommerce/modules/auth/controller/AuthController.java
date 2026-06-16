package com.ecommerce.modules.auth.controller;

import com.ecommerce.modules.auth.dto.AuthResponse;
import com.ecommerce.modules.auth.dto.LoginRequest;
import com.ecommerce.modules.auth.dto.RefreshTokenRequest;
import com.ecommerce.modules.auth.dto.RegisterRequest;
import com.ecommerce.modules.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @PostMapping("/logout")
    public String logout(Authentication authentication) {

        authService.logout(authentication.getName());

        return "Logged out successfully";
    }

    @PostMapping("/refresh")
    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request) {

        return authService.refreshToken(request);
    }
}
