package com.meetingroom.mroomserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.meetingroom.controller", "com.meetingroom.model", "com.meetingroom.repository", "com.meetingroom.util"})
@EntityScan("com.meetingroom.model")
@EnableJpaRepositories("com.meetingroom.repository")
public class MroomserverApplication {


	public static void main(String[] args) {
		SpringApplication.run(MroomserverApplication.class, args);
	}

}

