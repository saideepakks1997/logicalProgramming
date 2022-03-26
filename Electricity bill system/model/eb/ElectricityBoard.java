package eb;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import add_or_update_files.GetSeriesNo;
import comparator.RequestObjComparator;
import connection.Connection;
import consumer.Admin;
import consumer.Consumer;

public class ElectricityBoard implements Externalizable{
	GetSeriesNo getSeries = new GetSeriesNo();
	
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
	
	public ElectricityBoard() {
		Admin admin1 = new Admin("admin1", "admin1");
		admins.put(admin1.getUser_name(),admin1);
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
		return this.getSeries.getSeries("billNoSeries");
	}
	
	public long getConnNoSeries() {
		return this.getSeries.getSeries("connNoSeries");
	}

	public long getConsumerNoSeries() {
		return this.getSeries.getSeries("consumerNoSeries");
	}
	
	public long getRequestNoSeries() {
		return 	this.getSeries.getSeries("requestNoSeries");
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

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}
}
