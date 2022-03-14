package consumer;

import java.util.HashMap;
import java.util.Map;

import connection.Connection;

public class Consumer extends User{
	private int consumerNO;
	
	private Map<Long, Connection> connections = new HashMap<>();
	
	
	public Consumer(String name, String email, long phoNo,String user_name,String password) {
		this.setName(name);
		this.setEmailId(email);
		this.setPhoNo(phoNo);
		this.setUser_name(user_name);
		this.setPassword(password);
	}

	public int getConsumerNO() {
		return consumerNO;
	}

	public void setConsumerNO(int consumerNO) {
		this.consumerNO = consumerNO;
	}

	public Map<Long, Connection> getConnections() {
		return connections;
	}

	public void setConnection(Connection connection) {
		this.connections.put(connection.getServiceNo(), connection);
	}
}
