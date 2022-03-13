package common_operations;

import connection.Connection;
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
	public String getConnectionStatus(long connNo) {
		Connection conn = this.eb.getConnections().get(connNo);
		if(conn == null) {
			return "Please enter the correct connection number";
		}
		else {
//			double payableAmount = conn.
		}
		return null;
	}

}
