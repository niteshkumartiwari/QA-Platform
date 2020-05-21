package com.platform.springsecurityjwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.platform.springsecurityjwt.dao.UserDAO;
import com.platform.springsecurityjwt.models.User;

@SpringBootApplication
public class SpringSecurityJwtApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}
}
