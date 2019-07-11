package com.daarks.examples.streams.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import com.daarks.examples.streams.services.connectors.GreetServer;
import com.daarks.examples.streams.services.producers.OrderProducer;

@SpringBootApplication
public class StreamsOrderManagementApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(StreamsOrderManagementApplication.class, args);
		OrderProducer orderProducer = context.getBean(OrderProducer.class);
		orderProducer.start();	
		GreetServer greetServer = context.getBean(GreetServer.class);
		greetServer.start(9999);
	}

}

