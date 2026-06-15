package com.ecommerce.modules.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.modules.auth.jwt.JwtService;

@RestController
public class TestController {

    private final JwtService jwtService;

    public TestController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/token")
    public String generateToken(@RequestParam String email) {
        return jwtService.generateToken(email);
    }
    @GetMapping("/extract")
    public String extract(@RequestParam String token) {
        return jwtService.extractUsername(token);
    }
    @GetMapping("/validate")
    public boolean validate(@RequestParam String token) {
        return jwtService.isTokenValid(token);
    }
}