package eb;

import connection.TypeOfConnection;

public class ChangeOfConnectionRequest {
	private String user_name;
	private long serviceNo;
	private TypeOfConnection connType;
	
	public ChangeOfConnectionRequest (String user_name,long serviceNo, TypeOfConnection connType) {
		this.setUser_name(user_name);
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
