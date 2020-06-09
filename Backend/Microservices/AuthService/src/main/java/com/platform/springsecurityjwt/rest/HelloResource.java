package com.platform.springsecurityjwt.rest;

import com.platform.springsecurityjwt.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.platform.springsecurityjwt.models.AuthenticationRequest;
import com.platform.springsecurityjwt.models.AuthenticationResponse;
import com.platform.springsecurityjwt.models.User;
import com.platform.springsecurityjwt.services.UserService;
import com.platform.springsecurityjwt.services.UserServiceImpl;
import com.platform.springsecurityjwt.util.JwtUtil;

import java.util.List;

@RestController
@RequestMapping("/")
public class HelloResource {
	
	@Autowired
	private AuthenticationManager theAuthenticationManager;
	
	@Autowired
	private UserDetailsService userService;

	@Autowired
	private UserService theUserService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws Exception{
		try {
			theAuthenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch(BadCredentialsException e) {
			throw new Exception("Incorrect user or password ",e);
		}
		
		final UserDetails userDetails=userService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt= jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@PostMapping("/register")
	public User createUser(@RequestBody User theUser){
		try{
			return theUserService.createUser(theUser);
		}
		catch(Exception e) {
			throw new ApiRequestException(e.getMessage());
		}
	}

	@GetMapping("api/user/{id}")
	public User getUser(@PathVariable Long id){
		try{
			return theUserService.findById(id);
		}
		catch(Exception e) {
			throw new ApiRequestException(e.getMessage());
		}
	}

	@GetMapping("api/users/{userIds}")
	public List<User> getAllUsers(@PathVariable List<Long> userIds){
		try{
			return theUserService.getAllUser(userIds);
		}
		catch(Exception e) {
			throw new ApiRequestException(e.getMessage());
		}
	}

}
