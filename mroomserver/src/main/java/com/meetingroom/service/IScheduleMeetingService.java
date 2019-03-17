package com.meetingroom.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.meetingroom.model.ScheduleMeetingRoom;

@Component
public interface IScheduleMeetingService {
	
	public List<ScheduleMeetingRoom> getAllScheduledMeetings();
	
	public Map<String, Object> createNewMeeting(List<ScheduleMeetingRoom> meetings);
	
	public List<ScheduleMeetingRoom> getScheduledMeetingsForUser(long userId);
	
	public ScheduleMeetingRoom deleteMeeting(long id);
}
