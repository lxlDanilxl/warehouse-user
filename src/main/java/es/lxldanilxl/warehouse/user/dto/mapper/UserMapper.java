package es.lxldanilxl.warehouse.user.dto.mapper;

import es.lxldanilxl.warehouse.user.dto.RegisterDto;
import es.lxldanilxl.warehouse.user.model.User;

public final class UserMapper {

    private UserMapper() {
    }

    public static User registerDtoToUserMapper(RegisterDto registerDto) {
        return User.builder()
                .username(registerDto.getUsername())
                .password(registerDto.getPassword())
                .email(registerDto.getEmail())
                .build();
    }
}
