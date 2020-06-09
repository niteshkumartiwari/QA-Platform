package com.platform.springsecurityjwt.services;


import com.platform.springsecurityjwt.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();
    Role findById(Long roleId);
    void addRole(Role role);
    void updateRole(Role role);
    void deleteRole(Long roleId);
}
