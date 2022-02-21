package main;

import java.util.Scanner;

import customer.Customer;
import foodDeliveryApp.DeliveryApp;
import order.Order;

public class Main {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args)
    {
        Customer customer = new Customer("Mohan","Chennai");

        
        Order order = new Order("Briyani",3,customer);
  
        order.acceptOrder();
        order.calculateBill();
        
        askOption(order);
        
        DeliveryApp deliveryApp = new DeliveryApp(order);
        deliveryApp.delivery();
    }

	private static void askOption(Order order) {
		boolean loop = true;
		while(loop) {
			System.out.println("-----------------------------------");
				System.out.println("Select option \n"
						+ "1->Add cooking instructions\n"
						+ "2->Add delivery instructions\n"
						+ "3->Cancel order\n"
						+ "4->Wait for order");
				System.out.println("-----------------------------------");
				int opt = sc.nextInt();
			switch(opt) {
				case 1:order.cookingInstructions();
					break;
				case 2: DeliveryApp da = new DeliveryApp(order);
					da.addDeliveryInstructions();
					break;
				case 3:order.setOrderId(-1);
					System.out.println(order.getItemName()+" has been cancelled successfully");
					loop = false;
					break;
				case 4: loop = false;
					break;
			}
		}
	}
}
