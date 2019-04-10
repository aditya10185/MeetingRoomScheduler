package com.meetingroom.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@CrossOrigin(origins = "*")
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
	
	@PostMapping("/available")
	public @ResponseBody ResponseEntity<Map<String, Object>> getAvailableMeetingRooms(@RequestHeader("authorization") String jwt, @RequestBody Map<String, Object> payload) {
		try {
			if(authUtil.verifyAuthToken(jwt)) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy hh:mm");
				java.util.Date startDate = formatter.parse(payload.get("meetingStartDate").toString());
				java.util.Date endDate = formatter.parse(payload.get("meetingEndDate").toString());
				int capacity = Integer.parseInt(payload.get("capacity").toString());
				long difference = endDate.getTime() - startDate.getTime();
				logger.info(String.valueOf(difference));
				if(difference <= 14400000) {
					Timestamp meetingStartDate = new Timestamp(startDate.getTime());
					Timestamp meetingEndDate = new Timestamp(endDate.getTime());
					Map<String, Object> response = meetingRoomService.getAvailableMeetingRooms(meetingStartDate, meetingEndDate, capacity);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
				} else {
					Map<String, Object> error = new HashMap<String, Object>();
					error.put("error", "Cannot schedule a meeting room for more than 4 hours");
					return new ResponseEntity<Map<String, Object>> (error, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				Map<String, Object> error = new HashMap<String, Object>();
				error.put("error", "Sorry, you are not authorized to create a new meeting room.");
				return new ResponseEntity<Map<String, Object>> (error, HttpStatus.UNAUTHORIZED);
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			Map<String, Object> error = new HashMap<String, Object>();
			error.put("error", "Something went wrong, try again.");
			return new ResponseEntity<Map<String, Object>> (error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
