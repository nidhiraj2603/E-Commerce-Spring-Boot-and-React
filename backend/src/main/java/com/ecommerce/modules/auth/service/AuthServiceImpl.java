package com.ecommerce.modules.auth.service;

import com.ecommerce.modules.auth.dto.AuthResponse;
import com.ecommerce.modules.auth.dto.LoginRequest;
import com.ecommerce.modules.auth.dto.RefreshTokenRequest;
import com.ecommerce.modules.auth.dto.RegisterRequest;
import com.ecommerce.modules.auth.entity.RefreshToken;
import com.ecommerce.modules.auth.jwt.JwtService;
import com.ecommerce.modules.user.entity.Role;
import com.ecommerce.modules.user.entity.User;
import com.ecommerce.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.CUSTOMER).build();

        userRepository.save(user);

        String accessToken = jwtService.generateToken(user.getEmail());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return AuthResponse.builder().accessToken(accessToken).refreshToken(refreshToken.getToken()).build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Invalid email or password"));

        boolean isPasswordValid = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!isPasswordValid) {
            throw new RuntimeException("Invalid email or password");
        }

        String accessToken = jwtService.generateToken(user.getEmail());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return AuthResponse.builder().accessToken(accessToken).refreshToken(refreshToken.getToken()).build();
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {

        RefreshToken refreshToken = refreshTokenService.verifyExpiration(request.getRefreshToken());

        String accessToken = jwtService.generateToken(refreshToken.getUser().getEmail());

        return AuthResponse.builder().accessToken(accessToken).refreshToken(refreshToken.getToken()).build();
    }
}