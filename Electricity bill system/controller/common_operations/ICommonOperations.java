package common_operations;

import java.util.List;

import bill.Bill;
import bill.Payment;
import connection.TypeOfConnection;
import payment_options.AdminPaymentOptions;
import payment_options.ConsumerPaymentOptions;

public interface ICommonOperations {
	public boolean checkUserNameAvailable(String user_name, boolean isConsumer);

	public boolean validatePassword(String user_name,String password, String userType);


	public List<Payment> getAllPedingPayments(long connNo);

	public Bill acceptPayment(int opt, long connNo,List<Payment> pendingPayments, String paymentThrough);

	public String getConnectionType(long connNo);

	public List<TypeOfConnection> getAllConnectionTypes();

//	public boolean checkIfUserNameIsCorrect(String user_name);

//	List<String> checkIfPasswordIsValid(String password);

	public boolean isValidCustomerNo(long customerNo);

	public List<Bill> getBills(long serviceNo);

	public long getConsumerNoFromUserName(String user_name);

	public boolean checkIfValidConnectionNo(long connNo);

	public List<AdminPaymentOptions> getAdminPaymentOptions();

	public List<ConsumerPaymentOptions> getConsumerPaymentOptions();


}
