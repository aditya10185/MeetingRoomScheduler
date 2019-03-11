package com.meetingroom.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.meetingroom.model.User;

@Component
public interface IUserService {
	
	public List<User> getAllUsers();
	
	public Map<String, Object> createUser(User user) throws DataIntegrityViolationException;

}
