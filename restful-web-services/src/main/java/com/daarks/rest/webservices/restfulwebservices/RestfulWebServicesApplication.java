package com.daarks.rest.webservices.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context =  SpringApplication.run(RestfulWebServicesApplication.class, args);
		GreetClient greetClient = context.getBean(GreetClient.class);
		greetClient.startConnection("127.0.0.1", 9999);
		
		while(true) {
			System.out.println("Sending Message");
		 greetClient.sendMessage("Hello World");
		 Thread.sleep(4000);
		}
	}
}
