package com.ecommerce.modules.user.mapper;

import com.ecommerce.modules.user.dto.UserResponseDto;
import com.ecommerce.modules.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-15T21:25:05+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.17 (Homebrew)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( user.getId() );
        userResponseDto.setFirstName( user.getFirstName() );
        userResponseDto.setLastName( user.getLastName() );
        userResponseDto.setEmail( user.getEmail() );
        userResponseDto.setRole( user.getRole() );

        return userResponseDto;
    }
}
