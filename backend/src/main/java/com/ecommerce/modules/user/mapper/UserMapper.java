package com.ecommerce.modules.user.mapper;

import com.ecommerce.modules.user.dto.UserResponseDto;
import com.ecommerce.modules.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(User user);
}