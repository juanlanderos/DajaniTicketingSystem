package com.jpa.dajaniTestDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringLoadEntitiesApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringLoadEntitiesApplication.class, args);
	}

/*
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringLoadEntitiesApplication.class);
	}
*/


}
