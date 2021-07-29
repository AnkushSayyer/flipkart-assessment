package com.flipkart.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flipkart.domain.Product;
import com.flipkart.exceptions.NoProductFoundException;

public class InventoryRepository {
	private Map<String, List<Product>> products = new HashMap<>();
	
	public void addProduct(Product product) {
		List<Product> productList = new ArrayList<Product>();
		if(products.containsKey(product.getName()))
			productList = products.get(product.getName());
		
		productList.add(product);
		products.put(product.getName(), productList);
	}
	
	public List<Product> getProductsByName(String name) throws NoProductFoundException {
		if(!products.containsKey(name))
			throw new NoProductFoundException("No products found");
		return products.get(name);
	}
}
