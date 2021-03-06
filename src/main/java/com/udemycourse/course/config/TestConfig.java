package com.udemycourse.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.udemycourse.course.entities.Category;
import com.udemycourse.course.entities.Order;
import com.udemycourse.course.entities.OrderItem;
import com.udemycourse.course.entities.Payment;
import com.udemycourse.course.entities.Product;
import com.udemycourse.course.entities.User;
import com.udemycourse.course.entities.enums.OrderStatus;
import com.udemycourse.course.repositories.CategoryRepository;
import com.udemycourse.course.repositories.OrderItemRepository;
import com.udemycourse.course.repositories.OrderRepository;
import com.udemycourse.course.repositories.ProductRepository;
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
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

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
		//Category c4 = new Category(null, "Eletronics");
		//Category c5 = new Category(null, "Notebooks");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
	
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p4.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p1.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2021-07-20T21:00:00Z"), o1);
		o1.setPayment(pay1);
		
		orderRepository.save(o1);
		
	}
	
	
}
