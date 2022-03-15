package consumer;

import java.util.ArrayList;
import java.util.List;

import connection.Connection;

public class Consumer extends User{
	private int consumerNO;
	
	private List<Connection> connections = new ArrayList<Connection>();
	
	private List<String> notifications = new ArrayList<String>();
	
	public Consumer(int consumerNo, String name, String email, long phoNo,String address) {
		this.setConsumerNO(consumerNo);
		this.setName(name);
		this.setEmailId(email);
		this.setPhoNo(phoNo);
		this.setAddress(address);
	}

	public int getConsumerNO() {
		return consumerNO;
	}

	public void setConsumerNO(int consumerNO) {
		this.consumerNO = consumerNO;
	}

	public List< Connection> getConnections() {
		return connections;
	}

	public void setConnection(Connection connection) {
		this.connections.add(connection);
	}

	public List<String> getNotifications() {
		return notifications;
	}

	public void setNotifications(String notification) {
		this.notifications.add(notification);
	}
}
