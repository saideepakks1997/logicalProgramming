package consumer;

public class Admin extends User implements ICredentials   {
	private String user_name ;
	private String password;
	public Admin(String user_name, String password) {
		this.setUser_name(user_name);
		this.setPassword(password);
	}

	@Override
	public String getUser_name() {
		return user_name;
	}
	@Override
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}
}
