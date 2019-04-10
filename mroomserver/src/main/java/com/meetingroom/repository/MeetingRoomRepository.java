/**
 * 
 */
package com.meetingroom.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.meetingroom.model.MeetingRoom;

/**
 * @author aditya10185
 *
 */
public interface MeetingRoomRepository extends CrudRepository<MeetingRoom, Integer> {
	
	@Query("SELECT DISTINCT m "
			+ "FROM MeetingRoom m LEFT JOIN ScheduleMeetingRoom smr ON smr.meetingRoomId = m.id "
			+ "WHERE (smr.meetingStatus <> 'completed' "
			+ "and ((smr.meetingStartDate > :endDate) "
			+ "or (smr.meetingEndDate < :startDate)) "
			+ "and m.capacity >= :capacity) "
			+ "or ( smr.meetingId is null and m.capacity >= :capacity)")
	public List<MeetingRoom> getAvailableMeetingRooms(@Param("startDate") Timestamp startDateTime, @Param("endDate") Timestamp endDateTime, @Param("capacity") int capacity); 
	
}
