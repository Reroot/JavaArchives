package com.team.alpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class AirlineBookingServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(AirlineBookingServiceApplication.class, args);		
	}	

}
