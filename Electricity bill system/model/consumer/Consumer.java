package consumer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import connection.Connection;

public class Consumer extends User{
	private int consumerNO;
	Map<Long, Connection> connections = new HashMap<>();
	
	public Consumer(String user_name,String password) {
		this.setUser_name(user_name);
		this.setPassword(password);
//		this.
	}

	public int getConsumerNO() {
		return consumerNO;
	}

	public void setConsumerNO(int consumerNO) {
		this.consumerNO = consumerNO;
	}
}
