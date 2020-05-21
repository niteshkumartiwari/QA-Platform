package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.Id.UserRoleId;
import com.example.profileservices.userprofileservices.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleDAO extends JpaRepository<UserRole, UserRoleId> {
    List<UserRole> findByUserId(Long userId);
    List<UserRole> findByRoleId(Long roleId);
}
