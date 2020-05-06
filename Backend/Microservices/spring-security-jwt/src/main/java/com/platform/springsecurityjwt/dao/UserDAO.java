package com.platform.springsecurityjwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.platform.springsecurityjwt.models.User;

public interface UserDAO extends JpaRepository<User, Long>{
	@Query("select u from User u where username = ?1")
	public User getUserByUserName(String username);
}
