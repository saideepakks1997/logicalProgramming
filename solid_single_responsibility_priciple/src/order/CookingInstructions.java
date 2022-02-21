package order;

import java.util.Scanner;

public class CookingInstructions {
	private Order order;
	static Scanner sc = new Scanner(System.in);
	public CookingInstructions(Order order) {
		this.setOrder(order);
	}
		public void cookingInstructions() {
			System.out.println("Enter the cooking instructions");
			String instruction = sc.nextLine();
			order.setCookingInstructions(instruction);
		}
		
		//getter and setter methods
		public Order getOrder() {
			return order;
		}
		public void setOrder(Order order) {
			this.order = order;
		}
}
