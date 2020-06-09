package com.platform.springsecurityjwt.services;

import com.platform.springsecurityjwt.models.User;

import java.util.List;

public interface UserService {
	public User findUserByUsername(String username)throws Exception;
	public User findById(Long userId);
	public User createUser(User theUser);
	public List<User> getAllUser(List<Long> userIds);
}
