package connection;

import java.util.ArrayList;
import java.util.List;

import bill.Bill;
import bill.Payment;
import consumer.Consumer;

public class Connection {
	
	private long serviceNo;
	private TypeOfConnection connectionType;
	private long currentUnit;
	private Consumer consumer;
	private String connAddress;
	double charges = 10;
	private List<Payment> pendingPayments = new ArrayList<>();
	private List<Bill> bills = new ArrayList<Bill>();
	
	public Connection(long serviceNo, TypeOfConnection connectionType,String connAddress, Consumer consumer) {
		this.setServiceNo(serviceNo);
		this.setConnectionType(connectionType);
		this.setConnAddress(connAddress);
		this.setConsumer(consumer);
		
	}
	
	
	public Long getServiceNo() {
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
	
	public double calculateBill(double units) {
		return this.charges * units;
	}
}
