package com.daarks.apache.producers;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerCallback {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final Logger logger = LoggerFactory.getLogger(ProducerCallback.class);

		String bootstrapServer = "127.0.0.1:9092";

		// create producer properties

		Properties properties = new Properties();

		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		// create producer

		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

		// send data

		for (Integer i = 0; i < 10; i++) {
			String message = "Message_"+Integer.toString(i);
			ProducerRecord<String, String> record = new ProducerRecord<String, String>("second_topic", message);

			producer.send(record, new Callback() {

				public void onCompletion(RecordMetadata recordMetadata, Exception e) {
					// TODO Auto-generated method stub
					logger.info("Received new metadata\n" + "Topic: " + recordMetadata.topic() + "\n" + "Partion:"
							+ recordMetadata.partition() + "\n" + "Offset:" + recordMetadata.offset());

				}
			});

		}
		
		producer.flush();
		producer.close();
	}

}
