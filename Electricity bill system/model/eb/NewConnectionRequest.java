package eb;

import connection.TypeOfConnection;
import consumer.Consumer;

public class NewConnectionRequest {
//	private String user_name;
	private int consumerNo;
	private String address;
	private TypeOfConnection connType;
//	private Consumer consumer;
	
	public NewConnectionRequest(int consumerNo, String address, TypeOfConnection connType) {
		this.setConsumerNo(consumerNo);
		this.setAddress(address);
		this.setConnType(connType);
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

	public int getConsumerNo() {
		return consumerNo;
	}

	public void setConsumerNo(int consumerNo) {
		this.consumerNo = consumerNo;
	}

	
}
