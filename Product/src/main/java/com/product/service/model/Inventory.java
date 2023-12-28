package com.product.service.model;

public class Inventory {

	private int total;
	private int available;
	private int reserved;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getReserved() {
		return reserved;
	}

	public void setReserved(int reserved) {
		this.reserved = reserved;
	}

}
