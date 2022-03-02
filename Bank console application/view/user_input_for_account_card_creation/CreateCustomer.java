package user_input_for_account_card_creation;

import java.util.Scanner;

import customer.Customer;

public class CreateCustomer {
	static Scanner sc = new Scanner(System.in);
	public Customer createCustomer() {
		
		Customer customer = new Customer();
		System.out.println("Enter customer name");
		String name = sc.nextLine();
		
		System.out.println("Enter date of birth");
		String dob = sc.next();
				
		System.out.println("Enter address");
		String address = sc.nextLine();
		sc.nextLine();
		
		System.out.println("Enter phone number");
		long phnNo = sc.nextLong();
		sc.nextLine();
		customer.setName(name);
		customer.setDob(dob);
		customer.setAddress(address);
		customer.setPhnNo(phnNo);
		
		
		return customer;
	}
}
