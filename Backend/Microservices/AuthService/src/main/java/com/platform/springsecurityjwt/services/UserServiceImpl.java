package com.platform.springsecurityjwt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.platform.springsecurityjwt.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.platform.springsecurityjwt.dao.UserDAO;
import com.platform.springsecurityjwt.models.User;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User findUserByUsername(String username) throws Exception {
		return userDAO.getUserByUserName(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User theUser= findUserByUsername(username);
			return new org.springframework.security.core.userdetails.User(username,theUser.getPassword(),new ArrayList<>());
		}
		catch(Exception e) {
			throw new UsernameNotFoundException("User Not found");
		}
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

	@Override
	public User createUser(User theUser) {
		if(theUser.getPassword().isEmpty())
			throw new ApiRequestException("Password is Empty");

		theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
		User newUser=userDAO.save(theUser);
		return newUser;
	}

	@Override
	public List<User> getAllUser(List<Long> userIds) {
		List<User> userList= new ArrayList<>();
		for(Long userId: userIds){
			Optional result= userDAO.findById(userId);

			if(result.isPresent())
				userList.add((User)result.get());
		}

		return userList;
	}

}
