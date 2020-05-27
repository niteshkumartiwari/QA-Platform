package com.platform.springsecurityjwt.services;

import com.platform.springsecurityjwt.models.User;

public interface UserService {
	public User findUserByUsername(String username)throws Exception;
	public User findById(Long userId);
}
