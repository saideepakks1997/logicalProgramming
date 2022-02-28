package bank;

import java.util.Scanner;

import customer.Customer;

public interface GetCustomerDetails {
	static Scanner sc = new Scanner(System.in);
	public static Customer getCustomerDetails() {
		Customer customer = new Customer();
		System.out.println("Enter customer name");
		String name = sc.nextLine();
		
		System.out.println("Enter date of birth");
		String dob = sc.next();
		
		System.out.println("Enter adhar card number");
		long adharCardNo = sc.nextLong();
		
		System.out.println("Enter address");
		String address = sc.nextLine();
		sc.nextLine();
		
		System.out.println("Enter phone number");
		long phnNo = sc.nextLong();
		sc.nextLine();
		customer.setName(name);
		customer.setDob(dob);
		customer.setAddress(address);
		customer.setAdharCardNo(adharCardNo);
		customer.setPhnNo(phnNo);
		
//		this.customers.add(customer);
		
		return customer;
	}
}
