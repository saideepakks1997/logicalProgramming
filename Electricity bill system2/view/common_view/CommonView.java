package common_view;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import bill.Bill;
import bill.Payment;
import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import connection.TypeOfConnection;
import consumer_operations.ConsumerOperations;
import consumer_operations.IConsumerOperations;
import eb.ElectricityBoard;
import payment_options.AdminPaymentOptions;
import payment_options.ConsumerPaymentOptions;
import validator_encrypter.Validator;

public class CommonView {
	ICommonOperations commonOperations = null;
	IConsumerOperations consumerOperations = null;
	Validator validate = new Validator();
	
	UserInputView input = new UserInputView();
	DisplayView display = new DisplayView();
	
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	
	public CommonView(ElectricityBoard eb) {
		this.commonOperations = new CommonOperations(eb);
		this.consumerOperations = new ConsumerOperations(eb);
	}

	Scanner sc  = new Scanner(System.in);
	
	public TypeOfConnection selectTypeOfConnection() {
		int chances = 1;
		boolean loop = true;
		List<TypeOfConnection> connTypes = commonOperations.getAllConnectionTypes();
		while(loop) {
			loop = false;
			int i=1;
			System.out.println("Enter connection type");
			for(TypeOfConnection ctype: connTypes) {
				System.out.println((i++)+"."+ctype.getResponse());
			}
			Integer opt = input.getInt();
			try {
				if(opt > connTypes.size()) {
					if(chances >= validate.getMaxChance()) {
						display.displayChancesMessege();
						display.displayMessege("Going back to previous menu");
						return null;
					}
					loop = true;
					chances++;
					display.displayMessege("Please enter valid option");
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
			connNo = input.getLong();
			try {
				boolean isValid = commonOperations.checkIfValidConnectionNo(connNo);
				if(!isValid) {
					if(chances >= validate.getMaxChance()) {
						display.displayChancesMessege();
						loop = false;
						return null;
					}
					display.displayMessege("Enter valid service number");
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
			if(connNo != null)
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
				display.displayMessege("Enter option to accept the payment \n"
						+ "enter (-1) to exit without paying");
				
				display.displayPendingPayment(pendingPayments);
				Integer opt = input.getInt();
				
//				
				try {
					if(opt == -1) {
						display.displayMessege("No transaction has been done");
						return;
					}
					//If entered option is wrong
					else if(opt > pendingPayments.size()) {
						if(chances >= validate.getMaxChance()) {
							display.displayChancesMessege();
							display.displayMessege("Going back to previous menu");
							return ;
						}
						display.displayMessege("Enter Valid option");
						chances++;
					}
					
					else {
						String paymentThrough = getPaymentOption(isAdmin);
						if(paymentThrough != null) {
							Bill bill = commonOperations.acceptPayment(opt, serviceNo,pendingPayments,paymentThrough);
							if(bill != null) 
								display.displayBill(bill);
							}
						else {
							display.displayMessege("Payment of bill Failed");
						}
						//ask whether to continue for payment of bill
						if(!continuePayBills()) {
							loop = false;
							display.displayMessege("Going back to previous menu");
						}
					}
				}
				catch (NullPointerException e) {
					System.out.println("Delat");
					return;
				}
				
			}
			else {
				display.displayMessege("No pending payments");
				loop = false;
			}
		}
	}

	private boolean continuePayBills() {
		System.out.println("Still you want pay bills\n"
				+ "1-> YES\n"
				+ "Press any Number for NO");
		int no = input.getInt();
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
					Integer opt = input.getInt();
					if(opt < 0 || opt > paymentopts.size()) {
						if(chances >= validate.getMaxChance()) {
							display.displayChancesMessege();
							display.displayMessege("Going back to previous menu");
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
					int opt = input.getInt();
					if(opt < 0 || opt > paymentopts.size()) {
						if(chances >= validate.getMaxChance()) {
							display.displayChancesMessege();
							display.displayMessege("Going back to previous menu");
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
			email = input.getString();
			boolean isValidemail = validate.validateEmail(email);
//			
			if(!isValidemail) {
				loop = true;
				if(chances >= validate.getMaxChance()) {
					display.displayChancesMessege();
					display.displayMessege("Connection creation failed \n "
							+ "Going back to previous menu");
					return null;
				}
				loop = true;
				chances++;
				display.displayMessege("Please enter valid email id");
			}
			else {
				
				boolean isEmailAlreadyTaken = commonOperations.isEmailTaken(email.toLowerCase());
				if(isEmailAlreadyTaken) {
					if(chances >= validate.getMaxChance()) {
						display.displayChancesMessege();
						display.displayMessege("Connection creation failed \n "
								+ "Going back to previous menu");
						return null;
					}
					loop = true;
					chances++;
					display.displayMessege("Email id already taken");
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
			phoNo = input.getLong();
			try {
				boolean isValidPhno = validate.validatePhoNo(phoNo);
				if(isValidPhno) {
					boolean isPhoNoTaken = commonOperations.isPhoneNoTaken(phoNo);
					if(isPhoNoTaken) {
						if(chances >= validate.getMaxChance()) {
							display.displayChancesMessege();
							display.displayMessege("Connection creation failed \n "
									+ "Going back to previous menu");
							return null;
						}
						loop = true;
						chances++;
						display.displayMessege("Phone number already taken");
					}
				}
				else {
					if(chances >= validate.getMaxChance()) {
						display.displayChancesMessege();
						display.displayMessege("Connection creation failed \n "
								+ "Going back to previous menu");
						return null;
					}
					loop = true;
					display.displayMessege("Phone number should have 10 digits and it should start with 7,8,9");
				}
				
			}
			catch (NullPointerException e) {
				return null;
			}
		}
		return phoNo;
	}
	
	public Long getConsumerDetails(){
		Long consumerNo = null; 
		try {
			System.out.println("Start entering details");
			System.out.println("Enter name ");
			String name = input.getString();
			
			String email = getEmail();
			if(email == null)
				return null;
			Long phoNo= getPhoNo();
			if(phoNo == null)
				return null;
			
			System.out.println("Enter your address");
			String address = input.getString();
			
			consumerNo = consumerOperations.createConsumer(name,email,phoNo,address);
		}
		catch (Exception e) {
			return null;
		}
		return consumerNo;
	}
}
