package eb;

import connection.TypeOfConnection;

public class ChangeOfConnectionRequest {
//	private String user_name;
	private long requestNo;
	private long consumerNo;
	private long serviceNo;
	private TypeOfConnection connType;
	
	public ChangeOfConnectionRequest (long consumerNo,long serviceNo, TypeOfConnection connType,long requestNo) {
		this.setConsumerNo(consumerNo);
		this.setServiceNo(serviceNo);
		this.setConnType(connType);
		this.setRequestNo(requestNo);
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
}
