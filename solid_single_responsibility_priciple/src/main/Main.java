package main;
import order.*;
import customer.*;
import foodDeliveryApp.*;
public class Main {
	public static void main(String[] args)
    {
        Customer customer = new Customer("Mohan","Chennai");

        
        Order order = new Order("Briyani",3,customer);
  
        order.acceptOrder();
  
        BillCalculation billCalculation = new BillCalculation(order);
        billCalculation.calculateBill();
        
        PackTheOrder packOrder = new PackTheOrder();
        packOrder.packOrder(order.itemName);
        
        DeliveryApp deliveryApp = new DeliveryApp(order);
        deliveryApp.delivery();
    }
}
