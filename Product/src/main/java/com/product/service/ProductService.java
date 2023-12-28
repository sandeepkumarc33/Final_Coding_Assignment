package com.product.service;

import java.util.List;
import java.util.UUID;

import com.product.exception.CategoryNotFountException;
import com.product.exception.ProductNotFountException;
import com.product.service.dto.ProductDTO;


public interface ProductService {

	ProductDTO save(ProductDTO product) throws CategoryNotFountException;

	ProductDTO updateProduct(UUID id,ProductDTO product) throws CategoryNotFountException, ProductNotFountException;

	List<ProductDTO> getAllProducts();

	ProductDTO getProductById(UUID id) throws ProductNotFountException;

	boolean deleteProduct(UUID id);

	ProductDTO selectProduct(UUID id) throws ProductNotFountException;

}
