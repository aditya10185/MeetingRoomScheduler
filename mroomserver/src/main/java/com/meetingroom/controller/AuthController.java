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

import com.meetingroom.service.IAuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	
	@Autowired
	private IAuthService authService;
	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	
	@PostMapping("/login")
	public @ResponseBody ResponseEntity<Map<String, Object>> userLogin(@RequestBody Map<String, Object> payload) {
		long startTime = System.currentTimeMillis();
		try {
			String email = payload.get("email").toString();
			String password = payload.get("password").toString();
			Map<String, Object> response = authService.userLogin(email, password);
			if(!response.containsKey("error")) {
				long endTime = System.currentTimeMillis();
				long timeElapased = endTime - startTime;
				logger.info("Completed logging in user in " + timeElapased + "ms");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.ACCEPTED);
			} else {
				long endTime = System.currentTimeMillis();
				long timeElapased = endTime - startTime;
				logger.info("Failed logging user in " + timeElapased + "ms");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
