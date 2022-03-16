package eb;

import connection.TypeOfConnection;

public class ChangeOfConnectionRequest {
//	private String user_name;
	private int consumerNo;
	private long serviceNo;
	private TypeOfConnection connType;
	
	public ChangeOfConnectionRequest (int consumerNo,long serviceNo, TypeOfConnection connType) {
		this.setConsumerNo(consumerNo);
		this.setServiceNo(serviceNo);
		this.setConnType(connType);
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


	public int getConsumerNo() {
		return consumerNo;
	}

	public void setConsumerNo(int consumerNo) {
		this.consumerNo = consumerNo;
	}
}
