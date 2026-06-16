package com.ecommerce.modules.auth.service;

import com.ecommerce.modules.auth.entity.RefreshToken;
import com.ecommerce.modules.auth.repository.RefreshTokenRepository;
import com.ecommerce.modules.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private static final long REFRESH_EXPIRATION = 7 * 24 * 60 * 60;
    private final RefreshTokenRepository repository;

    @Override
    public RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = RefreshToken.builder().user(user).token(UUID.randomUUID().toString()).expiryDate(Instant.now().plusSeconds(REFRESH_EXPIRATION)).build();

        return repository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyExpiration(String token) {

        RefreshToken refreshToken = repository.findByToken(token).orElseThrow(() -> new RuntimeException("Refresh token not found"));

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {

            repository.delete(refreshToken);

            throw new RuntimeException("Refresh token expired");
        }

        return refreshToken;
    }
}