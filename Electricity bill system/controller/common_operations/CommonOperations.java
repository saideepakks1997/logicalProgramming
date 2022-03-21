package common_operations;

import java.util.ArrayList;
import java.util.List;

import bill.Bill;
import bill.Payment;
import connection.Connection;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.ElectricityBoard;
import payment_options.AdminPaymentOptions;
import payment_options.ConsumerPaymentOptions;

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

//	@Override
//	public boolean isServiceNoValid(long connNo) {
//		Connection conn = this.eb.getConnections().get(connNo);
//		if(conn == null) {
//			return false;
//		}
//		return true;
//	}

	@Override
	public List<Payment> getAllPedingPayments(long connNo) {
		List<Payment> pendingPayments = this.eb.getConnections().get(connNo).getPendingPayments();
		if(pendingPayments.size() == 0) {
			return null;
		}
//		
//		}
		return pendingPayments;
	}

	@Override
	public Bill acceptPayment(int opt, long connNo,List<Payment> pendingPayments, String paymentThrough) {
		
		Connection connection = this.eb.getConnections().get(connNo);
		
		try {
			Payment payment = pendingPayments.get(opt-1);
			pendingPayments.remove(opt-1);
			Bill bill = new Bill(this.eb.getBillNoSeries(),payment,paymentThrough);
			connection.setBills(bill);
			return bill;
		}
		catch(Exception e) {
			return null;
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
		boolean isValid = this.eb.getConsumerMapping().containsKey(user_name);
		return isValid;
	}
	
//	check if password follows all the standards
//	@Override
//	public List<String> checkIfPasswordIsValid(String password) {
//		int lowerCaseCount = 0;
//		int upperCaseCount = 0;
//		int specialCharacterCount = 0;
//		int numCount = 0;
//		
//		int len = password.length();
//		List<String> errors = new ArrayList<>();
//		if(!(len >= 8 && len <= 15))
//			errors.add("password length should be between 8 and 12");
//		if(password.contains(" "))
//			errors.add("password should not contain spaces");
//		
//		for(int i=0; i<len; i++) {
//			char a = password.charAt(i);
//			if(upperCaseCount == 0 && a >= 65 && a <= 90)
//				upperCaseCount++;
//			if(lowerCaseCount == 0 && a >= 97 && a <= 122)
//				lowerCaseCount++;
//			if(numCount == 0 && a >= 48 && a <= 57)
//				numCount++;
//			
//			if(
//				specialCharacterCount == 0 
//					&& 
//			   (
//				  (a >= 33 && a <= 47)
//					||
//				  (a >= 58 && a <= 64)
//			   )
//			   )
//					specialCharacterCount++;
//			
//			if(lowerCaseCount > 0 && upperCaseCount > 0 && numCount > 0 && specialCharacterCount > 0)
//				break;
//			}
//			if(lowerCaseCount == 0)
//				errors.add("password should contain atleast one lowercase letter");
//			if(upperCaseCount == 0)
//				errors.add("password should contain atleast one uppercase letter");
//			if(specialCharacterCount == 0)
//				errors.add("password should contain atleast one special character letter");
//			if(numCount == 0)
//				errors.add("password should contain atleast one number");
//			
//		return errors;
//	}

	@Override
	public boolean isValidCustomerNo(long customerNo) {
		
		return this.eb.getConsumers().containsKey(customerNo);
	}

	@Override
	public List<Bill> getBills(long serviceNo) {
		List<Bill> bills = this.eb.getConnections().get(serviceNo).getBills();
		return bills;
	}

	@Override
	public long getConsumerNoFromUserName(String user_name) {
		long consumerNo = this.eb.getConsumerMapping().get(user_name);
		return consumerNo;
	}

	@Override
	public boolean checkIfValidConnectionNo(long connNo) {
		// TODO Auto-generated method stub
		return this.eb.getConnections().containsKey(connNo);
	}

	@Override
	public List<AdminPaymentOptions> getAdminPaymentOptions() {
		List<AdminPaymentOptions> opts = new ArrayList<>();
		for(AdminPaymentOptions pay: AdminPaymentOptions.values()) {
			opts.add(pay);
		}
		return opts;
	}

	@Override
	public List<ConsumerPaymentOptions> getConsumerPaymentOptions() {
		List<ConsumerPaymentOptions> opts = new ArrayList<>();
		for(ConsumerPaymentOptions pay: ConsumerPaymentOptions.values()) {
			opts.add(pay);
		}
		return opts;
	}

	


}
