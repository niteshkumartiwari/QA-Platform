package com.platform.springsecurityjwt.dao;


import com.platform.springsecurityjwt.models.UserRole;
import com.platform.springsecurityjwt.models.id.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleDAO extends JpaRepository<UserRole, UserRoleId> {
    List<UserRole> findByUserId(Long userId);
    List<UserRole> findByRoleId(Long roleId);
}
