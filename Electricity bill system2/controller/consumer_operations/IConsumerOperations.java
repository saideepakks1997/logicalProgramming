package consumer_operations;

import java.util.List;

import connection.Connection;
import connection.TypeOfConnection;
import eb.RequestObj;

public interface IConsumerOperations {
	//Registration of user
	public boolean registerUser(long consumerNo, String user_name, String password);
	
	//Get all connections of consumer
	public List<Connection> getConsumerConnection(long consumerNo);

	//Creation of consumer
	public long createConsumer(String name, String email, long phoNo, String address);
	
	//New Connection request
	public RequestObj newConnectionReq(long consumerNo, String address, TypeOfConnection conType);

	//Change of Connection type request
	public RequestObj changeOfConnectionReq(long consumerNo, long serviceNo, TypeOfConnection connType);
	
	//Get notifications
	public List<String> getNotification(long consumerNo);

	public String checkUserIfAlreadyRegiestered(Long consumerNo);
	
	public void removeConsumer(Long consumerNo);


}
