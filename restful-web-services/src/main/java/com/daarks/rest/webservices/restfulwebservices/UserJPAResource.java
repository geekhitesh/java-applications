package com.daarks.rest.webservices.restfulwebservices;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daarks.rest.webservices.restfulwebservices.posts.Post;
import com.daarks.rest.webservices.restfulwebservices.users.User;
import com.daarks.rest.webservices.restfulwebservices.users.UserDaoService;

@RestController
public class UserJPAResource {
	
	@Autowired
	private UserDaoService userDaoService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@GetMapping(path="/jpa/users")
	public List<User> findAll() {
		
		//return userDaoService.findAll();
		
		return userRepository.findAll();
	}
	
	@GetMapping(path="/jpa/users/{id}")
	public Optional<User> find(@PathVariable int id) {
		
		 Optional<User>  user = userRepository.findById(id);
		//return userDaoService.findById(id);
		 System.out.println(user);
		 return user;
	}
	
	@PostMapping(path="/jpa/users")
	public ResponseEntity<Object> create(@RequestBody User user) {
		
		//User savedUser = userDaoService.save(user);
		
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
				       .fromCurrentRequest()
				       .path("/{id}")
				       .buildAndExpand(savedUser.getId()).toUri();
		
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id ) {
		
		User user = userDaoService.deleteById(id);
		
		if(user == null) {
			
		}
	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> getPosts(@PathVariable int id) {

		Optional<User> user = userRepository.findById(id);
		
		List<Post> posts= user.get().getPosts();
		
		return posts;
		
		
	}
	
	
	@PostMapping(path="/jpa/user/{id}/post")
	public ResponseEntity<Object> create(@PathVariable int id, @RequestBody Post post) {
		
		//User savedUser = userDaoService.save(user);
		
		Optional<User> user = userRepository.findById(id);
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
				       .fromCurrentRequest()
				       .path("/{id}")
				       .buildAndExpand(savedPost.getId()).toUri();
		
		
		return ResponseEntity.created(location).build();
		
	}
	
	
}
