package consumer_operations;

import java.util.List;

import connection.Connection;
import connection.TypeOfConnection;

public interface IConsumerOperations {

	public String registerUser(int consumerNo, String user_name, String password);

	public boolean checkConsumerCredentials(String user_name, String password);

	public List<Connection> getConsumerConnection(int consumerNo);

	public String newConnectionRequest(int consumerNo, String address, TypeOfConnection conType);

	public String changeOfConnectionRequest(int consumerNo,long serviceNo, TypeOfConnection connType);

	public List<String> getNotification(int  consumerNo);

	public int createConsumer(String name, String email, long phoNo, String address);

}
