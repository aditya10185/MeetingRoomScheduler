package com.meetingroom.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.meetingroom.model.MeetingRoom;

@Component
public interface IMeetingRoomService {
	
	public List<MeetingRoom> getAllMeetingRooms();
	
	public MeetingRoom getMeetingRoomByName(String name);
	
	public MeetingRoom getMeetingRoomById(int id);
	
	public Map<String, Object> createMeetingRoom(MeetingRoom m);
	
	public MeetingRoom deleteMeetingRoom(int id);
	
	public Map<String, Object> updateMeetingRoom(MeetingRoom m);
	
	public Map<String, Object> getAvailableMeetingRooms(Timestamp startDate, Timestamp endDate, int capacity);

}
