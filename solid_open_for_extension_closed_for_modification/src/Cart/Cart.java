package Cart;

import java.util.ArrayList;
import java.util.List;

import products.*;


public class Cart {
	List<Product> products = new ArrayList<Product>();
	public Cart() {
		products.add(new Mobile());
		products.add(new PlayStation());
		products.add(new LapTop());
	}
	
	public double showTotalAmount() {
		double totalAmount = 0;
		for(Product product:this.products)
			totalAmount += product.calculatePriceIncludingGst();
		
		return totalAmount;
		
	}
	public void displayCartProducts() {
		System.out.println("The list of product in the cart are");
		int i=1;
		for(Product product:this.products)
			System.out.println((i++)+"."+product.getClass().getSimpleName());
	}
}
