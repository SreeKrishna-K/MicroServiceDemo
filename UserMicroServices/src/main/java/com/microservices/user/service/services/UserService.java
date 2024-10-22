package com.microservices.user.service.services;

import java.util.List;

import com.microservices.user.service.entities.User;

public interface UserService {
	User saveUser(User user);
	
	List<User> getAllUser();
	
	User getUser(String userId);
	
	// to do delete and update 
}
