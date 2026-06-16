package com.ecommerce.modules.auth.service;

import com.ecommerce.modules.auth.entity.RefreshToken;
import com.ecommerce.modules.user.entity.User;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);

    RefreshToken verifyExpiration(String token);

    void deleteByUserId(Long userId);
}