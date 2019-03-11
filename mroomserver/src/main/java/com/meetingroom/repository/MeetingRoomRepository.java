/**
 * 
 */
package com.meetingroom.repository;

import org.springframework.data.repository.CrudRepository;

import com.meetingroom.model.MeetingRoom;

/**
 * @author aditya10185
 *
 */
public interface MeetingRoomRepository extends CrudRepository<MeetingRoom, Integer> {
	
}
