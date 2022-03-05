package instagram_operations;

import user.User;

public interface IInstagramOperations {
	public void registerUser(String name,String user_name,String password);

	public User loginUser(String user_name, String password);

	public void viewProfile(User user, String user_name);
}
