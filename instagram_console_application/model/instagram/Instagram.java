package instagram;

import java.util.*;
import user.InstaUser;

public class Instagram {
	private int maxPostInFeeds = 10; 
	private int passwordMinLen = 8;
	private int passwordMaxLen = 15;
	
	
	
	public Instagram() {
		InstaUser u1 = new InstaUser("Sai Deepak", "sai", "sai");
		this.setUser(u1);
		InstaUser u2 = new InstaUser("Sachin Tendulkar", "sachin", "sachin");
		this.setUser(u2);
		InstaUser u3 = new InstaUser("Virat Kholi", "virat", "virat");
		this.setUser(u3);
	}
	
	//user_name and user
	private Map<String,InstaUser> users = new HashMap<String, InstaUser>();


	public Map<String,InstaUser> getUsers() {
		return users;
	}
	public void setUser(InstaUser user) {
		this.users.put(user.getUser_name(), user);
	}
//	Check if credentials are correct
	public InstaUser getValidUser(String user_name, String password) {
	//	if no user found on the user_name isValid returns null
		Boolean isValid = this.users.get(user_name).validatePassword(password);
		if(isValid != null && isValid == true)
			return this.users.get(user_name);
		return null;
	}

	public int getMaxPostInFeeds() {
		return maxPostInFeeds;
	}
	public void setMaxPostInFeeds(int maxPosts) {
		this.maxPostInFeeds = maxPosts;
	}
	public int getPasswordMinLen() {
		return passwordMinLen;
	}
	public void setPasswordMinLen(int passwordMinLen) {
		this.passwordMinLen = passwordMinLen;
	}
	public int getPasswordMaxLen() {
		return passwordMaxLen;
	}
	public void setPasswordMaxLen(int passwordMaxLen) {
		this.passwordMaxLen = passwordMaxLen;
	}
}
