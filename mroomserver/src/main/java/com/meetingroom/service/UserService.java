package com.meetingroom.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.meetingroom.controller.UserController;
import com.meetingroom.model.User;
import com.meetingroom.repository.UserRepository;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<User>();
		logger.info("Finding all users");
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public Map<String, Object> createUser(User user) throws DataIntegrityViolationException {
		Map<String, Object> response = new HashMap<>();
		logger.info("Creating new user");
		userRepository.save(user);	
		response.put("Success", true);
		response.put("User", user.toString());
		return response;
	}
}
