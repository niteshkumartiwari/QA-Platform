package com.platform.springsecurityjwt.dao;

import com.platform.springsecurityjwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role,Long> {
}
