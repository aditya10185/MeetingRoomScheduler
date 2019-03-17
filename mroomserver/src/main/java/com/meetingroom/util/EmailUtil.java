/**
 * 
 */
package com.meetingroom.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author aditya10185
 *
 */
@Component
public class EmailUtil implements IEmailUtil {
	
	Logger logger = LoggerFactory.getLogger(EmailUtil.class);

	/* (non-Javadoc)
	 * @see com.meetingroom.util.IEmailUtil#sendEmail(java.util.Map)
	 */
	@Override
	public void sendEmail(Map<String, Object> email) {
		// TODO Auto-generated method stub
		logger.info("Sending Email");
	}

}
