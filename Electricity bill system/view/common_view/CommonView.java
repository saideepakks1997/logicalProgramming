package common_view;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import bill.Bill;
import bill.Payment;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import connection.Connection;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.ElectricityBoard;
import eb.RequestObj;
import eb.RequestStatus;
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
	
	
	public void displayRequest(List<RequestObj> requests, boolean isNewConnRequest) {
		int i = 1;
		if(isNewConnRequest) {
			for(RequestObj req : requests) {
				System.out.println("------------------------------");
				System.out.println("S.NO"+(i++));
				System.out.println(
						"ConsumerNo :- "+req.getConsumerNo()+"\n"
								+ "Address :- "+req.getAddress()+"\n"
								+ "Type of connection requested :- "+req.getConnType()+"\n"
								+ "Request Status :- "+RequestStatus.values()[req.getStatusNo()].displayName()
						);
				System.out.println("------------------------------");
			}
		}
		else {
			for(RequestObj req : requests) {
				System.out.println("------------------------------");
				System.out.println("S.NO :- "+(i++));
				System.out.println(
						"ConsumerNo :- "+req.getConsumerNo()+"\n"
								+ "Type of connection requested for change:- "+req.getConnType()+"\n"
								+ "Request Status :- "+RequestStatus.values()[req.getStatusNo()].displayName()
						);
				System.out.println("------------------------------");
			}
		}
	}
	
	public void displayNonPayers(String connType, Long connNo, List<Payment> list) {
		System.out.println("--------Connection Type :- "+connType+"--------------");
		System.out.println("--------------Service no:-"+connNo+"---------------");
		int i=1;
		for(Payment payment: list) {
			System.out.println((i++)+"."+payment);
		}
		System.out.println("---------------------------------------");
	}
	
	public void displayBill(Bill bill) {
		int i=1;
		System.out.println("------------------");
		System.out.println("Amount has been paid successfully\n"
				+ "S.no:- "+(i++)+"\n"
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
		System.out.printf("%3s %10s %25s %20s","Sno","ServiceNo","ConnectionType","Address\n");
		
		for(Connection con: consumerConns) {
			System.out.printf("%3d %10d %25s %20s",(i++),con.getServiceNo(),con.getConnectionType(),con.getConnAddress());
			System.out.println();
		}
		System.out.println("-------------------------------------------");
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
		Collection<Connection> connections =  consumer.getConnections().values();
		for(Connection con: connections) {
			System.out.println();
			System.out.printf("%10d %15d %30s %30s",con.getServiceNo(),con.getCurrentUnit(),con.getConnectionType(),con.getConnAddress());
			System.out.println();
		}
		System.out.println("--------------------------------------------------");
	}
	
	public Integer getInt() {
		int chances = 1;
		boolean loop = true;
		int opt = 0;
		while(loop) {
			loop = false;
			try {
				opt = sc.nextInt();
			}
			catch(InputMismatchException exception){
				if(chances >= validate.getMaxChance()) {
					displayChancesMessege();
					displayMessege("Going back to previous menu");
					sc = new Scanner(System.in);
					return null;
				}
				chances++;
				displayMessege("Wrong input enter correct input(i.e) number");
				System.out.println("Re enter the option");
				loop = true;
				sc = new Scanner(System.in);
			}
			
		}
		
		return opt;
	}
	public Long getLong() {
		int chances = 1;
		boolean loop = true;
		long val = 0;
		while(loop) {
			loop = false;
			try {
				val = sc.nextLong();
			
			}
			catch(InputMismatchException exception){
				if(chances >= validate.getMaxChance()) {
					displayChancesMessege();
					displayMessege("Going back to previous menu");
					sc = new Scanner(System.in);
					return null;
				}
				chances++;
				displayMessege("Wrong input enter correct input(i.e) number");
				System.out.println("Re enter the number");
				loop = true;
				sc = new Scanner(System.in);
			}
		}
		return val;
	}
	public String getString() {
		sc.skip("((?<!\\R)\\s)*");
		String value = sc.nextLine();
		return value;
	}
	
	public TypeOfConnection selectTypeOfConnection() {
		int chances = 1;
		boolean loop = true;
		List<TypeOfConnection> connTypes = commonOperations.getAllConnectionTypes();
		while(loop) {
			loop = false;
			int i=1;
			System.out.println("Enter connection type");
			for(TypeOfConnection ctype: connTypes) {
				System.out.println((i++)+"."+ctype);
			}
			Integer opt = getInt();
			try {
				if(opt > connTypes.size()) {
					if(chances >= validate.getMaxChance()) {
						displayChancesMessege();
						displayMessege("Going back to previous menu");
						return null;
					}
					loop = true;
					chances++;
					displayMessege("Please enter valid option");
				}
				else {
					return connTypes.get(opt-1);
				}
			}
			catch (NullPointerException e) {
				return null;
			}
		}
		return null;
	}
	
	public Long getConnectionNo() {
		boolean loop = true;
		long connNo = 0;
		int chances = 1;
		while(loop) {
			loop = false;
			System.out.println("Enter connection number");
			connNo = getLong();
			try {
				boolean isValid = commonOperations.checkIfValidConnectionNo(connNo);
				if(!isValid) {
					if(chances >= validate.getMaxChance()) {
						displayChancesMessege();
						loop = false;
						return null;
					}
					displayMessege("Enter valid service number");
					loop = true;
					chances++;
				}
			}
			catch (NullPointerException e) {
				return null;
			}
		}
		return connNo;
	}
	public void payBill(boolean isAdmin) {
		try {
			Long connNo = getConnectionNo();
			if(connNo != -1)
				viewAndPayAllPendingPayments(connNo,isAdmin);
		}
		catch (Exception e) {
			return;
		}
	}

	public void viewAndPayAllPendingPayments(long serviceNo, boolean isAdmin) {
		boolean loop = true;
		int chances = 1;
		while(loop) {
			List<Payment> pendingPayments = commonOperations.getAllPedingPayments(serviceNo);
			if(pendingPayments != null) {
				System.out.println("Enter option to accept the payment \n"
						+ "enter (-1) to exit without paying");
				System.out.println("----------------------");
				displayPendingPayment(pendingPayments);
				Integer opt = getInt();
				
//				
				try {
					if(opt == -1) {
						displayMessege("No transaction has been done");
						return;
					}
					//If entered option is wrong
					else if(opt > pendingPayments.size()) {
						if(chances >= validate.getMaxChance()) {
							displayChancesMessege();
							displayMessege("Going back to previous menu");
							return ;
						}
						displayMessege("Enter Valid option");
						chances++;
					}
					
					else {
						String paymentThrough = getPaymentOption(isAdmin);
						if(paymentThrough != null) {
							Bill bill = commonOperations.acceptPayment(opt, serviceNo,pendingPayments,paymentThrough);
							if(bill != null) 
								displayBill(bill);
							}
						else {
							displayMessege("Payment of bill Failed");
						}
						//ask whether to continue for payment of bill
						if(!continuePayBills()) {
							loop = false;
							displayMessege("Going back to previous menu");
						}
					}
				}
				catch (NullPointerException e) {
					System.out.println("Delat");
					return;
				}
				
			}
			else {
				displayMessege("No pending payments");
				loop = false;
			}
		}
	}

	private boolean continuePayBills() {
		System.out.println("Still you want pay bills\n"
				+ "1-> YES\n"
				+ "Press any Number for NO");
		int no = getInt();
		if(no != 1)
			return false;
		return true;
	}

	private String getPaymentOption(boolean isAdmin) {
		String paymentType = null;
		boolean loop = true;
		int chances = 1;
		while(loop) {
			loop = false;
			try {
				if(isAdmin) {
					int i=1;
					List<AdminPaymentOptions> paymentopts = commonOperations.getAdminPaymentOptions();
					System.out.println("Select option");
					for(AdminPaymentOptions payment: paymentopts) {
						System.out.println((i++)+"."+payment);
					}
					Integer opt = getInt();
					if(opt < 0 || opt > paymentopts.size()) {
						if(chances >= validate.getMaxChance()) {
							displayChancesMessege();
							displayMessege("Going back to previous menu");
							return null;
						}
						chances++;
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
						if(chances >= validate.getMaxChance()) {
							displayChancesMessege();
							displayMessege("Going back to previous menu");
							return null;
						}
						chances++;
						System.out.println("Enter valid option");
						loop = true;
					}
					else {
						paymentType = paymentopts.get(opt-1).toString();
					}
				}
			}
			catch (NullPointerException e) {
				return null;
			}
			
		}
		return paymentType;
	}


	public String getEmail() {
		boolean loop = true;
		int chances = 1;
		String email = null;
		while(loop) {
			loop = false;
			System.out.println("Enter email id");
			email = getString();
			boolean isValidemail = validate.validateEmail(email);
//			
			if(!isValidemail) {
				loop = true;
				if(chances >= validate.getMaxChance()) {
					displayChancesMessege();
					displayMessege("Connection creation failed \n "
							+ "Going back to previous menu");
					return null;
				}
				loop = true;
				chances++;
				displayMessege("Please enter valid email id");
			}
			else {
				
				boolean isEmailAlreadyTaken = commonOperations.isEmailTaken(email);
				if(isEmailAlreadyTaken) {
					if(chances >= validate.getMaxChance()) {
						displayChancesMessege();
						displayMessege("Connection creation failed \n "
								+ "Going back to previous menu");
						return null;
					}
					loop = true;
					chances++;
					displayMessege("Email id already taken");
				} 
			}
		}
		return email;
	}


	public Long getPhoNo() {
		int chances = 1;
		boolean loop = true;
		Long phoNo = 0l;
		while(loop) {
			loop = false;
			System.out.println("Enter phone no");
			phoNo = getLong();
			try {
				boolean isPhoNoTaken = commonOperations.isPhoneNoTaken(phoNo);
				if(isPhoNoTaken) {
					if(chances >= validate.getMaxChance()) {
						displayChancesMessege();
						displayMessege("Connection creation failed \n "
								+ "Going back to previous menu");
						return null;
					}
					loop = true;
					chances++;
					displayMessege("Phone number already taken");
				}
			}
			catch (NullPointerException e) {
				return null;
			}
			
			
		}
			
		return phoNo;
	}


	

	
}
