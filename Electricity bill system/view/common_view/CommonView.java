package common_view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import bill.Payment;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.ElectricityBoard;

public class CommonView {
	ICommonOperations commonOperations = null;
	
	public CommonView(ElectricityBoard eb) {
		this.commonOperations = new CommonOperations(eb);
	}

	Scanner sc  = new Scanner(System.in);
	public void displayMessege(Object messege) {
		System.out.println("-----------------------");
		System.out.println(messege);
		System.out.println("-----------------------");
	}
	
	public int getOption() {
		boolean loop = true;
		int opt = 0;
		while(loop) {
			loop = false;
			try {
				opt = sc.nextInt();
			
			}
			catch(InputMismatchException exception){
				displayMessege("Wrong input enter correct input(i.e) number");
				System.out.println("Re enter the option");
				loop = true;
				sc = new Scanner(System.in);
			}
		}
		
		return opt;
	}
	public String getString() {
		sc.skip("((?<!\\R)\\s)*");
		String value = sc.nextLine();
		return value;
	}
	public long getLong() {
		boolean loop = true;
		long val = 0;
		while(loop) {
			loop = false;
			try {
				val = sc.nextLong();
			
			}
			catch(InputMismatchException exception){
				displayMessege("Wrong input enter correct input(i.e) number");
				System.out.println("Re enter the number");
				loop = true;
				sc = new Scanner(System.in);
			}
		}
		return val;
	}
	
	

	public void displayNonPayers(String connType, Long connNo, List<Payment> list) {
		System.out.println("--------Connection Type :- "+connType+"--------------");
		System.out.println("--------------Service no:-"+connNo+"---------------");
		int i=1;
		for(Payment payment: list) {
			System.out.println(i+"."+payment);
		}
		System.out.println("---------------------------------------");
	}
	
	public void displayPasswordError(List<String> errors) {
		System.out.println("-------------------------");
		for(String error:errors)
			System.out.println(error);
		
		System.out.println("-------------------------");
	}

	public String registerUser() {
		System.out.println("Enter your name");
		String name = getString();
		
		System.out.println("Enter your email id");
		String email = getString();
		
		System.out.println("Enter phone no");
		long phoNo = getLong();
		boolean loop = true;
		String user_name = null; 
		while(loop) {
			System.out.println("Enter user name ");
			user_name = getString();
			boolean isUserTaken = commonOperations.checkIfUserNameIsCorrect(user_name);
			if(isUserTaken)
				displayMessege(user_name+" already taken please try different name");
			else 
				loop = false;
		}
		loop = true; 
		String password = null ;
		while(loop) {
			System.out.println("Enter the password to set ");
			password = getString();
			List<String> passwordErrors = commonOperations.checkIfPasswordIsValid(password);
			if(passwordErrors.size() == 0) {
				System.out.println("Re-enter the password ");
				String reCheckPassword = getString();
				if(password.equals(reCheckPassword)) {
					loop = false;
				}
				else {
					System.out.println("Re-entered password does not matches with password");
					System.out.println("Start entering password from starting");
				}
			}
				
			else
				displayPasswordError(passwordErrors);
			}
//		Consumer consumer = new Consumer(name, email, phoNo, user_name, password);
		commonOperations.addConsumer(name, email, phoNo, user_name, password);
		return user_name;
		
	}
	
	public TypeOfConnection selectTypeOfConnection() {
		boolean loop = true;
		List<TypeOfConnection> connTypes = commonOperations.getAllConnectionTypes();
		while(loop) {
			int i=1;
			System.out.println("Enter connection type");
			for(TypeOfConnection ctype: connTypes) {
				System.out.println((i++)+"."+ctype);
			}
			int opt = getOption();
			if(opt > connTypes.size()) {
				displayMessege("Please enter valid option");
			}
			else {
				loop = false;
				return connTypes.get(opt-1);
			}
		
		}
		return null;
	}
}
