package mate.academy.springbootproject.service;

import mate.academy.springbootproject.dto.user.UserRegistrationRequestDto;
import mate.academy.springbootproject.dto.user.UserResponseDto;
import mate.academy.springbootproject.exception.RegistrationException;

public interface UserService {
    UserResponseDto registerUser(UserRegistrationRequestDto requestDto)
            throws RegistrationException;

    UserResponseDto registerAdmin(UserRegistrationRequestDto requestDto)
            throws RegistrationException;

    boolean exists(String email);
}
