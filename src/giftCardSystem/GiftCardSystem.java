package giftCardSystem;

import java.util.*;

class User {
	public String customerId;
	private String password; // Encrypted
	private double accountBalance;
	private int rewardPoints;

	public User(String customerId, String password, double accountBalance) {
		this.customerId = customerId;
		this.password = encryptPassword(password);
		this.accountBalance = accountBalance;
		this.rewardPoints = 0;
	}

	private String encryptPassword(String password) {
		StringBuilder encrypted = new StringBuilder();
		for (char ch : password.toCharArray()) {
			if (Character.isUpperCase(ch))
				encrypted.append((char) (ch + 1));
			else if (Character.isLowerCase(ch))
				encrypted.append((char) (ch + 1));
			else if (Character.isDigit(ch))
				encrypted.append((char) (ch + 1));
			else
				encrypted.append(ch);
		}
		return encrypted.toString();
	}

	public boolean verifyLogin(String inputPassword) {
		return this.password.equals(encryptPassword(inputPassword));
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void updateAccountBalance(double amount) {
		this.accountBalance += amount;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void addRewardPoints(int points) {
		this.rewardPoints += points;
	}

	public void redeemRewards() {
		if (rewardPoints >= 10) {
			updateAccountBalance(10);
			rewardPoints -= 10;
		}
	}
}

class GiftCard {
	public String cardNumber;
	public String pin;
	public double balance;
	public boolean isBlocked;
	public List<Transaction> transactionHistory;

	public GiftCard() {
		this.cardNumber = generateCardNumber();
		this.pin = generatePin();
		this.balance = 0.0;
		this.isBlocked = false;
		this.transactionHistory = new ArrayList<>();
	}

	private String generateCardNumber() {
		return String.valueOf((int) (Math.random() * 100000));
	}

	private String generatePin() {
		return String.valueOf((int) (Math.random() * 10000));
	}

	public boolean verifyPin(String inputPin) {
		return this.pin.equals(inputPin);
	}

	public void topUp(double amount) {
		if (isBlocked) {
			System.out.println("Card is blocked. Cannot top-up.");
			return;
		}
		balance += amount;
		addTransaction("TopUp", amount);
	}

	public void purchase(double amount, User user) {
		if (isBlocked) {
			System.out.println("Card is blocked. Cannot make a purchase.");
			return;
		}
		if (balance >= amount) {
			balance -= amount;
			int rewardPoints = (int) (amount / 100);
			user.addRewardPoints(rewardPoints);
			addTransaction("Purchase", amount);
			System.out.println("Purchase successful. Remaining balance: " + balance);
		} else {
			System.out.println("Insufficient balance.");
		}
	}

	public void blockCard(User user) {
		if (isBlocked) {
			System.out.println("Card is already blocked.");
			return;
		}
		user.updateAccountBalance(balance);
		balance = 0;
		isBlocked = true;
		System.out.println("Card blocked and balance transferred to main account.");
	}

	public void addTransaction(String type, double amount) {
		transactionHistory.add(new Transaction(UUID.randomUUID().toString(), cardNumber, amount, new Date(), type));
	}

	public void printTransactionHistory() {
		for (Transaction t : transactionHistory) {
			System.out.println(t);
		}
	}
}

class Transaction {
	private String transactionId;
	private String cardNumber;
	private double amount;
	private Date date;
	private String type;

	public Transaction(String transactionId, String cardNumber, double amount, Date date, String type) {
		this.transactionId = transactionId;
		this.cardNumber = cardNumber;
		this.amount = amount;
		this.date = date;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Transaction ID: " + transactionId + ", Card Number: " + cardNumber + ", Amount: " + amount + ", Date: "
				+ date + ", Type: " + type;
	}
}

public class GiftCardSystem {
	private Map<String, User> users;
	private Map<String, GiftCard> giftCards;
	private User loggedInUser;

	public GiftCardSystem() {
		this.users = new HashMap<>();
		this.giftCards = new HashMap<>();
	}

	public void addUser(User user) {
		users.put(user.customerId, user);
	}

	public void login(String customerId, String password) {
		User user = users.get(customerId);
		if (user != null && user.verifyLogin(password)) {
			loggedInUser = user;
			System.out.println("Login successful! Welcome, " + customerId);
		} else {
			System.out.println("Invalid credentials.");
		}
	}

	public void logout() {
		loggedInUser = null;
		System.out.println("Logged out successfully.");
	}

	public void createGiftCard() {
		if (loggedInUser == null) {
			System.out.println("Please log in first.");
			return;
		}
		GiftCard card = new GiftCard();
		giftCards.put(card.cardNumber, card);
		System.out.println("Gift card created. Card Number: " + card.cardNumber + ", PIN: " + card.pin);
	}

	public void topUpGiftCard(String cardNumber, String pin, double amount) {
		GiftCard card = giftCards.get(cardNumber);
		if (card != null && card.verifyPin(pin)) {
			if (loggedInUser.getAccountBalance() >= amount) {
				card.topUp(amount);
				loggedInUser.updateAccountBalance(-amount);
				System.out.println("Top-up successful.");
			} else {
				System.out.println("Insufficient balance in main account.");
			}
		} else {
			System.out.println("Invalid card number or PIN.");
		}
	}

	public void blockGiftCard(String cardNumber) {
		GiftCard card = giftCards.get(cardNumber);
		if (card != null) {
			card.blockCard(loggedInUser);
		} else {
			System.out.println("Invalid card number.");
		}
	}

	public static void main(String[] args) {
		GiftCardSystem system = new GiftCardSystem();
		User user1 = new User("user1", "Password123", 5000);
		system.addUser(user1);

		system.login("user1", "Password123");
		system.createGiftCard();
		system.topUpGiftCard("12345", "6789", 500);
		system.logout();
	}
}
