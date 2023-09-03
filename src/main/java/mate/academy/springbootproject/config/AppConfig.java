package mate.academy.springbootproject.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootproject.dto.user.UserRegistrationRequestDto;
import mate.academy.springbootproject.exception.RegistrationException;
import mate.academy.springbootproject.model.Role;
import mate.academy.springbootproject.service.RoleService;
import mate.academy.springbootproject.service.UserService;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class AppConfig {
    private final RoleService roleService;
    private final UserService userService;

    @PostConstruct
    public void init() throws RegistrationException {
        Role.RoleName[] values = Role.RoleName.values();
        for (Role.RoleName roleName: Role.RoleName.values()) {
            if (!roleService.exists(roleName)) {
                Role role = new Role();
                role.setRoleName(roleName);
                roleService.save(role);
            }
        }

        if (!userService.exists("admin@gmail.com")) {
            UserRegistrationRequestDto admin = new UserRegistrationRequestDto();
            admin.setEmail("admin@gmail.com");
            admin.setPassword("12345678");
            admin.setRepeatPassword("12345678");
            admin.setFirstName("Serhii");
            admin.setLastName("Pankov");
            admin.setShippingAddress("Kharkiv, North Saltivka");
            userService.registerAdmin(admin);
        }
    }
}
