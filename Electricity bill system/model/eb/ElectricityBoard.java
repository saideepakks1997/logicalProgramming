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
	private Map<String, Consumer> consumers = new HashMap<String, Consumer>();
	private Map<String, Admin> admins = new HashMap<String, Admin>();
	
	private Map<Long,Connection> connections = new HashMap<Long, Connection>();
	
	public ElectricityBoard() {
		Admin admin1 = new Admin("admin1", "admin1");
		admins.put(admin1.getUser_name(),admin1);
		
		Connection conn1 = new Connection(getConnNoSeries(), TypeOfConnection.domestic);
		Connection conn2 = new Connection(getConnNoSeries(), TypeOfConnection.ltCommercial);
		
		this.connections.put(conn1.getServiceNo(), conn1);
		this.connections.put(conn2.getServiceNo(), conn2);
	}

	public Map<String, Consumer> getConsumers() {
		return consumers;
	}

	public void setConsumers(Map<String, Consumer> consumers) {
		this.consumers = consumers;
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

	public void setConnections(Map<Long,Connection> connections) {
		this.connections = connections;
	}

	public long getBillNoSeries() {
		return billNoSeries++;
	}
	
	public long getConnNoSeries() {
		return connNoSeries++;
	}
}
