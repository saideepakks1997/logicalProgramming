package instagram;

import java.util.*;

import user.User;

public class Instagram {
	private Map<String,User> users = new HashMap();
	
	
	public Map<String,User> getUsers() {
		return users;
	}
	public void setUser(User user) {
		this.users.put(user.getUser_name(), user);
	}
	
	public User getValidUser(String user_name, String password) {
		Boolean isValid = this.users.get(user_name).validatePassword(password);
		if(isValid != null && isValid == true)
			return this.users.get(user_name);
		return null;
	}
}
