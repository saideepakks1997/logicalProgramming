package common_operations;

import java.util.ArrayList;
import java.util.List;

import bill.Bill;
import bill.Payment;
import connection.Connection;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.ElectricityBoard;

public class CommonOperations implements ICommonOperations{
	ElectricityBoard eb = null;
	
	public CommonOperations(ElectricityBoard eb) {
		this.eb = eb;
	}
	
	@Override
	public boolean checkUserNameAvailable(String user_name, String user) {
		if(user.equalsIgnoreCase("consumer")) {
			return eb.getConsumers().containsKey(user_name);
		}
		else {
			return eb.getAdmins().containsKey(user_name);
		}
	}

	@Override
	public boolean validatePassword(String user_name,String password, String userType) {
		if(userType.equalsIgnoreCase("admin")) {
			return this.eb.getAdmins().get(user_name).validatePassword(password);
		}
		else
			return this.eb.getConsumers().get(user_name).validatePassword(password);
	}

	@Override
	public boolean isServiceNoValid(long connNo) {
		Connection conn = this.eb.getConnections().get(connNo);
		if(conn == null) {
			return false;
		}
		return true;
	}

	@Override
	public String getAllPedingPayments(long connNo) {
		List<Payment> payments = this.eb.getConnections().get(connNo).getPendingPayments();
		if(payments.size() == 0) {
			return null;
		}
		String display = "";
		int i=1;
		for(Payment payment: payments) {
			display += (i++)+"."+payment+"\n";
		}
		return display;
	}

	@Override
	public String acceptPayment(int opt, long connNo) {
		Connection connection = this.eb.getConnections().get(connNo);
		List<Payment> pendingPayments = connection.getPendingPayments();
		try {
			Payment payment = pendingPayments.get(opt-1);
			pendingPayments.remove(opt-1);
			Bill bill = new Bill(this.eb.getBillNoSeries(),payment);
			connection.setBills(bill);
			return "Amount has been paid successfully\n"
					+ "bill No :- "+bill.getBillNo()+"\n"
					+ "Paid amount :- "+bill.getPayment().getPayableAmount()+"\n"
					+ "Units consumed :- "+bill.getPayment().getUnitsConsumed()+"\n"
					+ "Paid date :- "+bill.getPaymentDate();
		}
		catch(Exception e) {
			return "Please enter valid number";
		}
	}

	@Override
	public String getConnectionType(long connNo) {
		TypeOfConnection connType = this.eb.getConnections().get(connNo).getConnectionType();
		return connType+"";
	}

	@Override
	public List<TypeOfConnection> getAllConnectionTypes() {
		List<TypeOfConnection> connectionTypes = new ArrayList<TypeOfConnection>();
		for(TypeOfConnection ctype: TypeOfConnection.values())
			connectionTypes.add(ctype);
		return connectionTypes;
	}

	@Override
	public boolean checkIfUserNameIsCorrect(String user_name) {
		boolean isValid = this.eb.getConsumerUserName().containsKey(user_name);
		return isValid;
	}
	
//	check if password follows all the standards
	@Override
	public List<String> checkIfPasswordIsValid(String password) {
		int lowerCaseCount = 0;
		int upperCaseCount = 0;
		int specialCharacterCount = 0;
		int numCount = 0;
		
		int len = password.length();
		List<String> errors = new ArrayList<>();
		if(!(len >= 8 && len <= 15))
			errors.add("password length should be between 8 and 12");
		if(password.contains(" "))
			errors.add("password should not contain spaces");
		
		for(int i=0; i<len; i++) {
			char a = password.charAt(i);
			if(upperCaseCount == 0 && a >= 65 && a <= 90)
				upperCaseCount++;
			if(lowerCaseCount == 0 && a >= 97 && a <= 122)
				lowerCaseCount++;
			if(numCount == 0 && a >= 48 && a <= 57)
				numCount++;
			
			if(
				specialCharacterCount == 0 
					&& 
			   (
				  (a >= 33 && a <= 47)
					||
				  (a >= 58 && a <= 64)
			   )
			   )
					specialCharacterCount++;
			
			if(lowerCaseCount > 0 && upperCaseCount > 0 && numCount > 0 && specialCharacterCount > 0)
				break;
			}
			if(lowerCaseCount == 0)
				errors.add("password should contain atleast one lowercase letter");
			if(upperCaseCount == 0)
				errors.add("password should contain atleast one uppercase letter");
			if(specialCharacterCount == 0)
				errors.add("password should contain atleast one special character letter");
			if(numCount == 0)
				errors.add("password should contain atleast one number");
			
		return errors;
	}

	@Override
	public boolean isValidCustomerNo(int customerNo) {
		
		return this.eb.getConsumers().containsKey(customerNo);
	}

	@Override
	public List<Bill> getBills(long serviceNo) {
		List<Bill> bills = this.eb.getConnections().get(serviceNo).getBills();
		return bills;
	}

	

//	@Override
//	public void addConsumer(String name, String email, long phoNo, String user_name, String password) {
//		Consumer consumer = new Consumer(name, email, phoNo, user_name, password);
//		this.eb.setConsumers(consumer);
//		
//	}
}
