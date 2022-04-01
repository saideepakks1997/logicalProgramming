package consumer;

public class Admin extends User    {
	
	private String user_name ;
	private String password;
	public Admin(String user_name, String password) {
		this.setUser_name(user_name);
		this.setPassword(password);
	}

	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}
}
