package com.category.service;

import java.util.List;
import java.util.UUID;

import com.category.exception.CategoryNotFountException;
import com.category.service.dto.CategoryDTO;



public interface CategoryService {

	CategoryDTO save(CategoryDTO category);

	CategoryDTO updateCategory(UUID id,CategoryDTO category) throws CategoryNotFountException;

	List<CategoryDTO> getAllCategories();

	CategoryDTO getCategoryById(UUID id);

	boolean deleteCategory(UUID id) throws CategoryNotFountException;

}
