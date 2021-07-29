package com.flipkart.service;

import java.util.Collections;
import java.util.List;

import com.flipkart.domain.Product;
import com.flipkart.exceptions.NoProductFoundException;

public class RatingSelectionStrategy implements SelectionStrategy {

	@Override
	public Product selectProduct(String userName, List<Product> products) throws NoProductFoundException {
		Collections.sort(products, (product1, product2) -> {
			if(product1.getRating() == product2.getRating())
				return (int) (product1.getPrice() - product2.getPrice());
			return (int) (product2.getRating() - product1.getRating());
		});
		
		Product productFound = products.stream().filter((product) -> !product.getSellerName().equals(userName) && product.getQuantity()>0)
				.findFirst().orElse(null);
		
		if(productFound == null)
			throw new NoProductFoundException("No products found");
		
		return productFound;
	}

}
