package com.flipkart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flipkart.domain.User;

public class UserService {
	Map<String, User> users = new HashMap<>();
	Map<String, List<String>> transactionHistory;
	
	public UserService() {
		this.users = new HashMap<String, User>();
		this.transactionHistory = new HashMap<>();
	}

	public void addUser(User user) {
		users.put(user.getName(), user);
	}
	
	public void addToSellerTransaction(String userName, String productName) {
		String transactionMessage = "SELL " + productName;
		addToTransactionHistory(userName, transactionMessage);
	}

	public void addToBuyerTransaction(String buyerName, String productName) {
		String transactionMessage = "BUY " + productName;
		addToTransactionHistory(buyerName, transactionMessage);
	}
	
	public List<String> getTransactionHistory(String username){
		return transactionHistory.get(username);
	}
	
	private void addToTransactionHistory(String userName, String transactionMessage) {
		List<String> transactions = new ArrayList<>();
		if(transactionHistory.containsKey(userName))
			transactions = transactionHistory.get(userName);
		transactions.add(transactionMessage);
		transactionHistory.put(userName, transactions);
	}
}
