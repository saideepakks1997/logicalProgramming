package consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import comparator.RequestObjectDescendingComp;
import connection.Connection;
import eb.RequestObj;
import tables.ConsumerTable;


public class Consumer extends User {
	ConsumerTable consumer_table = new ConsumerTable();
	
	private long consumerNO;
	private String user_name ;
	private String password;
	
	private List<Connection> connections = new ArrayList<Connection>();
	
	private List<String> notifications = new ArrayList<String>();
	
	private Map<RequestObj, String> notifis = new TreeMap<RequestObj, String>(new RequestObjectDescendingComp()); 
	
	public Consumer(long consumerNo, String name, String email, long phoNo,String address) {
		this.setConsumerNO(consumerNo);
		this.setName(name);
		this.setEmailId(email);
		this.setPhoNo(phoNo);
		this.setAddress(address);
		consumer_table.addConsumer(consumerNo, name, email, phoNo, address);
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
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean validatePassword(String password) {
		
		return this.password.equals(password);
	}

	public boolean setUserNamePassword(String user_name, String password) {
		boolean isDone = this.consumer_table.updateConsumer(this.consumerNO, user_name, password);
		return isDone;
	}

	public Map<RequestObj, String> getNotifis() {
		return notifis;
	}

	public void setNotifis(RequestObj req, String notification) {
		this.notifis.put(req, notification);
	}
}
