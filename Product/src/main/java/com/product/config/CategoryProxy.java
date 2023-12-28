package com.product.config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.product.service.dto.CategoryDTO;

@FeignClient(name="category-service")
public interface CategoryProxy {

	@GetMapping("/category/getAll")
	public ResponseEntity<List<CategoryDTO>> getAllCategories();
	
}
