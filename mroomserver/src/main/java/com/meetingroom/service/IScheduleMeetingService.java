package com.meetingroom.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.meetingroom.model.ScheduleMeetingRoom;

@Component
public interface IScheduleMeetingService {
	
	public List<ScheduleMeetingRoom> getAllScheduledMeetings();
	
	public Map<String, Object> createNewMeeting(long userId, List<String> attendees, ScheduleMeetingRoom meeting);
	
	public List<ScheduleMeetingRoom> getScheduledMeetingsForUser(long userId);
	
	public ScheduleMeetingRoom deleteMeeting(long id);
}
