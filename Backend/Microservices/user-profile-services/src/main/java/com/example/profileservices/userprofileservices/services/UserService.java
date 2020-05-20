package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.models.User;

public interface UserService {
	public User findUserById(Long userId);
	public User findById(Long userId);
}
