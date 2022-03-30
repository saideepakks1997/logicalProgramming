package consumer;

import java.util.Map;
import java.util.TreeMap;

import comparator.RequestObjectDescendingComp;
import connection.Connection;
import eb.RequestObj;


public class Consumer extends User {
	

	
	private long consumerNO;
	private String user_name;
	private String password;
	
	
	private Map<Long,Connection> connections = new TreeMap<>();
	
	private Map<RequestObj, String> notifis = new TreeMap<RequestObj, String>(new RequestObjectDescendingComp()); 
	
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

	public Map<Long,Connection> getConnections() {
		return connections;
	}

	public void setConnection(Connection connection) {
		this.connections.put(connection.getServiceNo(),connection);
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
	public String getPassword() {
		return this.password;
	}
	public boolean validatePassword(String password) {
		
		return this.password.equals(password);
	}

	public Map<RequestObj, String> getNotifis() {
		return notifis;
	}

	public void setNotifis(RequestObj req, String notification) {
		this.notifis.put(req, notification);
	}
}
