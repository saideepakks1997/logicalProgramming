package consumer;

import java.util.ArrayList;
import java.util.List;

import connection.Connection;

public class Consumer extends User implements ICredentials{
	private long consumerNO;
	private String user_name ;
	private String password;
	
	private List<Connection> connections = new ArrayList<Connection>();
	
	private List<String> notifications = new ArrayList<String>();
	
	public Consumer(long consumerNo, String name, String email, long phoNo,String address) {
		this.setConsumerNO(consumerNo);
		this.setName(name);
		this.setEmailId(email);
		this.setPhoNo(phoNo);
		this.setAddress(address);
	}

	public long getConsumerNO() {
		return consumerNO;
	}

	public void setConsumerNO(long consumerNO) {
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
	
	@Override
	public String getUser_name() {
		return user_name;
	}
	
	@Override
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}
}
