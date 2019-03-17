/**
 * 
 */
package com.meetingroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aditya10185
 *
 */

@RestController
@RequestMapping("/api/meeting")
@CrossOrigin(origins = "http://localhost:3000")
public class ScheduleMeetingController {
	
	@Autowired
	

}
