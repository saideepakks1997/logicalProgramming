package connection;

import java.util.HashMap;
import java.util.Map;

import bill.Bill;
import consumer.Consumer;

public class Connection {
	private long serviceNo;
	private TypeOfConnection connectionType;
	private long currentUnit;
	private Consumer consumer;
	
	private Map<Long, Bill> bills = new HashMap<>();

	public Connection(long serviceNo, TypeOfConnection connectionType) {
		this.setServiceNo(serviceNo);
		this.setConnectionType(connectionType);
	}
	
	public long getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(long serviceNo) {
		this.serviceNo = serviceNo;
	}

	public TypeOfConnection getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(TypeOfConnection connectionType) {
		this.connectionType = connectionType;
	}

	public long getCurrentUnit() {
		return currentUnit;
	}

	public void setCurrentUnit(long currentUnit) {
		this.currentUnit = currentUnit;
	}


	public Map<Long, Bill> getBills() {
		return bills;
	}

	public void setBills(Map<Long, Bill> bills) {
		this.bills = bills;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	
}
