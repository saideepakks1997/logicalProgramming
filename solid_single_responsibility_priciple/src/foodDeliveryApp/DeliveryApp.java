package foodDeliveryApp;
import order.Order;
public class DeliveryApp {
	 private Order order;
	    public DeliveryApp(Order order) {
	    	this.order = order; 
	    	}
	  
	    public void delivery()
	    {
	        System.out.println("Hi "+this.order.customer.name
	        		+" here is your order ");
	    }
}
