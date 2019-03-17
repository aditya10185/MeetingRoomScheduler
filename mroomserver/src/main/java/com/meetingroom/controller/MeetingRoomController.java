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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.meetingroom.model.MeetingRoom;
import com.meetingroom.service.IMeetingRoomService;
import com.meetingroom.util.IAuthUtil;

@RestController
@RequestMapping("/api/meeting-room")
@CrossOrigin(origins = "http://localhost:3000")
public class MeetingRoomController {
	@Autowired
	private IMeetingRoomService meetingRoomService;
	
	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private IAuthUtil authUtil;
	
	
	@PostMapping
	public @ResponseBody ResponseEntity<Map<String, Object>> createNewMeetingRoom(@RequestHeader("authorization") String jwt, @RequestBody Map<String, Object> payload) {
		try {
			logger.info(jwt);
			if(authUtil.verifyAuthToken(jwt)) {
				String name = payload.get("name").toString();
				String location = payload.get("location").toString();
				int capacity = Integer.parseInt(payload.get("capacity").toString());
				MeetingRoom m = new MeetingRoom(name, location, capacity);
				Map<String, Object> response = meetingRoomService.createMeetingRoom(m);
				return new ResponseEntity<Map<String, Object>> (response, HttpStatus.ACCEPTED);
			}
			Map<String, Object> error = new HashMap<String, Object>();
			error.put("error", "Sorry, you are not authorized to create a new meeting room.");
			return new ResponseEntity<Map<String, Object>> (error, HttpStatus.UNAUTHORIZED);
		} catch(JWTVerificationException jve) {
			logger.info(jve.getMessage());
			Map<String, Object> error = new HashMap<String, Object>();
			error.put("error", "Sorry, you are not authorized to create a new meeting room.");
			return new ResponseEntity<Map<String, Object>> (error, HttpStatus.UNAUTHORIZED);
		} catch(Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			Map<String, Object> error = new HashMap<String, Object>();
			error.put("error", "Something went wrong, try again.");
			return new ResponseEntity<Map<String, Object>> (error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
