package com.daarks.examples.streams.services.validation;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;


public class OrderStreams {
	
	  /*private StreamsBuilder createOrdersMaterializedView() {
		    StreamsBuilder builder = new StreamsBuilder();
		    builder.table("orders", Materialized.as("orders-store"))
		        .toStream();
		    return builder;
	}*/

	public void start() {
		
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "order-management");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        // 1 - stream from Kafka
        
        KTable<String, Long> table = builder.table("orders",Materialized.as("orders-store"));
        
  
        KStream<String, String> orders = builder.stream("orders");
       /* KTable<String, Long> wordCounts = textLines
                // 2 - map values to lowercase
                .mapValues(textLine -> textLine.toLowerCase())
                // can be alternatively written as:
                // .mapValues(String::toLowerCase)
                // 3 - flatmap values split by space
                .flatMapValues(textLine -> Arrays.asList(textLine.split("\\W+")))
                // 4 - select key to apply a key (we discard the old key)
                .selectKey((key, word) -> word)
                // 5 - group by key before aggregation
                .groupByKey()
                // 6 - count occurrences
                .count("Counts"); */

        // 7 - to in order to write the results back to kafka
       // wordCounts.to(Serdes.String(), Serdes.Long(), "word-count-output");

       // KafkaStreams streams = new KafkaStreams(builder, config);
       // streams.start();	
	}
}
