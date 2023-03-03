package com.abhinav.springbootkafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    //private final String topic = "KAFKA_TOPIC";
   // private final String topic = "kafka-test";
    private final String topic = "Topic02";

    @PostMapping("/produce")
    public void produceMsg(@RequestParam("msg") String msg, @RequestParam("country") String country){
        this.kafkaTemplate.send(topic, country, msg);
    }

    //@KafkaListener(topics = {"KAFKA_TOPIC", "kafka-test"})
    @KafkaListener(topics = "Topic02")
    public void consume(String msg){
        System.out.println("============The message is being consumed here============== "+msg);
    }
}
