package mate.academy.springbootproject.security;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootproject.dto.user.UserLoginRequestDto;
import mate.academy.springbootproject.dto.user.UserLoginResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserLoginResponseDto authenticate(UserLoginRequestDto requestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getEmail(), requestDto.getPassword())
        );
        return new UserLoginResponseDto(jwtUtil.generateToken(authentication.getName()));
    }
}
