package admin_operations;

import java.util.List;
import java.util.Map;

import bill.Payment;
import connection.Connection;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.RequestObj;

public interface IAdminOperations {

	public boolean setReading(long connNo,long readings);

	public Map<Long, List<Payment>> getNonPayers();

	public boolean changeConnectionType(TypeOfConnection typeOfConnection, long connNo);

	public Connection createConnectionForNewConsumer(String name,String email,long phoNO,String address,String connAddress, TypeOfConnection connType);

	public Connection createConnectionForExistingConsumer(long customerNo,String connAddress, TypeOfConnection connType);

//	public List<NewConnectionRequest> getNewConnectionRequests();

	public boolean addNotification(long consumerNo, String status);

//	public List<ChangeOfConnectionRequest> getConnectionChangeRequests();

	public String getUserNameFromConsumerNo(int consumerNo);

	public Map<Long, Consumer> getAllConsumers();

	public boolean updateNotification(long consumerNo, int i, String status, String string);
	
//	public boolean updateConnectionChangeStatus(ChangeOfConnectionRequest req, int index);

//	public boolean removeRequest(int reqIndex,String reqType);
	
	
	//new
	public List<RequestObj> getRequests(boolean isNewConnRequest);

	public boolean updateStatus(RequestObj req);

	public boolean removeRequest(RequestObj req);

	public void setNotification(RequestObj req, String status);
}
