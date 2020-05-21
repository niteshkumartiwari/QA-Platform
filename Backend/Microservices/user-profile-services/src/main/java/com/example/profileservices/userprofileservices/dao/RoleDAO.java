package com.example.profileservices.userprofileservices.dao;

import com.example.profileservices.userprofileservices.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role,Long> {
}
