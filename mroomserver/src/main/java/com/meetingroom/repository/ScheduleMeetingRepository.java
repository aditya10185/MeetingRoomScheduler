package com.meetingroom.repository;

import org.springframework.data.repository.CrudRepository;

import com.meetingroom.model.ScheduleMeetingRoom;

public interface ScheduleMeetingRepository extends CrudRepository<ScheduleMeetingRoom, Integer> {
	
}
