package com.flipkart.application;

import com.flipkart.domain.Product;
import com.flipkart.domain.SelectionStrategyType;
import com.flipkart.domain.User;
import com.flipkart.domain.UserGender;
import com.flipkart.service.InventoryService;
import com.flipkart.service.PriceSelectionStrategy;
import com.flipkart.service.RatingSelectionStrategy;
import com.flipkart.service.SelectionStrategy;
import com.flipkart.service.UserService;

public class Driver {
	
	public static void main(String args[]) {
		User mohan = new User("Mohan", UserGender.M, 30);
		User deepesh = new User("Deepesh", UserGender.M, 30);
		User anand = new User("Anand Maurya", UserGender.M, 27);
		User nakul = new User("Nakul", UserGender.M, 27);
		User akshay = new User("Akshay", UserGender.M, 27);
		
		UserService userService = new UserService();
		userService.addUser(mohan);
		userService.addUser(deepesh);
		userService.addUser(anand);
		userService.addUser(nakul);
		userService.addUser(akshay);
		
		Product p1 = new Product("Mohan", "iPhone 12", 80000.0, 4.7, 3);
		Product p2 = new Product("Deepesh", "iPhone 12", 75000.0, 4.6, 4);
		Product p3 = new Product("Mohan", "OnePlus 8T", 35000.0, 4.2, 1);
		Product p4 = new Product("Anand Maurya", "Nokia 1100", 1000.0, 5.0, 3);
		
		SelectionStrategy ratingSelectionStrategy = new RatingSelectionStrategy();
		SelectionStrategy priceSelectionStrategy = new PriceSelectionStrategy();
		InventoryService inventoryService = new InventoryService(ratingSelectionStrategy, priceSelectionStrategy, userService);
		
		inventoryService.addProduct(p1);
		inventoryService.addProduct(p2);
		inventoryService.addProduct(p3);
		inventoryService.addProduct(p4);
		
		System.out.println(inventoryService.selectProduct("Anand Maurya", "iPhone 12", SelectionStrategyType.RATING));
		
		System.out.println(inventoryService.selectProduct("Akshay", "iPhone 12", SelectionStrategyType.PRICE));
		
		System.out.println(inventoryService.selectProduct("Deepesh", "Vivo V10", SelectionStrategyType.PRICE));
		
		System.out.println(inventoryService.selectProduct("Mohan", "Nokia 1100", SelectionStrategyType.RATING));
		
		System.out.println(inventoryService.selectProduct("Anand Maurya", "Nokia 1100", SelectionStrategyType.RATING));

		
		System.out.println(userService.getTransactionHistory("Anand Maurya"));
		
		System.out.println(userService.getTransactionHistory("Akshay"));
		
		System.out.println(userService.getTransactionHistory("Deepesh"));
		
		System.out.println(userService.getTransactionHistory("Mohan"));
	}
}
