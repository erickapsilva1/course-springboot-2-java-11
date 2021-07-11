package com.udemycourse.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.udemycourse.course.entities.Category;
import com.udemycourse.course.entities.Order;
import com.udemycourse.course.entities.User;
import com.udemycourse.course.entities.enums.OrderStatus;
import com.udemycourse.course.repositories.CategoryRepository;
import com.udemycourse.course.repositories.OrderRepository;
import com.udemycourse.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Sophie Ferreira", "sferreira@gmail.com", "123", "1234");
		User u2 = new User(null, "Priscila Ferreira", "pferreira@gmail.com", "321", "3215");
		
		Order o1 = new Order(null, Instant.parse("2021-07-20T19:00:00Z"), u1, OrderStatus.PAID);
		Order o2 = new Order(null, Instant.parse("2021-07-20T18:15:00Z"), u2, OrderStatus.WAITING_PAYMENT);
		Order o3 = new Order(null, Instant.parse("2021-07-20T19:10:00Z"), u1, OrderStatus.CANCELED);
		
		Category c1 = new Category(null, "Books");
		Category c2 = new Category(null, "Games");
		Category c3 = new Category(null, "Comics");
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
	}
	
	
}
