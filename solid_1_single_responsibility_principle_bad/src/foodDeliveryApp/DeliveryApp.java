package foodDeliveryApp;

import java.util.Scanner;

import order.Order;

public class DeliveryApp {
	static Scanner sc = new Scanner(System.in);
	private Order order;
	 public String deliveryInstructions;
	    public DeliveryApp(Order order) {
	    	this.order = order; 
	    	}
	  
	    public void delivery(){
	    	if(this.order.getOrderId() != -1) {
	    		System.out.println("Hi "+this.order.getCustomer().getName()
		        		+" your order is on the way");
	    		if(this.order.getCookingInstructions() != null)
	    			System.out.println("Special instruction of "+this.order.getCookingInstructions()+" has been considered");
	    	}
	    		
	    }
	    
	    public void addDeliveryInstructions() {
			System.out.println("Enter cooking instructions");
			String instructions = sc.nextLine();
			order.setCookingInstructions(instructions);
		}
}
