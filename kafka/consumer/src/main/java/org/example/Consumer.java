package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {
    public static void main( String[] args ) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "my-consumer-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.example.MessageDeserializer");

        KafkaConsumer<String, Message> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("my-topic"));

        while (true) {
            ConsumerRecords<String, Message> records = consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String, Message> record : records){
                Message message = record.value();
                processMessage(message);
            }
        }
    }

    public static void processMessage(Message message){
        switch(message.getOperation()){
            case "prime":
                if(isPrime(message.getOperand())){
                    System.out.println(message.getOperand() + " is prime.");
                }
                else{
                    System.out.println(message.getOperand() + " is not prime.");
                }

                break;
            case "fibonacci":
                if(isFib(message.getOperand())){
                    System.out.println(message.getOperand() + " is fibonacci.");
                }
                else{
                    System.out.println(message.getOperand() + " is not fibonacci.");
                }

                break;
            case "fibPrime":
                boolean a = isPrime(message.getOperand());
                boolean b = isFib(message.getOperand());
                if(a && b){
                    System.out.println(message.getOperand() + " is both fibonacci and prime.");
                }
                else if(b){
                    System.out.println(message.getOperand() + " is fibonacci");
                }
                else if(a){
                    System.out.println(message.getOperand() + " is prime");
                }
                else{
                    System.out.println(message.getOperand() + " is neither fibonacci nor prime.");
                }

                break;
            default:
                System.out.println("invalid operation.");
        }
    }

    public static boolean isFib(int num){
        int a = 0, b = 1, c = 1;

        while(c < num){
            c = a + b;
            a = b;
            b = c;
        }

        return c == num;
    }

    public static boolean isPrime(int num){
        double m = Math.sqrt(num);

        for(int i = 2; i < m+1; i++){
            if(num % i == 0) return false;
        }

        return true;
    }
}
