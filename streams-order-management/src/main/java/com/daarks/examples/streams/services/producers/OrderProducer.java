package com.daarks.examples.streams.services.producers;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import com.daarks.examples.streams.services.entities.Order;

@Component
public class OrderProducer {
	
	private static final String ORDER_TOPIC="orders";
	private static final String BOOTSTRAP_SERVER = "127.0.0.1:9092";
	private KafkaProducer<String, String> producer;
	private long timestamp;
	private long startTime;
	
	
	public KafkaProducer<String, String> start() {
		
		startTime = System.currentTimeMillis();
		// create producer properties

		Properties properties = new Properties();

		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		// create producer
		producer = new KafkaProducer<String, String>(properties);
		return producer;
	}
	
	public long push(Order order) {
		
		ProducerRecord<String,String> record = new ProducerRecord<String, String>(OrderProducer.ORDER_TOPIC,
				order.getId().toString(),order.getName());
	    producer.send(record, (RecordMetadata recordMetaData,Exception e) -> {
	    	timestamp = recordMetaData.timestamp();
	    });
	    
        System.out.println("Message processed by thread: "+
                Thread.currentThread().getName()
                +" at:" + ((System.currentTimeMillis() - startTime)/1000)
                +" second");
	    
	    return timestamp;
	}
}
