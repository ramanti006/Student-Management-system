package com.example.Student.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import jakarta.annotation.PostConstruct;

@Profile("dev")
@Configuration
public class DBconfig {
	
	@PostConstruct
	public void test()
	{
		System.out.println("dev profile loaded");
		
	}

}
