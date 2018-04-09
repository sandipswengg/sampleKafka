package sandip.kafkademo.demoproducer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.*;

public class StaticKafkaConsumer {

	final static Logger logger = Logger.getLogger(StaticKafkaConsumer.class);

	public static void main(String[] args) {
		
		String topicName = args[0];
		
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "ExceptionHandleGroup");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");//import kafka.log.Log;

		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList(topicName));
		
		ArrayList logLineCollection = new ArrayList();
		
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(200);
			List<ConsumerRecord<String, String>> lst = new ArrayList<>();
			
			for (ConsumerRecord<String, String> record : records) {
				logger.debug(record.toString());
				try {
					logLineCollection = AccessLogReader.logConverter(record.toString());
					logger.debug(logLineCollection);
				}
				catch (RuntimeException e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}
		}
	}

}
