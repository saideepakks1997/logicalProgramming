package cart;

import java.util.ArrayList;
import java.util.List;

import products.*;


public class Cart {
	List<Object> products = new ArrayList<>();
	public Cart() {
		products.add(new Mobile());
		products.add(new PlayStation());
		products.add(new LapTop());
	}
	//
	public double showTotalAmount() {
		double totalAmount = 0;
		for(Object product:this.products) {
			if(product instanceof Mobile) {
				totalAmount +=((Mobile) product).calculatePriceIncludingGst();
			}
			else if(product instanceof LapTop) {
				totalAmount +=((LapTop) product).calculatePriceIncludingGst();
			}
			else if(product instanceof PlayStation) {
				totalAmount +=((PlayStation) product).calculatePriceIncludingGst();
			}
		}
			
		
		return totalAmount;
		
	}
	public void displayCartProducts() {
		System.out.println("The list of product in the cart are");
		int i=1;
		for(Object product:this.products)
			System.out.println((i++)+"."+product.getClass().getSimpleName());
	}
}
