package common_operations;

import java.util.List;

import bill.Bill;
import bill.Payment;
import connection.TypeOfConnection;
import payment_options.AdminPaymentOptions;
import payment_options.ConsumerPaymentOptions;

public interface ICommonOperations {
	//Check if user name is already taken
	public boolean checkUserNameAvailable(String user_name, boolean isConsumer);
	
	//Password validation
	public boolean validatePassword(String user_name,String password, String userType);

	//Get all pending payments
	public List<Payment> getAllPedingPayments(long connNo);

	//Accept Payment
	public Bill acceptPayment(int opt, long connNo,List<Payment> pendingPayments, String paymentThrough);

	//Get Type of connection for particular connection
	public String getConnectionType(long connNo);

	//Get List of all types of connection
	public List<TypeOfConnection> getAllConnectionTypes();
	
	//Check if the entered consumer no is valid
	public boolean isValidCustomerNo(long customerNo);
	
	//Get List of all bills 
	public List<Bill> getBills(long serviceNo);
	
	//Get consumer number from user name
	public long getConsumerNoFromUserName(String user_name);
	
	//Check if Entered Connection is valid
	public boolean checkIfValidConnectionNo(long connNo);
	
	//List all admin payment options
	public List<AdminPaymentOptions> getAdminPaymentOptions();

	//List of all Consumer payment options
	public List<ConsumerPaymentOptions> getConsumerPaymentOptions();

	public void gerenateData();

	public void loadData();


}
