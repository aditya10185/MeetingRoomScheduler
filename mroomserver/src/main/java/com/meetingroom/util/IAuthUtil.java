package com.meetingroom.util;

import org.springframework.stereotype.Component;

import com.meetingroom.model.User;

@Component
public interface IAuthUtil {
	
	public String createToken(User user);
	
	public User verifyAuthToken(String token);
}
