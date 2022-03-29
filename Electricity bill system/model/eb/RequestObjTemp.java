package eb;

import java.io.Serializable;
import java.time.LocalDateTime;

import connection.TypeOfConnection;

public class RequestObjTemp implements Serializable{
	
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
	
	private RequestObjTemp(RequestObjTemp builder) {
		this.requestNo = builder.requestNo;
		this.consumerNo = builder.consumerNo;
		this.serviceNo = builder.serviceNo;
		this.connType = builder.connType;
		this.requestedTime = builder.requestedTime;
		this.lastUpdatedTime = builder.lastUpdatedTime;
		this.address = builder.address;
		this.isNewConnectionReq = builder.isNewConnectionReq;
	}
	
	public int getStatusNo() {
		return statusNo;
	}
	public long getRequestNo() {
		return requestNo;
	}
	public long getConsumerNo() {
		return consumerNo;
	}
	public long getServiceNo() {
		return serviceNo;
	}
	public TypeOfConnection getConnType() {
		return connType;
	}
	public LocalDateTime getRequestedTime() {
		return requestedTime;
	}
	public LocalDateTime getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public String getAddress() {
		return address;
	}
	public boolean isNewConnectionReq() {
		return isNewConnectionReq;
	}
	public boolean isRequestCompleted() {
		return isRequestCompleted;
	}
	
	public static class RequestObjTempBuilder{
		
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
		public RequestObjTempBuilder(long consumerNo, TypeOfConnection connType,long requestNo) {
			this.setConsumerNo(consumerNo);
			this.setConnType(connType);
			this.setRequestNo(requestNo);
			this.setRequestedTime(LocalDateTime.now());
			this.setLastUpdatedTime();
		}
		
		public void setStatusNo(int statusNo) {
			this.statusNo = statusNo;
		}
		public void setRequestNo(long requestNo) {
			this.requestNo = requestNo;
		}
		public void setConsumerNo(long consumerNo) {
			this.consumerNo = consumerNo;
		}
		public void setServiceNo(long serviceNo) {
			this.serviceNo = serviceNo;
		}
		public void setConnType(TypeOfConnection connType) {
			this.connType = connType;
		}
		public void setRequestedTime(LocalDateTime requestedTime) {
			this.requestedTime = requestedTime;
		}
		public void setLastUpdatedTime() {
			this.lastUpdatedTime = LocalDateTime.now();
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public void setNewConnectionReq(boolean isNewConnectionReq) {
			this.isNewConnectionReq = isNewConnectionReq;
		}
		public void setRequestCompleted(boolean isRequestCompleted) {
			this.isRequestCompleted = isRequestCompleted;
		}
		
	}
}
