package eb;

import java.time.LocalDate;
import java.time.LocalDateTime;

import connection.TypeOfConnection;

public class NewConnectionRequest {
	private int statusNo;
	private long requestNo;
	private long consumerNo;
	private String address;
	private TypeOfConnection connType;
	private LocalDateTime requestedTime;
	private LocalDateTime lastUpdatedTime; 
	
	public NewConnectionRequest(long consumerNo, String address, TypeOfConnection connType,long requestNo) {
		this.setConsumerNo(consumerNo);
		this.setAddress(address);
		this.setConnType(connType);
		this.setRequestNo(consumerNo);
		this.setRequestedTime(LocalDateTime.now());
	}
	
	public String toString() {
		String request = "ConsumerNo :- "+this.getConsumerNo()+"\n"
						+ "Address :- "+this.getAddress()+"\n"
						+ "Type of connection requested :- "+this.getConnType()+"\n"
						+ "Request Status :- "+RequestStatus.values()[this.statusNo].displayName();
		return request;
	}
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public TypeOfConnection getConnType() {
		return connType;
	}

	public void setConnType(TypeOfConnection connType) {
		this.connType = connType;
	}

	public long getConsumerNo() {
		return consumerNo;
	}

	public void setConsumerNo(long consumerNo) {
		this.consumerNo = consumerNo;
	}

	public long getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(long requestNo) {
		this.requestNo = requestNo;
	}

	public int getStatusNo() {
		return statusNo;
	}

	public void setStatusNo() {
		this.statusNo++;
	}

	public LocalDateTime getRequestedTime() {
		return requestedTime;
	}

	public void setRequestedTime(LocalDateTime requestedTime) {
		this.requestedTime = requestedTime;
	}

	public LocalDateTime getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	
}
