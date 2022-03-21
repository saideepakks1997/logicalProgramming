package consumer_operations;

import java.util.List;

import connection.Connection;
import connection.TypeOfConnection;
import eb.ChangeOfConnectionRequest;
import eb.NewConnectionRequest;

public interface IConsumerOperations {

	public boolean registerUser(long consumerNo, String user_name, String password);

	public boolean checkConsumerCredentials(String user_name, String password);

	public List<Connection> getConsumerConnection(long consumerNo);

	public NewConnectionRequest newConnectionRequest(long consumerNo, String address, TypeOfConnection conType);

	public ChangeOfConnectionRequest changeOfConnectionRequest(long consumerNo,long serviceNo, TypeOfConnection connType);

	public List<String> getNotification(long  consumerNo);

	public long createConsumer(String name, String email, long phoNo, String address);

}
