package mate.academy.springbootproject.service;

import mate.academy.springbootproject.model.Role;

public interface RoleService {
    Role save(Role role);

    boolean exists(Role.RoleName roleName);
}
