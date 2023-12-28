package com.mqconsumer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mqconsumer.kafka.JsonKafkaProducer;
import com.mqconsumer.payload.ProductDTO;

@RestController
@RequestMapping("/kafka")
public class JsonMessageController {

    private JsonKafkaProducer kafkaProducer;

    public JsonMessageController(JsonKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody ProductDTO dto){
        kafkaProducer.sendMessage(dto);
        return ResponseEntity.ok("Json message sent to kafka topic");
    }
}
