package eb;

import connection.TypeOfConnection;
import consumer.Consumer;

public class NewConnectionRequest {
	private String user_name;
	private String address;
	private TypeOfConnection connType;
//	private Consumer consumer;
	
	public NewConnectionRequest(String user_name, String address, TypeOfConnection connType) {
		this.setUser_name(user_name);
		this.setAddress(address);
		this.setConnType(connType);
	}
	
	public String toString() {
		String request = "User name :- "+this.getUser_name()+"\n"
						+ "Address :- "+this.getAddress()+"\n"
						+ "Type of connection requested :- "+this.getConnType();
		return request;
	}
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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

	
}
