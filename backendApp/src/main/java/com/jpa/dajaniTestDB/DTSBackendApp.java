package com.jpa.dajaniTestDB;

import com.jpa.dajaniTestDB.entity.RoleEntity;
import com.jpa.dajaniTestDB.model.UserModel;
import com.jpa.dajaniTestDB.service.serviceInterface.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DTSBackendApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DTSBackendApp.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new RoleEntity(null, "USER"));
			userService.saveRole(new RoleEntity(null, "AGENT"));
			userService.saveRole(new RoleEntity(null, "ADMIN"));

			Set<RoleEntity> emptySet = new HashSet<>();
			userService.saveUser(new UserModel(null, "juan123", "something@email.com", "Juan",
					"Landeros", "root", emptySet, null, null));

			userService.saveUser(new UserModel(null,"John123","something1@email.com", "John",
					"Doe", "root", emptySet, null, null));

			userService.saveUser(new UserModel(null,"Jane123","something2@email.com", "Jane",
					"Doe", "root", emptySet, null, null));

			userService.addRoleToUser("juan123", "ADMIN");
			userService.addRoleToUser("John123", "AGENT");
			userService.addRoleToUser("Jane123", "USER");
		};
	}
}
