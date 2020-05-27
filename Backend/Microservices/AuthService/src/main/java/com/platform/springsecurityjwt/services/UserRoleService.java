package com.platform.springsecurityjwt.services;


import com.platform.springsecurityjwt.models.UserRole;
import com.platform.springsecurityjwt.util.response.RoleResponse;
import com.platform.springsecurityjwt.util.response.UserResponse;

import java.util.List;

public interface UserRoleService {
    List<UserResponse> findByRoleId(Long roleId);
    List<RoleResponse> findByUserId(Long userId);
    void addRoleToUser(UserRole theUserRole);
    void deleteRole(Long userId,Long roleId);
}
