package foodDeliveryApp;

import java.util.Scanner;

import order.Order;

public class DeliveryInstructions {
	static Scanner sc = new Scanner(System.in);
	private Order order;
	
	public DeliveryInstructions(Order order) {
		this.setOrder(order);
	}

	public void addDeliveryInstructions() {
		System.out.println("Enter cooking instructions");
		String instructions = sc.nextLine();
		this.order.setCookingInstructions(instructions);
	}
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
