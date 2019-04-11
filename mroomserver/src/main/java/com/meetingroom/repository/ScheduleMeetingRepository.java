package com.meetingroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.meetingroom.model.MeetingRoom;
import com.meetingroom.model.ScheduleMeetingRoom;

public interface ScheduleMeetingRepository extends CrudRepository<ScheduleMeetingRoom, Integer> {
	
	@Query("SELECT smr "
			+ "FROM ScheduleMeetingRoom smr "
			+ "WHERE smr.hostId = :hostId")
	public List<ScheduleMeetingRoom> getMeetingsForHost(@Param("hostId") long hostId);
	
}
