package eb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.text.GapContent;

import comparator.RequestObjComparator;
import connection.Connection;
import connection.TypeOfConnection;
import consumer.Admin;
import consumer.Consumer;
import file_paths.GetSeriesNo;

public class ElectricityBoard {
//	GetSeriesNo getSeries = new GetSeriesNo();
	
	private long billNoSeries = 111111;
	private long connNoSeries = 22222;
	private long consumerNoSeries = 1;
	private long requestNoSeries= 1;
	
	//consumerNo and Consumer
	private Map<Long, Consumer> consumers = new TreeMap<Long, Consumer>();
	private Map<String, Long> consumerMapping = new HashMap<String, Long>();
	private Map<String, Admin> admins = new HashMap<String, Admin>();
	
	private Map<Long,Connection> connections = new HashMap<Long, Connection>();
	
	private Set<RequestObj> requests = new TreeSet<>(new RequestObjComparator());
//	private List<NewConnectionRequest> newConnRequests = new ArrayList<NewConnectionRequest>();
//	private List<ChangeOfConnectionRequest> connChangeRequests = new ArrayList<ChangeOfConnectionRequest>();
	
	public ElectricityBoard() {
		Admin admin1 = new Admin("admin1", "admin1");
		admins.put(admin1.getUser_name(),admin1);
//		Consumer consumer1 = new Consumer(getConsumerNoSeries(),"Sai", "ks.sai@gmail.com", 9787898l, "trl");
//		Consumer consumer2 = new Consumer(getConsumerNoSeries(),"Bharath", "bharath@gmail.com", 9787848l, "Chennai");
//		Connection conn1 = new Connection(getConnNoSeries(), TypeOfConnection.Domestic,"main road trl",consumer1);
//		Connection conn2 = new Connection(getConnNoSeries(), TypeOfConnection.LtCommercial,"1st street trt",consumer2);
////		
////		consumer1.setConnection(conn1);
//		this.setConnections(conn1);
//		this.setConsumers(consumer1);
//		consumer1.setConnection(conn1);
////		consumer1.setConnection(conn1);
////		this.getConsumers().get(consumer1.getConsumerNO()).setConnection(conn1);
////		
////		
//		this.setConnections(conn2);
//		this.setConsumers(consumer2);
//		this.getConsumers().get(consumer2.getConsumerNO()).setConnection(conn2);
	}

	public Map<Long, Consumer> getConsumers() {
		return consumers;
	}

	public void setConsumers(Consumer consumer) {
		this.consumers.put(consumer.getConsumerNO(), consumer);
	}
	
	public Map<String, Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(Map<String, Admin> consumer) {
		this.admins = consumer ;
	}

	public Map<Long,Connection> getConnections() {
		return connections;
	}

	public void setConnections(Connection conn) {
		this.connections.put(conn.getServiceNo(), conn);
	}

	public long getBillNoSeries() {
		return this.billNoSeries++;
//				getSeries.getSeries("billNoSeriess");
	}
	
	public long getConnNoSeries() {
		return this.connNoSeries++;
//				getSeries.getSeries("connNoSeries");
	}

	public long getConsumerNoSeries() {
		return this.consumerNoSeries++;
//				getSeries.getSeries("consumerNoSeries");
	}
	
	public long getRequestNoSeries() {
		return 	this.requestNoSeries++;
//				getSeries.getSeries("requestNoSeries");
	}

	public Map<String, Long> getConsumerMapping() {
		return consumerMapping;
	}

	public void setConsumerMapping(Consumer consumer) {
		this.consumerMapping.put(consumer.getUser_name(), consumer.getConsumerNO());
	}

	public Set<RequestObj> getRequests() {
		return requests;
	}

	public void setRequests(RequestObj request) {
		this.requests.add(request);
	}
}
