package com.daarks.rest.webservices.restfulwebservices;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daarks.rest.webservices.restfulwebservices.users.User;
import com.daarks.rest.webservices.restfulwebservices.users.UserDaoService;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userDaoService;

	@GetMapping(path="/users/all")
	public List<User> findAll() {
		
		return userDaoService.findAll();
	}
	
	@GetMapping(path="/users/{id}")
	public User find(@PathVariable int id) {
		
		return userDaoService.findById(id);
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<Object> create(@RequestBody User user) {
		
		User savedUser = userDaoService.save(user);
		
		URI location = ServletUriComponentsBuilder
				       .fromCurrentRequest()
				       .path("/{id}")
				       .buildAndExpand(savedUser.getId()).toUri();
		
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id ) {
		
		User user = userDaoService.deleteById(id);
		
		if(user == null) {
			
		}
	}
	

	
	
}
