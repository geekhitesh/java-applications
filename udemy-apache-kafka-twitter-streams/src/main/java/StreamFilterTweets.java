import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import com.google.gson.JsonParser;

public class StreamFilterTweets {

	public static void main(String[] args) {
		
		String bootstrapServers = "127.0.0.1:9092";

		// create properties
		Properties properties = new Properties();
		properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "demo-kafka-streams-4");
		properties.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
		properties.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
		
		
		// create topology
		
		StreamsBuilder streamsBuilder = new StreamsBuilder();
		
		
		
		KStream<String, String> inputTopic = streamsBuilder.stream("twitter-tweets");
		KStream<String, String> filteredStream = inputTopic.filter(
				(k,jsonTweet) ->  extractUserFollowersInTweet(jsonTweet) > 100
				);
		
		filteredStream.to("important-tweets");
		
		//build topology
		
		KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), properties);
		
		
		
		// start the application
		
		kafkaStreams.start();
		
	}
	
	private static JsonParser jsonParser = new JsonParser();

	private static Integer extractUserFollowersInTweet(String tweetJson) {
		// gson library
		
		try {
			
		
		return jsonParser.parse(tweetJson)
				.getAsJsonObject()
				.get("user")
				.getAsJsonObject()
				.get("followers_count")
				.getAsInt();
		
		}
		catch(NullPointerException e)
		{
			return 0;
		}
	}
}
