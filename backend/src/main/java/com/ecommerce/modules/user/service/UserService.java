package com.ecommerce.modules.user.service;

import com.ecommerce.modules.user.dto.CreateUserRequest;
import com.ecommerce.modules.user.dto.UserResponseDto;

public interface UserService {

    UserResponseDto createUser(CreateUserRequest request);

    UserResponseDto getCurrentUser(String email);
}