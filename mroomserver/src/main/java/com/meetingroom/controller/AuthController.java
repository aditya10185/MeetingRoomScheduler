package com.meetingroom.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.meetingroom.model.User;
import com.meetingroom.repository.UserRepository;
import com.meetingroom.util.AuthUtil;
import com.meetingroom.util.Passwords;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	private Logger logger = LoggerFactory.getLogger(AuthUtil.class);
	
	@Autowired
	private AuthUtil auth;
	
	@PostMapping("/login")
	public @ResponseBody ResponseEntity<Map<String, Object>> userLogin(@RequestBody Map<String, Object> payload) {
		long startTime = System.currentTimeMillis();
		try {
			String email = payload.get("email").toString();
			String password = payload.get("password").toString();
			User user = userRepository.findUserByEmailWithPassword(email);
			if(Passwords.verifyPassword(user.getPassword(), password)) {
				String token = auth.createToken(user);
				Map<String, Object> response = new HashMap<String, Object>();
				response.put("accessToken", token);
				response.put("user", user.toString());
				long endTime = System.currentTimeMillis();
				long timeElapased = endTime - startTime;
				logger.info("Completed logging in user in " + timeElapased + "ms");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.ACCEPTED);
			} else {
				Map<String, Object> error = new HashMap<String, Object>();
				error.put("error", "Invalid password, try again.");
				long endTime = System.currentTimeMillis();
				long timeElapased = endTime - startTime;
				logger.info("Failed logging user in " + timeElapased + "ms");
				return new ResponseEntity<Map<String, Object>>(error, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
			long endTime = System.currentTimeMillis();
			long timeElapased = endTime - startTime;
			logger.info("Failed logging user in " + timeElapased + "ms");
			Map<String, Object> error = new HashMap<String, Object>();
			error.put("error", "Something went wrong, try again.");
			return new ResponseEntity<Map<String, Object>>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
