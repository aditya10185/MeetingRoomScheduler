/**
 * 
 */
package com.meetingroom.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetingroom.model.ScheduleMeetingRoom;
import com.meetingroom.service.IScheduleMeetingService;
import com.meetingroom.util.IAuthUtil;

/**
 * @author aditya10185
 *
 */

@RestController
@RequestMapping("/api/meeting")
@CrossOrigin(origins = "*")
public class ScheduleMeetingController {
	
	@Autowired
	private IScheduleMeetingService meeting;
	
	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private IAuthUtil authUtil;
	
	@PostMapping
	public @ResponseBody ResponseEntity<Map<String, Object>> createNewMeeting(@RequestHeader("authorization") String jwt, @RequestBody Map<String, Object> payload) {
		try {
			if(authUtil.verifyAuthToken(jwt)) {
				List<ScheduleMeetingRoom> meetings = new ArrayList<>();
				String uniqueId = UUID.randomUUID().toString();
				long hostId = Long.parseLong(payload.get("hostId").toString());
				String hostEmail = payload.get("hostEmail").toString();
				List<String> attendees = (List<String>)payload.get("attendees");
				SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy hh:mm");
				java.util.Date startDate = formatter.parse(payload.get("meetingStartDate").toString());
				java.util.Date endDate = formatter.parse(payload.get("meetingEndDate").toString());
				logger.info(startDate.toString());
				long difference = endDate.getTime() - startDate.getTime();
				if(difference >= 14400000) {
//				Sql date conversion
				Timestamp meetingStartDate = new Timestamp(startDate.getTime());
				Timestamp meetingEndDate = new Timestamp(endDate.getTime());
				String meetingStatus = "scheduled";
				String meetingLocation = payload.get("meetingLocation").toString();
				long meetingRoomId = Long.parseLong(payload.get("meetingRoomId").toString());
				for(String attendee : attendees) {
					ScheduleMeetingRoom meeting = new ScheduleMeetingRoom(uniqueId, hostId, hostEmail, attendee, meetingStartDate, meetingEndDate, meetingStatus, meetingLocation, meetingRoomId);
					meetings.add(meeting);
				}
				Map<String, Object> response = meeting.createNewMeeting(meetings);
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
