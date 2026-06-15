package com.ecommerce.modules.auth.service;

import com.ecommerce.modules.auth.dto.AuthResponse;
import com.ecommerce.modules.auth.dto.RegisterRequest;
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

    @Override
    public AuthResponse register(RegisterRequest request) {

        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .role(Role.CUSTOMER)
                .build();

        userRepository.save(user);

        String token =jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);    
    }
}