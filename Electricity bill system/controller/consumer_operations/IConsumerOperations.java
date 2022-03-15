package consumer_operations;

import java.util.List;

import connection.Connection;
import connection.TypeOfConnection;

public interface IConsumerOperations {

	public String registerUser(int consumerNo, String user_name, String password);

	public boolean checkConsumerCredentials(String user_name, String password);

	public List<Connection> getConsumerConnection(String user_name);

	public String newConnectionRequest(String user_name, String address, TypeOfConnection conType);

	public String changeOfConnectionRequest(String user_name,long serviceNo, TypeOfConnection connType);

	public List<String> getNotification(String user_name);

}
