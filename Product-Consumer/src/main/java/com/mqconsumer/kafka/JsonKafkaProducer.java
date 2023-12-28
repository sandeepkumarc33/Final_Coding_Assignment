package com.mqconsumer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.mqconsumer.payload.ProductDTO;

@Service
public class JsonKafkaProducer {

    @Value("${spring.kafka.topic-json.name}")
    private String topicJsonName;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String, ProductDTO> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, ProductDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(ProductDTO data){

        LOGGER.info(String.format("Message sent -> %s", data.toString()));

        Message<ProductDTO> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topicJsonName)
                .build();

        kafkaTemplate.send(message);
    }
}
