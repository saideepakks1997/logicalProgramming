package instagram_operations;

import java.time.LocalDateTime;

import user.User;

public interface IInstagramOperations {
	public User registerUser(String name,String user_name,String password);

	public User loginUser(String user_name, String password);

	public String checkFollowOrUnfollowStatus(User user, String user_name);

	public void changeFollowStatus(String status,User user,String searching_user_name);

	public void createPost(User user, String content,LocalDateTime time);

	public void viewPosts(User user);

	public boolean checkIfUserNameIsCorrect(String user_name);

	public boolean checkIfPasswordIsValid(String password);
}
