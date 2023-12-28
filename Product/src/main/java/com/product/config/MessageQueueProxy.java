package com.product.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.product.service.dto.ProductDTO;

@FeignClient(name = "message_queue")
public interface MessageQueueProxy {

	@PostMapping("/kafka/publish")
	public ResponseEntity<String> publish(@RequestBody ProductDTO productDTO);
}
