package common_operations;

public interface ICommonOperations {
	public boolean checkUserNameAvailable(String user_name, String userType);

	public boolean validatePassword(String user_name,String password, String userType);

	public String getConnectionStatus(long connNo);
}
