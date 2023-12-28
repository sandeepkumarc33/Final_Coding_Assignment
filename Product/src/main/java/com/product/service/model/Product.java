package com.product.service.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id = UUID.randomUUID();

	private String name;
	private String brand;
	private String description;

	@Embedded
	private Price price;

	@Embedded
	private Inventory inventory;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<CustomAttribute> attributes;
	
	
	private String category;



	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public List<CustomAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<CustomAttribute> attributes) {
		this.attributes = attributes;
	}

	public UUID getId() {
		return id;
	}
	

}