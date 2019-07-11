import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerDemoWithThreads {

	public static void main(String args[]) {
		
		ConsumerDemoWithThreads threads = new ConsumerDemoWithThreads();
		threads.run();

	}
	
	public void run() {
		
		Logger logger = LoggerFactory.getLogger(ConsumerDemoWithThreads.class);
		// Create Configurations

		String groupId = "my-fourth-application";
		String topic = "my-fourth-topic";
		
		logger.info("Application started");
		
		CountDownLatch latch = new CountDownLatch(1);
		Runnable myConsumerThread = new ConsumerThread(latch,groupId,topic);
		
		Thread t1 = new Thread( myConsumerThread);
		
		t1.start();
		
		Runtime.getRuntime().addShutdownHook(new Thread( new Runnable() {
			
			@Override
			public void run() {
				logger.info("shutdown Requested");
				
			}
		}));
		
		try {
			logger.info("waiting for finishing the work");
			latch.await();
		} catch (InterruptedException e) {
			logger.info("Application got interrupted");
		}finally {
			logger.info("Application closing");
		}
	}

	public class ConsumerThread implements Runnable {

		private CountDownLatch countDownLatch;
		private KafkaConsumer<String, String> consumer;
		private String topic;
		private String groupId;
		Logger logger = LoggerFactory.getLogger(ConsumerThread.class);
		String bootstrapServer = "127.0.0.1:9092";

		public ConsumerThread(CountDownLatch countDownLatch, String groupId, String topic) {
			this.countDownLatch = countDownLatch;
			this.groupId = groupId;
			this.topic = topic;
		}

		public void run() {

			Properties properties = new Properties();

			properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
			properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
			properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
			properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
			properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

			// create consumer
			consumer = new KafkaConsumer<String, String>(properties);

			// subscribe consumer to our topics

			consumer.subscribe(Arrays.asList(topic));

			// poll for new data

			try {

				while (true) {
					ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

					for (ConsumerRecord<String, String> record : records) {
						logger.info("Key:" + record.key() + ", Value:" + record.value());
						logger.info("Partition:" + record.partition() + ", Offset:" + record.offset());
					}
				}

			} catch (WakeupException e) {
				logger.info("wakeup exception");
			}finally {
				consumer.close();
				countDownLatch.countDown();
			}

		}

		public void shutDown() {
			consumer.wakeup();
			
		}

	}
}
