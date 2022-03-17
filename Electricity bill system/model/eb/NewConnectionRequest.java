package eb;

import connection.TypeOfConnection;
import consumer.Consumer;

public class NewConnectionRequest {
//	private String user_name;
	private long requestNo;
	private long consumerNo;
	private String address;
	private TypeOfConnection connType;
//	private Consumer consumer;
	
	public NewConnectionRequest(long consumerNo, String address, TypeOfConnection connType,long requestNo) {
		this.setConsumerNo(consumerNo);
		this.setAddress(address);
		this.setConnType(connType);
		this.setRequestNo(consumerNo);
	}
	
	public String toString() {
		String request = "ConsumerNo :- "+this.getConsumerNo()+"\n"
						+ "Address :- "+this.getAddress()+"\n"
						+ "Type of connection requested :- "+this.getConnType();
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

	
}
