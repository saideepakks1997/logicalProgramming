package common_operations;

import java.util.List;

import bill.Bill;
import bill.Payment;
import connection.TypeOfConnection;

public interface ICommonOperations {
	public boolean checkUserNameAvailable(String user_name, String userType);

	public boolean validatePassword(String user_name,String password, String userType);

	public boolean isServiceNoValid(long connNo);

	public List<Payment> getAllPedingPayments(long connNo);

	public Bill acceptPayment(int opt, long connNo);

	public String getConnectionType(long connNo);

	public List<TypeOfConnection> getAllConnectionTypes();

	public boolean checkIfUserNameIsCorrect(String user_name);

	List<String> checkIfPasswordIsValid(String password);

	public boolean isValidCustomerNo(long customerNo);

	public List<Bill> getBills(long serviceNo);

	public long getConsumerNoFromUserName(String user_name);

	public boolean checkIfValidConnectionNo(long connNo);


}
