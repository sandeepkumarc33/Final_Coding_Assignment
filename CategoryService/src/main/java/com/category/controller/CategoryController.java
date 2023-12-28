package com.category.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

import com.category.exception.CategoryNotFountException;
import com.category.service.CategoryService;
import com.category.service.dto.CategoryDTO;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/category")
public class CategoryController {


	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private Environment env;
	
	@PostMapping("/save")
	public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO category) {
		
		CategoryDTO categoryDTO = categoryService.save(category);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/getCategory/{id}").buildAndExpand(category.getId()).toUri();
		return ResponseEntity.created(location).body(categoryDTO);
	}

	
	@PutMapping("/update/{UUID}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable UUID id, @RequestBody CategoryDTO categoryDto) throws CategoryNotFountException {
		
		CategoryDTO categoryResponse=categoryService.updateCategory(id, categoryDto);
		if(categoryResponse != null) {
			return ResponseEntity.ok().body(categoryResponse);
		}
		return ResponseEntity.notFound().build();
		
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<CategoryDTO>> getAllCategories(){
	List<CategoryDTO> list = categoryService.getAllCategories();
	System.out.println(env.getProperty("server.port"));
	return ResponseEntity.ok().body(list);
		
	}

	@GetMapping("/getCategory/{id}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable  UUID id) {
	
		CategoryDTO categoryDTO = categoryService.getCategoryById(id);
		
		if(categoryDTO != null) {
			return ResponseEntity.ok().body(categoryDTO);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/delete")
	ResponseEntity<Object> deleteCategory(@RequestParam UUID id) throws CategoryNotFountException {
		
		boolean isDeleted = categoryService.deleteCategory(id);
		if(isDeleted) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
