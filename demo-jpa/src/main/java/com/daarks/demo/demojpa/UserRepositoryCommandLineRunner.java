package com.daarks.demo.demojpa;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.daarks.demo.demojpa.entity.User;
import com.daarks.demo.demojpa.service.UserRepository;

@Component
public class UserRepositoryCommandLineRunner  implements CommandLineRunner {

	
	private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);
	
	
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		User user = new User("Hitesh","Supervisor");
		userRepository.save(user);
		
		log.info("New User Created: "+user);
		
		Optional<User> findById = userRepository.findById(1L);
		
		log.info("User is created: "+findById);
		
		List<User>users = userRepository.findAll();
		
		System.out.println(users);
	}
	
}
