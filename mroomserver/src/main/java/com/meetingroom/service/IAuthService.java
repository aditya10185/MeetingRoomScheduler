package com.meetingroom.service;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public interface IAuthService {
	public Map<String, Object> userLogin(String email,String password);
}
