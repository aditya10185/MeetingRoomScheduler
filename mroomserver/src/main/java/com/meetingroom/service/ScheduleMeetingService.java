/**
 * 
 */
package com.meetingroom.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetingroom.model.ScheduleMeetingRoom;
import com.meetingroom.repository.ScheduleMeetingRepository;
import com.meetingroom.util.IEmailUtil;

/**
 * @author aditya10185
 *
 */
@Service
public class ScheduleMeetingService implements IScheduleMeetingService {
	
	@Autowired
	private ScheduleMeetingRepository meetingRepository;
	
	@Autowired
	private IEmailUtil emailUtil;

	@Override
	public List<ScheduleMeetingRoom> getAllScheduledMeetings() {
		// TODO Auto-generated method stub
		List<ScheduleMeetingRoom> meetings = new ArrayList<>();
		meetingRepository.findAll().forEach(meetings::add);
		return meetings;
	}

	@Override
	public Map<String, Object> createNewMeeting(List<ScheduleMeetingRoom> meetings) {
		// TODO Auto-generated method stub
		meetingRepository.saveAll(meetings);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", "Meeting Scheduled");
		Map<String, String> email = new HashMap<String, String>();
		email.put("name", "MRS on behalf of - "+ meetings.get(0).getHostEmail());
		email.put("email", meetings.get(0).getHostEmail());
		String attendees = "";
		for(ScheduleMeetingRoom m : meetings) {
			attendees = m.getAttendee() + "," + attendees;
		}
		email.put("to", attendees);
		email.put("subject", "Scheduled Meeting for - "+ meetings.get(0).getMeetingStartDate() + " at "+ meetings.get(0).getMeetingLocation());
		email.put("message", "New meeting scheduled with " + meetings.get(0).getHostEmail());
		emailUtil.sendEmail(email);
		return response;
	}

	@Override
	public List<ScheduleMeetingRoom> getScheduledMeetingsForUser(long userId) {
		return meetingRepository.getMeetingsForHost(userId);
	}

	@Override
	public ScheduleMeetingRoom deleteMeeting(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
