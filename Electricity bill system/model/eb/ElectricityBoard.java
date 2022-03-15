package eb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connection.Connection;
import connection.TypeOfConnection;
import consumer.Admin;
import consumer.Consumer;

public class ElectricityBoard {
	private long billNoSeries = 11111111l;
	private long connNoSeries = 22222l;
	private int consumerNoSeries = 1;
	
	//user_name and Consumer
	private Map<Integer, Consumer> consumers = new HashMap<Integer, Consumer>();
	private Map<String, Consumer>  consumerUserName = new HashMap<String, Consumer>();
	private Map<String, Admin> admins = new HashMap<String, Admin>();
	
	private Map<Long,Connection> connections = new HashMap<Long, Connection>();
	
	private List<NewConnectionRequest> newConnRequests = new ArrayList<NewConnectionRequest>();
	private List<ChangeOfConnectionRequest> connChangeRequests = new ArrayList<ChangeOfConnectionRequest>();
	
	public ElectricityBoard() {
		Admin admin1 = new Admin("admin1", "admin1");
		admins.put(admin1.getUser_name(),admin1);
		Consumer consumer1 = new Consumer(getConsumerNoSeries(),"Sai", "ks.sai@gmail.com", 9787898l, "trl");
		Consumer consumer2 = new Consumer(getConsumerNoSeries(),"Bharath", "bharath@gmail.com", 9787848l, "Chennai");
		Connection conn1 = new Connection(getConnNoSeries(), TypeOfConnection.Domestic,"main road trl",consumer1);
		Connection conn2 = new Connection(getConnNoSeries(), TypeOfConnection.ltCommercial,"1st street trt",consumer2);
		
		this.setConnections(conn1);
		this.setConsumers(consumer1);
		this.getConsumers().get(consumer1.getConsumerNO()).setConnection(conn1);
		
		
		this.setConnections(conn2);
		this.setConsumers(consumer2);
		this.getConsumers().get(consumer2.getConsumerNO()).setConnection(conn2);
	}

	public Map<Integer, Consumer> getConsumers() {
		return consumers;
	}

	public void setConsumers(Consumer consumer) {
		this.consumers.put(consumer.getConsumerNO(), consumer);
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

	public int getConsumerNoSeries() {
		return this.consumerNoSeries++;
	}

	public Map<String, Consumer> getConsumerUserName() {
		return consumerUserName;
	}

	public void setConsumerUserName(Consumer consumer) {
		this.consumerUserName.put(consumer.getUser_name(), consumer);
	}

	public List<NewConnectionRequest> getNewConnRequests() {
		return newConnRequests;
	}

	public void setNewConnRequests(NewConnectionRequest newConnRequest) {
		this.newConnRequests.add(newConnRequest);
	}

	public List<ChangeOfConnectionRequest> getConnChangeRequests() {
		return connChangeRequests;
	}

	public void setConnChangeRequests(ChangeOfConnectionRequest connChangeRequest) {
		this.connChangeRequests.add(connChangeRequest);
	}

}
