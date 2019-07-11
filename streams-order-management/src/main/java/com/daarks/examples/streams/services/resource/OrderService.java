package com.daarks.examples.streams.services.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.daarks.examples.streams.services.entities.Order;
import com.daarks.examples.streams.services.producers.OrderProducer;

@RestController
public class OrderService {
	
	@Autowired
	private OrderProducer orderProducer;

	@PostMapping(path="/order/create")
	public long postOrder(@RequestBody Order order) {
		
		
		return orderProducer.push(order);
	}
	
	
	@GetMapping("/order/get")
	public Order get() {
		
		return new Order();
	}
	
}
