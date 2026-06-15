package com.ecommerce.modules.user.controller;

import com.ecommerce.common.response.ApiResponse;
import com.ecommerce.modules.user.dto.CreateUserRequest;
import com.ecommerce.modules.user.dto.UserResponseDto;
import com.ecommerce.modules.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ApiResponse<UserResponseDto> createUser(
            @Valid @RequestBody CreateUserRequest request) {

        return ApiResponse.<UserResponseDto>builder()
                .success(true)
                .message("User created successfully")
                .data(userService.createUser(request))
                .build();
    }
}