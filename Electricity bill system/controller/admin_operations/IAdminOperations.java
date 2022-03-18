package admin_operations;

import java.util.List;
import java.util.Map;

import bill.Payment;
import connection.Connection;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.ChangeOfConnectionRequest;
import eb.NewConnectionRequest;

public interface IAdminOperations {

	public boolean setReading(long connNo,long readings);

	public Map<Long, List<Payment>> getNonPayers();

	public String changeConnectionType(TypeOfConnection typeOfConnection, long connNo);

	public String createConnectionForNewConsumer(String name,String email,long phoNO,String address,String connAddress, TypeOfConnection connType);

	public Connection createConnectionForExistingConsumer(long customerNo,String connAddress, TypeOfConnection connType);

	public List<NewConnectionRequest> getNewConnectionRequests();

	public boolean addNotification(long consumerNo, int i, String status,String reqType);

	public List<ChangeOfConnectionRequest> getConnectionChangeRequests();

	public String getUserNameFromConsumerNo(int consumerNo);

	public Map<Long, Consumer> getAllConsumers();

	public void updateStatus(NewConnectionRequest req, int index);
	
}
