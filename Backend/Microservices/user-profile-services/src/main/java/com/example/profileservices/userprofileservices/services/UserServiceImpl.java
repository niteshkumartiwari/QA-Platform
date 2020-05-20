package com.example.profileservices.userprofileservices.services;

import com.example.profileservices.userprofileservices.exception.ApiRequestException;
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

	@Override
	public User findById(Long userId) {
		try{
			return userDAO.findById(userId).get();
		}
		catch (Exception e){
			throw new ApiRequestException("UserId Not Found.");
		}
	}

}
