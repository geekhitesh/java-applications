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

public class ProducerCallback {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		String topic="my-first-topic";
		String message;
		final Logger logger = LoggerFactory.getLogger(ProducerCallback.class);
		
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		// create producer
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		
		for(Integer i=0;i<20;i++) {
			
			message = "message_"+i.toString();
			logger.info("Message:"+message);
			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, message);
			producer.send(record, new Callback() {
				
				public void onCompletion(RecordMetadata recordMetaData, Exception arg1) {
					logger.info("\nTopic:"+recordMetaData.topic() +"\n"+
							    "Partition:"+recordMetaData.partition()+ "\n"+
							    "Offset:"+recordMetaData.offset());
					
				}
			}).get();
			
		}



		producer.close();
	}

}
