package com.category.service.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.category.service.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>{

}
	