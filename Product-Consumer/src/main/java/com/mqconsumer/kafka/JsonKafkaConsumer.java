package com.mqconsumer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mqconsumer.payload.ProductDTO;

@Service
public class JsonKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ProductDTO productDTO){
        LOGGER.info(String.format("Json message recieved -> %s", productDTO.toString()));
    }
}
