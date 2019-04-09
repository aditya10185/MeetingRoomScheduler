package com.meetingroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.meetingroom.model.MeetingRoom;
import com.meetingroom.model.ScheduleMeetingRoom;

public interface ScheduleMeetingRepository extends CrudRepository<ScheduleMeetingRoom, Integer> {
	
}
