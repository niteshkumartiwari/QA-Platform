package com.platform.springsecurityjwt.services;


import com.platform.springsecurityjwt.models.UserRole;
import com.platform.springsecurityjwt.util.response.RoleResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserRoleService {
    Page<UserRole> findByRoleId(Long roleId,int currentPage,int noOfElemPerPage);
    List<RoleResponse> findByUserId(Long userId);
    void addRoleToUser(UserRole theUserRole);
    void deleteRole(Long userId,Long roleId);
}
