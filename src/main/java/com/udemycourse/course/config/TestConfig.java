package com.udemycourse.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.udemycourse.course.entities.User;
import com.udemycourse.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Sophie Ferreira", "sferreira@gmail.com", "123", "1234");
		User u2 = new User(null, "Priscila Ferreira", "pferreira@gmail.com", "321", "3215");
	
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
	
	
}
