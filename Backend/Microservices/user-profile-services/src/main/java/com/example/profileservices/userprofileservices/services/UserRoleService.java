package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.UserRole;
import com.example.profileservices.userprofileservices.util.response.RoleResponse;
import com.example.profileservices.userprofileservices.util.response.UserResponse;

import java.util.List;

public interface UserRoleService {
    List<UserResponse> findByRoleId(Long roleId);
    List<RoleResponse> findByUserId(Long userId);
    void addRoleToUser(UserRole theUserRole);
    void deleteRole(Long userId,Long roleId);
}
