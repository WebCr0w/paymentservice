package com.example.paytickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PayticketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayticketsApplication.class, args);
	}

}
