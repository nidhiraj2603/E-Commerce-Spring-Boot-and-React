package com.ecommerce.modules.user.dto;

import com.ecommerce.modules.user.entity.Role;
import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;
}