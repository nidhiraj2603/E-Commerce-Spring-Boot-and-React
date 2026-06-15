package com.ecommerce.modules.auth.jwt;

public interface JwtService {

    String generateToken(String email);
}