package onlineShoppingApplication;

import java.util.*;

//Class representing Product
class Product {
	private String id;
	private String name;
	private String description;
	private double price;
	private int quantity;

	public Product(String id, String name, String description, double price, int quantity) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean isAvailable(int quantity) {
		return this.quantity >= quantity;
	}

	public void updateQuantity(int quantity) {
		this.quantity = quantity;
	}
}

//Class representing OrderItem
class OrderItem {
	private Product product;
	private int quantity;

	public OrderItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getTotalPrice() {
		return product.getPrice() * quantity;
	}
}

//Class representing ShoppingCart
class ShoppingCart {
	private Map<String, OrderItem> items;

	public ShoppingCart() {
		items = new HashMap<>();
	}

	public void addItem(Product product, int quantity) {
		if (product.isAvailable(quantity)) {
			items.put(product.getId(), new OrderItem(product, quantity));
		} else {
			System.out.println("Product not available in the desired quantity.");
		}
	}

	public void removeItem(String productId) {
		items.remove(productId);
	}

	public void updateItemQuantity(String productId, int quantity) {
		OrderItem item = items.get(productId);
		if (item != null && item.getProduct().isAvailable(quantity)) {
			items.put(productId, new OrderItem(item.getProduct(), quantity));
		}
	}

	public List<OrderItem> getItems() {
		return new ArrayList<>(items.values());
	}

	public void clear() {
		items.clear();
	}
}

//Enum representing OrderStatus
enum OrderStatus {
	PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
}

//Class representing Order
class Order {
	String id;
	private User user;
	private List<OrderItem> items;
	private double totalAmount;
	private OrderStatus status;

	public Order(String id, User user, List<OrderItem> items) {
		this.id = id;
		this.user = user;
		this.items = items;
		this.totalAmount = calculateTotalAmount();
		this.status = OrderStatus.PENDING;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public double calculateTotalAmount() {
		return items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public OrderStatus getStatus() {
		return status;
	}
}

//Class representing Payment
interface Payment {
	boolean processPayment(double amount);
}

//Class representing CreditCardPayment
class CreditCardPayment implements Payment {
	@Override
	public boolean processPayment(double amount) {
		System.out.println("Processing credit card payment of amount: " + amount);
		return true;
	}
}

//Class representing User
class User {
	String id;
	private String name;
	private String email;
	private String password;
	private List<Order> orders;

	public User(String id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.orders = new ArrayList<>();
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	public List<Order> getOrders() {
		return orders;
	}
}

//Class representing OnlineShoppingService
class OnlineShoppingService {
	private Map<String, User> users;
	private Map<String, Product> products;
	private Map<String, Order> orders;

	private static OnlineShoppingService instance;

	private OnlineShoppingService() {
		users = new HashMap<>();
		products = new HashMap<>();
		orders = new HashMap<>();
	}

	public static OnlineShoppingService getInstance() {
		if (instance == null) {
			instance = new OnlineShoppingService();
		}
		return instance;
	}

	public void registerUser(User user) {
		users.put(user.id, user);
	}

	public Product getProduct(String productId) {
		return products.get(productId);
	}

	public void addProduct(Product product) {
		products.put(product.getId(), product);
	}

	public Order placeOrder(User user, ShoppingCart cart, Payment payment) {
		List<OrderItem> items = cart.getItems();
		if (items.isEmpty()) {
			System.out.println("Cart is empty!");
			return null;
		}

		double totalAmount = items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
		if (payment.processPayment(totalAmount)) {
			Order order = new Order(UUID.randomUUID().toString(), user, items);
			orders.put(order.id, order);
			user.addOrder(order);
			return order;
		}
		return null;
	}
}

//Main class to run the application
public class Main {
	public static void main(String[] args) {
		OnlineShoppingService service = OnlineShoppingService.getInstance();

		// Add products
		Product p1 = new Product("1", "Laptop", "High-end laptop", 1200.00, 10);
		Product p2 = new Product("2", "Phone", "Smartphone with 4G", 800.00, 20);
		service.addProduct(p1);
		service.addProduct(p2);

		// Register a user
		User user = new User("1", "John Doe", "john@example.com", "password123");
		service.registerUser(user);

		// Shopping cart
		ShoppingCart cart = new ShoppingCart();
		cart.addItem(p1, 1);
		cart.addItem(p2, 2);

		// Payment
		Payment payment = new CreditCardPayment();

		// Place an order
		Order order = service.placeOrder(user, cart, payment);
		if (order != null) {
			System.out.println("Order placed successfully! Total amount: " + order.getTotalAmount());
		}
	}
}
