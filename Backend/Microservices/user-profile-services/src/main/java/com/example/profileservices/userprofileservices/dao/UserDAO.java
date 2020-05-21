package com.example.profileservices.userprofileservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.profileservices.userprofileservices.models.User;
import org.springframework.stereotype.Repository;

public interface UserDAO extends JpaRepository<User, Long>{

}
