package com.platform.springsecurityjwt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platform.springsecurityjwt.models.AuthenticationRequest;
import com.platform.springsecurityjwt.models.AuthenticationResponse;
import com.platform.springsecurityjwt.services.MyUserDetailsService;
import com.platform.springsecurityjwt.util.JwtUtil;

@RestController
@RequestMapping("/")
public class HelloResource {
	
	@Autowired
	private AuthenticationManager theAuthenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@RequestMapping("/hello")
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
		
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt= jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
