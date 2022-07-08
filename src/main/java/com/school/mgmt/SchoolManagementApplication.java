package com.school.mgmt;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.school.mgmt.domain.Role;
import com.school.mgmt.domain.User;
import com.school.mgmt.service.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class SchoolManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			
			if(userService.getUser("admin")==null) {
				
				userService.saveUser(new User(1,"admin","admin","admin", new ArrayList<>()));
				userService.saveUser(new User(2,"user","user","user", new ArrayList<>()));
				userService.saveRole(new Role(1,"ROLE_USER"));
				userService.saveRole(new Role(2,"ROLE_ADMIN"));
				
				userService.addRoleToUser("admin","ROLE_ADMIN");
			}
		};
	}
}
