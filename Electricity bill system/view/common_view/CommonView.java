package common_view;

import java.beans.Customizer;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import bill.Bill;
import bill.Payment;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import connection.Connection;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.ElectricityBoard;
import payment_options.AdminPaymentOptions;
import payment_options.ConsumerPaymentOptions;
import validator_encrypter.Validator;

public class CommonView {
	ICommonOperations commonOperations = null;
	Validator validate = new Validator();
	
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	
	public CommonView(ElectricityBoard eb) {
		this.commonOperations = new CommonOperations(eb);
	}

	Scanner sc  = new Scanner(System.in);
	public void displayMessege(Object messege) {
		System.out.println("---------------------------------------------------");
		System.out.println(messege);
		System.out.println("---------------------------------------------------");
	}
	
	public int getInt() {
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
	
	public TypeOfConnection selectTypeOfConnection() {
		boolean loop = true;
		List<TypeOfConnection> connTypes = commonOperations.getAllConnectionTypes();
		while(loop) {
			int i=1;
			System.out.println("Enter connection type");
			for(TypeOfConnection ctype: connTypes) {
				System.out.println((i++)+"."+ctype);
			}
			int opt = getInt();
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
	
	public long getConnectionNo() {
		boolean loop = true;
		long connNo = 0;
		int chances = 1;
		while(loop) {
			loop = false;
			System.out.println("Enter connection number");
			connNo = getLong();
			boolean isValid = commonOperations.checkIfValidConnectionNo(connNo);
			if(!isValid) {
				if(chances >= validate.getMaxChance()) {
					displayChancesMessege();
					loop = false;
					return -1;
				}
				displayMessege("Enter valid service number");
				loop = true;
				chances++;
			}
		}
		return connNo;
	}
	public void payBill(boolean isAdmin) {
		boolean loop = true;
		long connNo = getConnectionNo();
		viewAndPayAllPendingPayments(connNo,isAdmin);
	}


	public void displayBill(Bill bill) {
		int i=1;
		System.out.println("------------------");
		System.out.println("Amount has been paid successfully\n"
				+ "S.no:- "+(i++)
				+ "bill No :- "+bill.getBillNo()+"\n"
				+ "Paid amount :- "+bill.getPayment().getPayableAmount()+"\n"
				+ "Units consumed :- "+bill.getPayment().getUnitsConsumed()+"\n"
				+ "Paid date :- "+bill.getPaymentDate().format(dateFormatter)+"\n"
				+ "Paid time :- "+bill.getPaymentDate().format(timeFormatter)+"\n"
				+ "Paid Through :- "+bill.getPaidThrough());
		System.out.println("------------------");
		
	}

	public void displayConnections(List<Connection> consumerConns) {
		System.out.println("-------------------------------------------");
		int i=1;
		System.out.printf("%3s %10s %15s %20s","Sno","ServiceNo","ConnectionType","Address\n");
		
		for(Connection con: consumerConns) {
			System.out.printf("%3d %10d %15s %20s",(i++),con.getServiceNo(),con.getConnectionType(),con.getConnAddress());
			System.out.println();
		}
		System.out.println("-------------------------------------------");
	}
	
	public void viewAndPayAllPendingPayments(long serviceNo, boolean isAdmin) {
		boolean loop = true;
		while(loop) {
			List<Payment> pendingPayments = commonOperations.getAllPedingPayments(serviceNo);
			if(pendingPayments != null) {
				System.out.println("Enter option to accept the payment \n"
						+ "enter (-1) to exit without paying");
				System.out.println("----------------------");
				int i=1;
				displayPendingPayment(pendingPayments);
				int opt = getInt();
				
				if(opt == -1) {
					displayMessege("No transaction has been done");
					return;
				}
				String paymentThrough = getPaymentOption(isAdmin);
				
				Bill bill = commonOperations.acceptPayment(opt, serviceNo,pendingPayments,paymentThrough);
				if(bill != null) {
					displayBill(bill);
				}
					
				else {
					displayMessege("Please enter valid option");
				}
				
				System.out.println("Still you want pay bills\n"
						+ "1-> Yes\n"
						+ "Press any no for No");
				int no = getInt();
				if(no != 1) {
					loop = false;
					displayMessege("Going back to previous menu");
				}
			}
			else {
				displayMessege("No pending payments");
				loop = false;
			}
		}
	}

	private String getPaymentOption(boolean isAdmin) {
		String paymentType = null;
		boolean loop = true;
		while(loop) {
			loop = false;
			if(isAdmin) {
				int i=1;
				List<AdminPaymentOptions> paymentopts = commonOperations.getAdminPaymentOptions();
				System.out.println("Select option");
				for(AdminPaymentOptions payment: paymentopts) {
					System.out.println((i++)+"."+payment);
				}
				int opt = getInt();
				if(opt < 0 || opt > paymentopts.size()) {
					System.out.println("Enter valid option");
					loop = true;
				}
				else {
					paymentType = paymentopts.get(opt-1).toString();
				}
			}
			else {
				int i=1;
				List<ConsumerPaymentOptions> paymentopts = commonOperations.getConsumerPaymentOptions();
				System.out.println("Select option");
				for(ConsumerPaymentOptions payment: paymentopts) {
					System.out.println((i++)+"."+payment);
				}
				int opt = getInt();
				if(opt < 0 || opt > paymentopts.size()) {
					System.out.println("Enter valid option");
					loop = true;
				}
				else {
					paymentType = paymentopts.get(opt-1).toString();
				}
			}
		}
		return paymentType;
	}

	public void displayPendingPayment(List<Payment> pendingPayments) {
		int i=1;
		System.out.println("------------------");
		for(Payment payment: pendingPayments) {
			System.out.println((i++)+"."+payment);
		}
		System.out.println("------------------");
		
	}
	public void displayChancesMessege() {
		System.out.println("------------------");
		System.out.println("Maximum chances given please try after sometimes");
		System.out.println("------------------");
	}

	public void displayConsumerConnection(Consumer consumer) {
		System.out.println("--------------------------------------------------");
		System.out.println("Consumer No :- "+consumer.getConsumerNO());
		System.out.println("Consumer Name :- "+consumer.getName());
		System.out.println("Consumer Address :- "+consumer.getAddress());
		System.out.printf("%10s %15s %30s %30s","Serivce No","Current Units","Connection Type","Connection Address");
		for(Connection con: consumer.getConnections()) {
			System.out.println();
			System.out.printf("%10d %15d %30s %30s",con.getServiceNo(),con.getCurrentUnit(),con.getConnectionType(),con.getConnAddress());
			System.out.println();
		}
		System.out.println("--------------------------------------------------");
	}
}
