package mate.academy.springbootproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootproject.dto.user.UserLoginRequestDto;
import mate.academy.springbootproject.dto.user.UserLoginResponseDto;
import mate.academy.springbootproject.dto.user.UserRegistrationRequestDto;
import mate.academy.springbootproject.dto.user.UserResponseDto;
import mate.academy.springbootproject.exception.RegistrationException;
import mate.academy.springbootproject.security.AuthenticationService;
import mate.academy.springbootproject.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.registerUser(requestDto);
    }
}
