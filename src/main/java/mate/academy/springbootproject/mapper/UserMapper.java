package mate.academy.springbootproject.mapper;

import mate.academy.springbootproject.config.MapperConfig;
import mate.academy.springbootproject.dto.user.UserRegistrationRequestDto;
import mate.academy.springbootproject.dto.user.UserResponseDto;
import mate.academy.springbootproject.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(config = MapperConfig.class, componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class UserMapper {
    protected PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "password",
            expression = "java(passwordEncoder.encode(requestDto.getPassword()))")
    public abstract User toModel(UserRegistrationRequestDto requestDto);

    public abstract UserResponseDto toUserResponseDto(User user);
}
