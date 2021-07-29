package com.flipkart.domain;

public class Product {
	private String name;
	private String sellerName;
	private Double price;
	private Double rating;
	private int quantity;
	
	public Product(String sellerName, String name, Double price, Double rating, int quantity) {
		super();
		this.name = name;
		this.sellerName = sellerName;
		this.price = price;
		this.rating = rating;
		this.quantity = quantity;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", sellerName=" + sellerName + ", price=" + price + ", rating=" + rating
				+ ", quantity=" + quantity + "]";
	}
}
