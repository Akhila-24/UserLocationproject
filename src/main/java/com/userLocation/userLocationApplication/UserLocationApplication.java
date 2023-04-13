package com.userLocation.userLocationApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class UserLocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserLocationApplication.class, args);
	}

}
