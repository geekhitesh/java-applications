package com.daarks.apache.producers;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerWithKeysAndCallback {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub

		final Logger logger = LoggerFactory.getLogger(ProducerWithKeysAndCallback.class);

		String bootstrapServer = "127.0.0.1:9092";

		// create producer properties

		Properties properties = new Properties();

		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		// create producer

		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

		// send data

		for (Integer i = 0; i < 999; i++) {
			
			String message = "Message_"+Integer.toString(i);
			String key = "key_"+Integer.toString(i);
			
			logger.info("Key:"+key);
			
			ProducerRecord<String, String> record = new ProducerRecord<String,String>("AUDIT_TOPIC",key ,message);

			producer.send(record, new Callback() {

				public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//					// TODO Auto-generated method stub
//					logger.info("Received new metadata\n" + "Topic: " + recordMetadata.topic() + "\n" + "Partion:"
//							+ recordMetadata.partition() + "\n" + "Offset:" + recordMetadata.offset() + "\n");
					// TODO Auto-generated method stub
					logger.info("Partion:"+ recordMetadata.partition());
				}
			}).get();

		}
		
		producer.flush();
		producer.close();
	}

}
