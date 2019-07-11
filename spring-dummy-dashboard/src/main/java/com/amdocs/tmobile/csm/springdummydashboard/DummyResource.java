package com.amdocs.tmobile.csm.springdummydashboard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyResource {

	
	@GetMapping("/welcome")
	public String hello() {
		
		return "Hello DTU";
	}
	
}
