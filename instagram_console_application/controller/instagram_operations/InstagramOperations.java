package instagram_operations;

import display.Display;
import instagram.Instagram;
import user.User;

public class InstagramOperations implements IInstagramOperations{
	Instagram instagram = null;
	Display display = new Display();
	public InstagramOperations(Instagram instagram) {
		this.instagram = instagram;
	}
	@Override
	public void registerUser(String name, String user_name, String password) {
		if(!isUserNamePicked(user_name)) {
			User user = new User(name, user_name, password);
			instagram.setUser(user);
			display.displaySuccess(name+" you registered successfully");;
		}
		else
			display.displayError("User name already exists");
			
	}
	
	public boolean isUserNamePicked(String user_name) {
		return this.instagram.getUsers().containsKey(user_name);
	}
	
	@Override
	public User loginUser(String user_name, String password) {
		User user = instagram.getValidUser(user_name, password);
		if(user == null) {
			display.displayError("User name or password invalid");
			return null;
		}
		return user;
	}
	@Override
	public void viewProfile(User user, String user_name) {
		User profile = instagram.getUsers().get(user_name);
		if(profile != null) {
//			if(profile.get)
		}
		else
			display.displayError(user_name+" not found");
	}
}
