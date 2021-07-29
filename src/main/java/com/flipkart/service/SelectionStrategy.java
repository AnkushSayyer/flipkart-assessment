package com.flipkart.service;

import java.util.List;

import com.flipkart.domain.Product;
import com.flipkart.exceptions.NoProductFoundException;

public interface SelectionStrategy {

	public Product selectProduct(String userName, List<Product> products) throws NoProductFoundException;
}
