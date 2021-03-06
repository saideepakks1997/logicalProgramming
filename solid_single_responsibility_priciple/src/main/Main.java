package main;
import order.*;

import java.util.Scanner;

import customer.*;
import foodDeliveryApp.*;
public class Main {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args)
    {
        Customer customer = new Customer("Mohan","Chennai");

        
        Order order = new Order("Pizza",3,customer);
  
        order.acceptOrder();
  
        BillCalculation billCalculation = new BillCalculation(order);
        billCalculation.calculateBill();
        
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
				case 1:CookingInstructions ci = new CookingInstructions(order);
					ci.cookingInstructions();
					break;
				case 2:DeliveryInstructions di = new DeliveryInstructions(order);
					di.addDeliveryInstructions();
					break;
				case 3:CancelOrder co = new CancelOrder();
					co.cancelOrder();
					loop = false;
					break;
				case 4: loop = false;
					break;
			}
		}
	}
}
