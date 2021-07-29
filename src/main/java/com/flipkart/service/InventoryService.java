package com.flipkart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flipkart.domain.Product;
import com.flipkart.domain.SelectionStrategyType;
import com.flipkart.exceptions.NoProductFoundException;
import com.flipkart.repository.InventoryRepository;

public class InventoryService {
	private SelectionStrategy ratingSelectionStrategy;
	private SelectionStrategy priceSelectionStrategy;
	private UserService userService;
	private InventoryRepository inventoryRepository = new InventoryRepository();
	

	public InventoryService(SelectionStrategy ratingSelectionStrategy, SelectionStrategy priceSelectionStrategy,
			UserService userService) {
		this.ratingSelectionStrategy = ratingSelectionStrategy;
		this.priceSelectionStrategy = priceSelectionStrategy;
		this.userService = userService;
	}

	public void addProduct(Product product) {
		inventoryRepository.addProduct(product);
		addToSellerTransaction(product.getSellerName(), product.getName());
	}
	
	public String selectProduct(String userName, String productName, SelectionStrategyType selectionStrategy) {
		try {
			List<Product> products = inventoryRepository.getProductsByName(productName);

			if (products == null)
				return null;

			Product product = getSelectedProduct(userName, productName, products, selectionStrategy);
			decreaseQuantity(product);
			addToBuyerTransaction(userName, productName);
//			addToSellerTransaction(product.getSellerName(), productName);
			
			return product.toString();
		} catch (NoProductFoundException e) {
			return e.getMessage();
		}
	}

	private void addToSellerTransaction(String sellerName, String productName) {
		userService.addToSellerTransaction(sellerName, productName);
	}

	private void addToBuyerTransaction(String buyerName, String productName) {
		userService.addToBuyerTransaction(buyerName, productName);
	}

	private void decreaseQuantity(Product product) {
		int quantity = product.getQuantity();
		quantity--;
		product.setQuantity(quantity);
	}

	private Product getSelectedProduct(String userName, String productName, List<Product> products, SelectionStrategyType selectionStrategy) throws NoProductFoundException {
		switch (selectionStrategy) {
		case RATING:
			return ratingSelectionStrategy.selectProduct(userName, products);
		case PRICE:
			return priceSelectionStrategy.selectProduct(userName, products);
		default:
			break;
		}
		return null;
	}
}
