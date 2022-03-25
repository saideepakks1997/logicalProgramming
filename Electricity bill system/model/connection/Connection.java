package connection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bill.Bill;
import bill.Payment;
import consumer.Consumer;

public class Connection implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long serviceNo;
	private TypeOfConnection connectionType;
	private long currentUnit;
	private Consumer consumer;
	private String connAddress;
	
//	private Map<Long, Bill> bills = new HashMap<>();
	private List<Payment> pendingPayments = new ArrayList<>();
	private List<Bill> bills = new ArrayList<Bill>();
	
	public Connection(long serviceNo, TypeOfConnection connectionType,String connAddress, Consumer consumer) {
		this.setServiceNo(serviceNo);
		this.setConnectionType(connectionType);
		this.setConnAddress(connAddress);
		this.setConsumer(consumer);
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
		System.out.println(currentUnit);
		this.currentUnit = currentUnit;
		System.out.println("setted"+this.currentUnit);
	}


	public List< Bill> getBills() {
		return bills;
	}

	public void setBills(Bill bill) {
		this.bills.add(bill);
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public List<Payment> getPendingPayments() {
		return pendingPayments;
	}

	public void setPendingPayments(Payment payment) {
		this.pendingPayments.add(payment);
	}

	public String getConnAddress() {
		return connAddress;
	}

	public void setConnAddress(String address) {
		this.connAddress = address;
	}
	
}
