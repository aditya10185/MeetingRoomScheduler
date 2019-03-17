package com.meetingroom.util;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public interface IEmailUtil {
	
	public void sendEmail(Map<String, Object> email);
	

}
