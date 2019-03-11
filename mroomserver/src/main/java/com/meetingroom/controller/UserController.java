package com.meetingroom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.meetingroom.model.User;
import com.meetingroom.service.IUserService;
import com.meetingroom.util.Passwords;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
		public static final Logger logger = LoggerFactory.getLogger(UserController.class);
		
		@RequestMapping
		public @ResponseBody ResponseEntity<List<User>> getAllUsers() {
			long startTime = System.currentTimeMillis();
			List<User> users = userService.getAllUsers();
			long endTime = System.currentTimeMillis();
			logger.info("Got all users in " + (endTime - startTime) + " ms" );
			return (users != null) ? new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.ACCEPTED)
									: new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
		
		@PostMapping
		public @ResponseBody ResponseEntity<Map<String, Object>> createNewUser(@RequestBody Map<String, Object> payload){
			long startTime = System.currentTimeMillis();
			try {
				String firstName = payload.get("firstName").toString();
				String lastName = payload.get("lastName").toString();
				String password = Passwords.hashPassword(payload.get("password").toString());
				String contactNo = payload.get("contactNo").toString();
				String email = payload.get("email").toString();
				User newUser = new User(firstName, lastName, email, contactNo, password);
				Map<String, Object> response = userService.createUser(newUser);
				long endTime = System.currentTimeMillis();
				long timeElapased = endTime - startTime;
				logger.info("Completed creating new user in " + timeElapased + "ms");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
			} catch(DataIntegrityViolationException dive) {
				dive.printStackTrace();
				logger.debug(dive.getMessage());
				Map<String, Object> error = new HashMap<>();
				error.put("error", "An user with the same email address already exists.");
				long endTime = System.currentTimeMillis();
				long timeElapased = endTime - startTime;
				logger.info("Failed creating new user in " + timeElapased + "ms");
				return new ResponseEntity<Map<String, Object>>(error, HttpStatus.CONFLICT);
			} catch(Exception e) {
				e.printStackTrace();
				logger.debug(e.getMessage());
				Map<String, Object> error = new HashMap<>();
				error.put("error", "User Creation Failed, Try again.");
				long endTime = System.currentTimeMillis();
				long timeElapased = endTime - startTime;
				logger.info("Failed creating new user in " + timeElapased + "ms");
				return new ResponseEntity<Map<String, Object>>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}	
}
