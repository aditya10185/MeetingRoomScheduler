/**
 * 
 */
package com.meetingroom.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetingroom.model.MeetingRoom;
import com.meetingroom.repository.MeetingRoomRepository;

/**
 * @author aditya10185
 *
 */
@Service
public class MeetingRoomService implements IMeetingRoomService {
	
	@Autowired
	private MeetingRoomRepository mroomrepo;

	/* (non-Javadoc)
	 * @see com.meetingroom.service.IMeetingRoomService#getAllMeetingRooms()
	 */
	@Override
	public List<MeetingRoom> getAllMeetingRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.meetingroom.service.IMeetingRoomService#getMeetingRoomByName(java.lang.String)
	 */
	@Override
	public MeetingRoom getMeetingRoomByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.meetingroom.service.IMeetingRoomService#getMeetingRoomById(int)
	 */
	@Override
	public MeetingRoom getMeetingRoomById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.meetingroom.service.IMeetingRoomService#createMeetingRoom(com.meetingroom.model.MeetingRoom)
	 */
	@Override
	public Map<String, Object> createMeetingRoom(MeetingRoom m) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.meetingroom.service.IMeetingRoomService#deleteMeetingRoom(int)
	 */
	@Override
	public MeetingRoom deleteMeetingRoom(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.meetingroom.service.IMeetingRoomService#updateMeetingRoom(com.meetingroom.model.MeetingRoom)
	 */
	@Override
	public Map<String, Object> updateMeetingRoom(MeetingRoom m) {
		// TODO Auto-generated method stub
		return null;
	}

}
