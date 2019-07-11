package com.daarks.examples.streams.services.consumers;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {
	
	private KafkaConsumer<String,String> consumer;
	private final String ORDERS_TOPIC = "orders";	
	
	public KafkaConsumer<String,String> start() {
		
		// Create Configurations
		String bootstrapServer = "127.0.0.1:9092";
		String groupId = "orders-consumer";
		Properties properties = new Properties();
		
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		consumer = new KafkaConsumer<String, String>(properties);	
		return consumer;
		
	}
	
	
}
