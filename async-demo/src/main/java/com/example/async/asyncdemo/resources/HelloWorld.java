package com.example.async.asyncdemo.resources;

import java.util.concurrent.Future;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HelloWorld.class);
	
	@GetMapping("/greet")
	@Async("threadPoolTaskExecutor")
	public Future<String> greet() {
	
		logger.info("Called Async started"+Thread.currentThread().getName());
		try {
			Thread.sleep(3000);
			
			return new AsyncResult<String>("hello world !!!!");
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
		logger.info("Called Async Ended"+Thread.currentThread().getName());
		return null;
	}
}
