package mate.academy.springbootproject.service.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootproject.dto.user.UserRegistrationRequestDto;
import mate.academy.springbootproject.dto.user.UserResponseDto;
import mate.academy.springbootproject.exception.RegistrationException;
import mate.academy.springbootproject.mapper.UserMapper;
import mate.academy.springbootproject.model.Role;
import mate.academy.springbootproject.model.User;
import mate.academy.springbootproject.repository.role.RoleRepository;
import mate.academy.springbootproject.repository.user.UserRepository;
import mate.academy.springbootproject.service.UserService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto registerUser(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        User user = getRawUser(requestDto);
        user.setRoles(Set.of(roleRepository.findRoleByRoleName(Role.RoleName.USER)));
        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto registerAdmin(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        User admin = getRawUser(requestDto);
        admin.setRoles(Set.of(roleRepository.findRoleByRoleName(Role.RoleName.USER),
                roleRepository.findRoleByRoleName(Role.RoleName.ADMIN)));
        return userMapper.toUserResponseDto(userRepository.save(admin));
    }

    private User getRawUser(UserRegistrationRequestDto requestDto) throws RegistrationException {
        if (userRepository.findUserByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration");
        }
        return userMapper.toModel(requestDto);
    }

    @Override
    public boolean exists(String email) {
        return userRepository.existsByEmail(email);
    }
}
