package com.devsuperior.hroauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.hroauth.entities.User;
import com.devsuperior.hroauth.feignclients.UserFeignClient;

@Service
public class UserService {
	
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserFeignClient feignClient;
	
	public User findByEmail(String email) {
		User user = feignClient.findByEmail(email).getBody();
		if (user == null) {
			log.error("Email not found: " + email);
			throw new IllegalArgumentException("Email not found");
		}
		log.info( "Email found: " + email);
		return user;
	}

}
