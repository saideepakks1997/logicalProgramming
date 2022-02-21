package main;

import cart.*;

public class Main {
	public static void main(String args[]) {
		Cart cart = new Cart();
		cart.displayCartProducts();
		double totalCost = cart.showTotalAmount();
		System.out.println("The total cost of the above items is "+totalCost);
	}
}
