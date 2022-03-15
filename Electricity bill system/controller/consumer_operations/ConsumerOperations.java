package consumer_operations;

import java.util.List;

import connection.Connection;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.ChangeOfConnectionRequest;
import eb.ElectricityBoard;
import eb.NewConnectionRequest;

public class ConsumerOperations implements IConsumerOperations{
	ElectricityBoard eb = null;
	public ConsumerOperations(ElectricityBoard eb) {
		this.eb = eb;
	}
	@Override
	public String registerUser(int consumerNo, String user_name, String password) {
		Consumer consumer = this.eb.getConsumers().get(consumerNo);
		
		consumer.setUser_name(user_name);
		consumer.setPassword(password);
		
		this.eb.setConsumerUserName(consumer);
		return "Registered successfully";
	}
	@Override
	public boolean checkConsumerCredentials(String user_name, String password) {
		Consumer consumer = this.eb.getConsumerUserName().get(user_name);
		return consumer.validatePassword(password);
		
	}
	@Override
	public List<Connection> getConsumerConnection(String user_name) {
		List<Connection> connections = this.eb.getConsumerUserName().get(user_name).getConnections();
		return connections;
	}
	@Override
	public String newConnectionRequest(String user_name, String address, TypeOfConnection connType) {
		NewConnectionRequest request = new NewConnectionRequest(user_name, address, connType);
		this.eb.setNewConnRequests(request);
		return "New Connection has been requested";
	}
	@Override
	public String changeOfConnectionRequest(String user_name,long serviceNo, TypeOfConnection connType) {
		if(this.eb.getConnections().get(serviceNo).getConnectionType() == connType) {
			return "Connection is already of type "+connType;
		}
		ChangeOfConnectionRequest request = new ChangeOfConnectionRequest(user_name,serviceNo, connType);
		this.eb.setConnChangeRequests(request);
		return "Change of connection for "+serviceNo+" has been requested successfully";
	}
	@Override
	public List<String> getNotification(String user_name) {
		List<String> notifications = this.eb.getConsumerUserName().get(user_name).getNotifications();
		return notifications;
	}

}
