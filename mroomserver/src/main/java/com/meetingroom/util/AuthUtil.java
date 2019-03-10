package com.meetingroom.util;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.meetingroom.model.User;
import com.meetingroom.repository.UserRepository;

@Component
public class AuthUtil {
	@Value("${app.secret}")
	private String secret;
	
	@Autowired
	private UserRepository userRepository;
	
	private Logger logger = LoggerFactory.getLogger(AuthUtil.class);
	
	
	public String createToken(User user) {
		logger.info(secret);
		Algorithm algorithmHS = Algorithm.HMAC256(secret);
		try {
			Date currentDate = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			c.add(Calendar.DATE, 1);
			String token = JWT.create()
								.withClaim("userId", user.getId())
								.withClaim("userEmail", user.getEmail())
								.withIssuer("MeetingRoom")
								.withExpiresAt(c.getTime())
								.sign(algorithmHS);
			return token;
		} catch(JWTCreationException jce) {
			logger.debug(jce.getMessage());
			return null;
		}
	}
	
	public User verifyAuthToken(String token) {
		Algorithm algorithmHS = Algorithm.HMAC256(secret);
		try {
			JWTVerifier verifier = JWT.require(algorithmHS)
										.withIssuer("MeetingRoom")
										.acceptExpiresAt(5)
										.build();
			DecodedJWT jwt = verifier.verify(token);
			logger.info(jwt.getClaim("userEmail").toString());
			return userRepository.findUserByEmail(jwt.getClaim("userEmail").toString());
		} catch(JWTVerificationException exception) {
			logger.debug(exception.getMessage());
			return null;
		}
	}
}
