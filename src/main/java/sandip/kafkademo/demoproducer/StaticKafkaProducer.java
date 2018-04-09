package sandip.kafkademo.demoproducer;

import java.io.FileReader;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.Producer;



public class StaticKafkaProducer {
	
	// final static Logger logger = Logger.getLogger(StaticKafkaProducer.class);
	
	public static void main(String[] args) {
		Properties props = new Properties();
		String topicName = "ExceptionManage";
		
		try {
			FileReader prop_file = new FileReader("producer.properties");
			props.load(prop_file);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		String str;
		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		for (int i =0; i < 10; i++ ) {
			str = "Message :" + i;
			producer.send(new ProducerRecord<String, String>(topicName, 
		            Integer.toString(i), str));
		}
		
		producer.close();
	}
}
	
