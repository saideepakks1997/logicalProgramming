package admin_operations;

import java.util.List;
import java.util.Map;

import bill.Payment;
import connection.Connection;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.RequestObj;

public interface IAdminOperations {
	//Set readings to consumer
	public boolean setReading(long connNo,long readings);
	
	//Get list of all Non payers
	public Map<Long, List<Payment>> getNonPayers();
	
	//Change type of connection from domestic to ltCommercial etc
	public boolean changeConnectionType(TypeOfConnection typeOfConnection, long connNo);

	//Create New Connection for New Consumer
//	public Connection createConnectionForNewConsumer(String name,String email,long phoNO,String address,String connAddress, TypeOfConnection connType);

	//Create new Connection for existing consumer
	public Connection createConnection(long customerNo,String connAddress, TypeOfConnection connType);
	
	//Get list of all consumers
	public Map<Long, Consumer> getAllConsumers();
	
	//Get all request new connection request and change of connection type(Request from user)
	public List<RequestObj> getRequests(boolean isNewConnRequest);
	
	//Updating the status number 
	public boolean updateStatus(RequestObj req);

	//Remove request if request is completed or rejected
	public boolean removeRequest(RequestObj req);
	
	//Set notifiaction to the consumer
	public void setNotification(RequestObj req, String status);
}
