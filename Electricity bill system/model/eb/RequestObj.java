package eb;

import java.io.Serializable;
import java.time.LocalDateTime;

import connection.TypeOfConnection;

public class RequestObj implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int statusNo;
	private long requestNo;
	private long consumerNo;
	private long serviceNo;
	private TypeOfConnection connType;
	private LocalDateTime requestedTime;
	private LocalDateTime lastUpdatedTime;
	private String address;
	private boolean isNewConnectionReq;//new connection request or change of status request
	//if rejected or accepted returns true if the status is in the middle returns false
	private boolean isRequestCompleted;
	//Request for new connection 
	public RequestObj(long consumerNo, String address, TypeOfConnection connType,long requestNo) {
		this.setConsumerNo(consumerNo);
		this.setAddress(address);
		this.setConnType(connType);
		this.setRequestNo(requestNo);
		this.setRequestedTime(LocalDateTime.now());
		this.setLastUpdatedTime();
		this.setIsNewConnectionReq(true);
		this.setRequestCompleted(false);
	}
	//change of connection type
	public RequestObj(long consumerNo,long serviceNo, TypeOfConnection connType,long requestNo) {
		this.setConsumerNo(consumerNo);
		this.setServiceNo(serviceNo);
		this.setConnType(connType);
		this.setRequestNo(requestNo);
		this.setRequestedTime(LocalDateTime.now());
		this.setLastUpdatedTime();
		this.setIsNewConnectionReq(false);
		this.setRequestCompleted(false);
	}
	
	public int getStatusNo() {
		return statusNo;
	}
	public void setStatusNo() {
		this.statusNo++;
	}
	public long getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(long requestNo) {
		this.requestNo = requestNo;
	}
	public long getConsumerNo() {
		return consumerNo;
	}
	public void setConsumerNo(long consumerNo) {
		this.consumerNo = consumerNo;
	}
	public long getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(long serviceNo) {
		this.serviceNo = serviceNo;
	}
	public TypeOfConnection getConnType() {
		return connType;
	}
	public void setConnType(TypeOfConnection connType) {
		this.connType = connType;
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
	public void setLastUpdatedTime() {
		this.lastUpdatedTime = LocalDateTime.now();
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isNewConnectionReq() {
		return isNewConnectionReq;
	}
	public void setIsNewConnectionReq(boolean isNewConnectionReq) {
		this.isNewConnectionReq = isNewConnectionReq;
	}
	public boolean isRequestCompleted() {
		return isRequestCompleted;
	}
	public void setRequestCompleted(boolean isRequestCompleted) {
		this.isRequestCompleted = isRequestCompleted;
	}
}
