/**
 * 
 */
package com.meetingroom.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetingroom.controller.AuthController;
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
	
	private Logger logger = LoggerFactory.getLogger(AuthController.class);

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
		Map<String, Object> response = new HashMap<String, Object>();
		logger.info("Creating new room");
		mroomrepo.save(m);
		response.put("meetingRoom", m);
		response.put("message", "Successfully created");
		return response;
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

	@Override
	public Map<String, Object> getAvailableMeetingRooms(Timestamp startDate, Timestamp endDate, int capacity) {
		// TODO Auto-generated method stub
		Map<String, Object> response = new HashMap<>();
		List<MeetingRoom> meetingRooms = mroomrepo.getAvailableMeetingRooms(startDate, endDate, capacity);
		logger.info(meetingRooms.toString());
		response.put("meetingRooms", meetingRooms);
		response.put("message", "Ok");
		return response;
	}
}
