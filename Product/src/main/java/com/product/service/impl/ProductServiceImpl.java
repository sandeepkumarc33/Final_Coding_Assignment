package com.product.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.config.CategoryProxy;
import com.product.config.MessageQueueProxy;
import com.product.exception.CategoryNotFountException;
import com.product.exception.ProductNotFountException;
import com.product.service.ProductService;
import com.product.service.dao.ProductRepository;
import com.product.service.dto.CategoryDTO;
import com.product.service.dto.ProductDTO;
import com.product.service.model.Product;

@Service
@Primary
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryProxy categoryProxy;
	
	@Autowired
	private MessageQueueProxy messageQueueProxy;

	@Override
	public ProductDTO save(ProductDTO productDTO) throws CategoryNotFountException {


        
		
		ResponseEntity<List<CategoryDTO>> categoryDTO=categoryProxy.getAllCategories();
		boolean isValid=categoryDTO.getBody().stream().map(a->a.getName()).anyMatch(a->a.equals(productDTO.getCategory()));
		if(isValid) {
			
		Product product = convertToEntity(productDTO);
		
		
		Product savedProduct = productRepository.save(product);

		return convertToDTO(savedProduct);

		}else {
			throw new CategoryNotFountException(productDTO.getCategory()+" is not found");
		}
	}

	@Override
	public ProductDTO updateProduct(UUID id, ProductDTO productDto) throws CategoryNotFountException,ProductNotFountException {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			
			ResponseEntity<List<CategoryDTO>> categoryDTO=categoryProxy.getAllCategories();
			boolean isValid=categoryDTO.getBody().stream().map(a->a.getName()).anyMatch(a->a.equals(productDto.getCategory()));
			if(isValid) {
			Product existingProduct = optionalProduct.get();
			existingProduct.setName(productDto.getName());
			existingProduct.setPrice(productDto.getPrice());
			Product updatedProduct = productRepository.save(existingProduct);
			return convertToDTO(updatedProduct);
			}else {
				throw new CategoryNotFountException("Category Not found");
			}
		} else {
			throw new ProductNotFountException("Product Not Found");
		}
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public ProductDTO getProductById(UUID id) throws ProductNotFountException {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(!optionalProduct.isPresent()) {
			throw new ProductNotFountException("id "+id);
		}
		return optionalProduct.map(this::convertToDTO).orElse(null);
	}
	
	
	@Override
	public ProductDTO selectProduct(UUID id) throws ProductNotFountException {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(!optionalProduct.isPresent()) {
			throw new ProductNotFountException("id "+id);
		}
		messageQueueProxy.publish(optionalProduct.map(this::convertToDTO).orElse(null));
		return optionalProduct.map(this::convertToDTO).orElse(null);
	}

	@Override
	public boolean deleteProduct(UUID productId) {
		if (productRepository.existsById(productId)) {
			productRepository.deleteById(productId);
			return true;
		} else {
			return false;
		}
	}

	// Helper method to convert Product entity to ProductDTO
	private ProductDTO convertToDTO(Product product) {
		return modelMapper.map(product, ProductDTO.class);
	}

	// Helper method to convert ProductDTO to Product entity
	private Product convertToEntity(ProductDTO productDTO) {
		return modelMapper.map(productDTO, Product.class);
	}
	
	
	
	
	

}
