package order;

import java.util.Scanner;

import customer.Customer;

public class Order {

	static Scanner sc = new Scanner(System.in);
	private Customer customer;
	private int orderId;
	private String itemName;
	private int quantity;
	private int totalBillAmt;
	private String cookingInstructions;
	
	public Order(String itemName,int quantity,Customer customer) {
    	this.setItemName(itemName);
    	this.setQuantity(quantity);
    	this.setCustomer(customer);
    }
    
  
    public void acceptOrder()
    {
        System.out.println("Order has been accepted \n");
//        		
    }
    
    public void calculateBill()
    {
        int totalAmt = 300 *  this.getQuantity();
        this.setTotalBillAmt(totalAmt);
        System.out.println("Order Summary \n"
		+ this.getItemName()+ " ordered\n"
		+ "Nos "+this.getQuantity()
		+"Amount :-"+this.getTotalBillAmt()
        		);
    }
    
    public void cancelOrder() {
		System.out.println(this.getOrderId()+"."+this.getItemName()+""
				+ " has been cancelled successfully");
	}

    public void cookingInstructions() {
		System.out.println("Enter the cooking instructions");
		String instruction = sc.nextLine();
		this.setCookingInstructions(instruction);
	}
//getter and setter methods
	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public int getTotalBillAmt() {
		return totalBillAmt;
	}


	public void setTotalBillAmt(int totalBillAmt) {
		this.totalBillAmt = totalBillAmt;
	}


	public String getCookingInstructions() {
		return cookingInstructions;
	}


	public void setCookingInstructions(String cookingInstructions) {
		this.cookingInstructions = cookingInstructions;
	}
}
