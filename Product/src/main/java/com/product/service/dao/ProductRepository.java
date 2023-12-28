package com.product.service.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.service.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID	>{

}
