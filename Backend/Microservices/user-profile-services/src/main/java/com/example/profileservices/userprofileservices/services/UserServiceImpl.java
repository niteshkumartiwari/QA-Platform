package com.example.profileservices.userprofileservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.profileservices.userprofileservices.dao.UserDAO;
import com.example.profileservices.userprofileservices.models.User;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public User findUserById(Long userId) {
		return userDAO.getOne(userId);
	}
	
}
