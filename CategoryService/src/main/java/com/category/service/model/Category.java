package com.category.service.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorys")
public class Category {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id = UUID.randomUUID();

	private String name;
	
	

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}