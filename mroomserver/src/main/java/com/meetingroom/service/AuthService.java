package com.meetingroom.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetingroom.model.User;
import com.meetingroom.repository.UserRepository;
import com.meetingroom.util.AuthUtil;
import com.meetingroom.util.Passwords;

@Service
public class AuthService implements IAuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	private Logger logger = LoggerFactory.getLogger(AuthUtil.class);
	
	@Autowired
	private AuthUtil auth;
	
	public Map<String, Object> userLogin(String email,String password) {
		User user = userRepository.findUserByEmailWithPassword(email);  
		logger.info("Attempting login");
		if(Passwords.verifyPassword(user.getPassword(), password)) {
			logger.info("Pasword matched");
			String token = auth.createToken(user);
	 		Map<String, Object> response = new HashMap<>();
	 		response.put("token", token);
	 		ObjectMapper objMap = new ObjectMapper();
	 		Map<?, ?> userMap = objMap.convertValue(user, Map.class);
	 		userMap.remove("password");
	 		response.put("user", userMap);
	 		return response;
		} else {
			Map<String, Object> response = new HashMap<>();
			response.put("error", "Invalid email/password, try again.");
			return response;
		}
	}
	

}
