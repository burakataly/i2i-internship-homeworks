package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class Producer {
    public static void main( String[] args ) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.example.MessageSerializer");

        KafkaProducer<String, Message> producer = new KafkaProducer<>(props);

        for(int i=1; i < 20; i++){
            Message message = new Message(i, "fibPrime");
            producer.send(new ProducerRecord<>("my-topic", "operation", message));
        }


        producer.close();
    }
}
