package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();
    Role findById(Long roleId);
    void addRole(Role role);
    void updateRole(Role role);
    void deleteRole(Long roleId);
}
