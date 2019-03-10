package com.meetingroom.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.meetingroom.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Query("SELECT u.id, u.firstName, u.lastName, u.email, u.contactNo FROM User u WHERE LOWER(u.email) = LOWER(:email)")
	public User findUserByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
	public User findUserByEmailWithPassword(@Param("email") String email);
}
