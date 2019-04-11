/**
 * 
 */
package com.meetingroom.util;

import java.util.List;
import java.util.Map;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
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
	public void sendEmail(Map<String, String> email) {
		// TODO Auto-generated method stub
		logger.info("Sending Email");
		Email resp = EmailBuilder.startingBlank()
				.from(email.get("name").toString(), email.get("email").toString())
				.toWithDefaultName("Invitees", email.get("to").toString())
				.withSubject(email.get("subject").toString())
				.withPlainText(email.get("message").toString())
				.buildEmail();
				
		MailerBuilder
				.withSMTPServer("smtpout.asia.secureserver.net", 465, "mailer@coconutale.com", "Admin123$")
				.withTransportStrategy(TransportStrategy.SMTPS)
				.buildMailer()
				.sendMail(resp);
	}

}
