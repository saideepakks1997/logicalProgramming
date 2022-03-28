package eb;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import comparator.RequestObjComparator;
import connection.Connection;
import consumer.Admin;
import consumer.Consumer;

public class ElectricityBoard implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	private long billNoSeries = 111111;
	private long connNoSeries = 22222;
	private long consumerNoSeries = 1;
	private long requestNoSeries= 1;
	
	private Set<String> emails = new HashSet<String>();
	private Set<Long> phoNos = new HashSet<Long>();
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
		this.phoNos.add(consumer.getPhoNo());
		this.emails.add(consumer.getEmailId());
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
	}
	
	public long getConnNoSeries() {
		return this.connNoSeries++;
	}

	public long getConsumerNoSeries() {
		return this.consumerNoSeries++;
	}
	
	public long getRequestNoSeries() {
		return 	this.requestNoSeries++;
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

	public Set<String> getEmails() {
		return emails;
	}

	public Set<Long> getPhoNos() {
		return phoNos;
	}
	
}
