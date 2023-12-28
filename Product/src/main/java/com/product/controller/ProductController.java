package com.product.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.product.exception.CategoryNotFountException;
import com.product.exception.ProductNotFountException;
import com.product.service.ProductService;
import com.product.service.dto.ProductDTO;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/save")
	@Retry(name = "default", fallbackMethod = "fallbackResponse")
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product) throws CategoryNotFountException {

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/getProductById/{id}").buildAndExpand(product.getId()).toUri();

		System.out.println("++++"+uri);
		ProductDTO productDto = productService.save(product);

		return ResponseEntity.created(uri).body(productDto);

	}

	public ResponseEntity<String> fallbackResponse(Exception x) {
		return ResponseEntity.internalServerError().body("fallbackResponse");
	}
	@PutMapping("/update/{UUID}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID id, @RequestBody ProductDTO ProductDTO) throws CategoryNotFountException, ProductNotFountException {
		ProductDTO productDto = productService.updateProduct(id, ProductDTO);
		if (productDto != null) {
			return ResponseEntity.ok(productDto);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/getAllProducts")
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		List<ProductDTO> list = productService.getAllProducts();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/getProductById/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable UUID id) throws ProductNotFountException {
		ProductDTO ProductDTO = productService.getProductById(id);
		return ResponseEntity.ok(ProductDTO);
	}
	
	@GetMapping("/selectProduct/{id}")
	public ResponseEntity<ProductDTO> selectProduct(@PathVariable UUID id) throws ProductNotFountException {
		productService.selectProduct(id);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteProduct(@RequestParam UUID id) {
		boolean isDeleted = productService.deleteProduct(id);
		if (isDeleted) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
