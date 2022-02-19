package order;
import customer.Customer;
public class Order {
	static int currOrderId = 0;
	public Customer customer;
    public int orderId;
    public String itemName;
    public int quantity;
    public int totalBillAmt;
    public Order(String itemName,int quantity,Customer customer) {
    	this.itemName = itemName;
    	this.quantity =quantity;
    	this.customer = customer;
    }
    
  
    public void acceptOrder()
    {
        System.out.println("Order has been accepted \n");
//        		+ "Order Summary \n"
//        		+ this.itemName+ " ordered\n"
//        		+ "Nos "+this.quantity
//        	);
    }
}
