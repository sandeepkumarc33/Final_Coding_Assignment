package com.mqconsumer.payload;

import java.util.List;
import java.util.UUID;

public class ProductDTO {

	private UUID id;

	private String name;
	private String brand;
	private String description;
	private Price price;
	private Inventory inventory;
	private List<CustomAttribute> attributes;
	
	private String category;



	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", brand=" + brand + ", description=" + description
				+ ", price=" + price + ", inventory=" + inventory + ", attributes=" + attributes + ", category="
				+ category + "]";
	}

	

}