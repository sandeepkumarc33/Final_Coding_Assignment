package com.category.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.category.exception.CategoryNotFountException;
import com.category.service.CategoryService;
import com.category.service.dao.CategoryRepository;
import com.category.service.dto.CategoryDTO;
import com.category.service.model.Category;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    @Autowired
    private ModelMapper mapper;

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(UUID categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        return optionalCategory.map(this::mapToDTO).orElse(null);
    }

	public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = mapToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return mapToDTO(savedCategory);
    }

    public CategoryDTO updateCategory(UUID categoryId, CategoryDTO categoryDTO) throws CategoryNotFountException {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            // Update category details based on categoryDTO
            category.setName(categoryDTO.getName());
            // Other updates...
            Category updatedCategory = categoryRepository.save(category);
            return mapToDTO(updatedCategory);
        } else {
        	throw new CategoryNotFountException("Category Not Found") ;
        	}
    }

    public boolean deleteCategory(UUID categoryId) throws CategoryNotFountException {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
            return true;
        } else {
        	throw new CategoryNotFountException("Category Not Found");
        }
    }

    private CategoryDTO mapToDTO(Category category) {
      return mapper.map(category, CategoryDTO.class);
    }

    private Category mapToEntity(CategoryDTO categoryDTO) {
        return mapper.map(categoryDTO, Category.class);
    }

	
}
