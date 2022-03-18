package eb;

import java.time.LocalDateTime;

import connection.TypeOfConnection;

public class ChangeOfConnectionRequest {
//	private String user_name;
	private int statusNo;
	private long requestNo;
	private long consumerNo;
	private long serviceNo;
	private TypeOfConnection connType;
	private LocalDateTime requestedTime;
	private LocalDateTime lastUpdatedTime; 

	public ChangeOfConnectionRequest (long consumerNo,long serviceNo, TypeOfConnection connType,long requestNo) {
		this.setConsumerNo(consumerNo);
		this.setServiceNo(serviceNo);
		this.setConnType(connType);
		this.setRequestNo(requestNo);
		this.setRequestedTime(LocalDateTime.now());
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

	public void setStatusNo(int statusNo) {
		++this.statusNo;
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
