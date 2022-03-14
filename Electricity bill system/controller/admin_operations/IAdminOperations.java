package admin_operations;

import java.util.List;
import java.util.Map;

import bill.Payment;
import connection.TypeOfConnection;

public interface IAdminOperations {

	public String setReading(long connNo,long readings);

	public Map<Long, List<Payment>> getNonPayers();

	public String changeConnectionType(TypeOfConnection typeOfConnection, long connNo);

	public String createConnection(String user_name, TypeOfConnection connType);
	
}
