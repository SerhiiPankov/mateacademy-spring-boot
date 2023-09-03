package mate.academy.springbootproject.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.springbootproject.model.Role;
import mate.academy.springbootproject.repository.role.RoleRepository;
import mate.academy.springbootproject.service.RoleService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public boolean exists(Role.RoleName roleName) {
        return roleRepository.existsByRoleName(roleName);
    }
}
