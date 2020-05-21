package com.platform.springsecurityjwt.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.platform.springsecurityjwt.dao.UserDAO;
import com.platform.springsecurityjwt.models.User;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	private UserDAO userDAO;
	
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
	
}
