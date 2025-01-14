package com.chatterly.automation_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AutomationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomationServiceApplication.class, args);
	}

}
