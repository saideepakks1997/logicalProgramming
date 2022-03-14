package eb;

import java.util.HashMap;
import java.util.Map;

import connection.Connection;
import connection.TypeOfConnection;
import consumer.Admin;
import consumer.Consumer;

public class ElectricityBoard {
	private long billNoSeries = 11111111l;
	private long connNoSeries = 22222l;
	//user_name and Consumer
	private Map<String, Consumer> consumers = new HashMap<String, Consumer>();
	private Map<String, Admin> admins = new HashMap<String, Admin>();
	
	private Map<Long,Connection> connections = new HashMap<Long, Connection>();
	
	public ElectricityBoard() {
		Admin admin1 = new Admin("admin1", "admin1");
		admins.put(admin1.getUser_name(),admin1);
		
		Connection conn1 = new Connection(getConnNoSeries(), TypeOfConnection.Domestic);
		Connection conn2 = new Connection(getConnNoSeries(), TypeOfConnection.ltCommercial);
		
		this.connections.put(conn1.getServiceNo(), conn1);
		this.connections.put(conn2.getServiceNo(), conn2);
	}

	public Map<String, Consumer> getConsumers() {
		return consumers;
	}

	public void setConsumers(Consumer consumer) {
		this.consumers.put(consumer.getUser_name(), consumer);
	}
	
	public Map<String, Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(Map<String, Admin> consumers) {
		this.admins = admins ;
	}

	public Map<Long,Connection> getConnections() {
		return connections;
	}

	public void setConnections(Connection conn) {
		this.connections.put(conn.getServiceNo(), conn);
	}

	public long getBillNoSeries() {
		return billNoSeries++;
	}
	
	public long getConnNoSeries() {
		return connNoSeries++;
	}
}
