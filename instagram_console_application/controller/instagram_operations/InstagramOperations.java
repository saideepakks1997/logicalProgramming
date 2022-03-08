package instagram_operations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import display.Display;
import instagram.Instagram;
import post.Post;
import user.User;

public class InstagramOperations implements IInstagramOperations{
	Instagram instagram = null;
	Display display = new Display();
	public InstagramOperations(Instagram instagram) {
		this.instagram = instagram;
	}

	@Override
	public boolean checkIfUserNameIsCorrect(String user_name) {
		return instagram.getUsers().containsKey(user_name);
	}
	
	@Override
	public boolean checkIfPasswordIsValid(String password) {
		int lowerCaseCount = 0;
		int upperCaseCount = 0;
		int specialCharacterCount = 0;
		int numCount = 0;
		
		int len = password.length();
		List<String> errors = new ArrayList<>();
		if(!(len >= instagram.getPasswordMinLen() && len <= instagram.getPasswordMaxLen()))
			errors.add("password length should be between 8 and 12");
		if(password.contains(" "))
			errors.add("password should not contain spaces");
		
		for(int i=0; i<len; i++) {
			char a = password.charAt(i);
			if(upperCaseCount == 0 && a >= 65 && a <= 90)
				upperCaseCount++;
			if(lowerCaseCount == 0 && a >= 97 && a <= 122)
				lowerCaseCount++;
			if(numCount == 0 && a >= 48 && a <= 57)
				numCount++;
			
			if(
				specialCharacterCount == 0 
					&& 
			   (
				  (a >= 33 && a <= 47)
					||
				  (a >= 58 && a <= 64)
			   )
			   )
					specialCharacterCount++;
			
			if(lowerCaseCount > 0 && upperCaseCount > 0 && numCount > 0 && specialCharacterCount > 0)
				break;
			}
			if(lowerCaseCount == 0)
				errors.add("password should contain atleast one lowercase letter");
			if(upperCaseCount == 0)
				errors.add("password should contain atleast one uppercase letter");
			if(specialCharacterCount == 0)
				errors.add("password should contain atleast one special character letter");
			if(numCount == 0)
			errors.add("password should contain atleast one numeber");
		
			if(errors.size() != 0) 
				display.displayPasswordError(errors);
			
		return errors.size() == 0;
	}
	
//	Register user
	@Override
	public User registerUser(String name, String user_name, String password) {
		if(!checkIfUserNameIsCorrect(user_name)) {
			User user = new User(name, user_name, password);
			instagram.setUser(user);
			display.displaySuccess(name+" you registered successfully");
			return user;
		}
		else {
			display.displayError("User name already exists");
			return null;
		}
			
			
	}
	
//	login the user
	@Override
	public User loginUser(String user_name, String password) {
//		User user = instagram.getUsers().
		User user = instagram.getValidUser(user_name, password);
		
		if(user == null) {
			display.displayError("password invalid");
			return null;
		}
		return user;
	}
//	check if user searched is followed or unfollowed.
	@Override
	public String checkFollowOrUnfollowStatus(User user, String user_name) {
		User profile = instagram.getUsers().get(user_name);
		if(profile != null) {
			if(user.getFollowing().contains(profile.getUser_name())) {
				display.displayProfileStatus(profile.getUser_name(), "Following");
				return "UN FOLLOW";
			}
			else {
				display.displayProfileStatus(profile.getUser_name(), "not Following");
				return "FOLLOW";
			}
		}
		else
			display.displayError(user_name+" not found");
		return null;
	}
//	Changing follow status
	@Override
	public void changeFollowStatus(String status,User user,String searching_user_name) {
		User searchedUser = instagram.getUsers().get(searching_user_name);
	//	While follow update profile owner and also searchedUser(i.e)Add following for
	//  profile owner and add follower for searchedUser
		if(status == "FOLLOW") {
			user.setFollowing(searching_user_name);
			searchedUser.setFollower(user.getUser_name());
			
			addAllPostsToNewFollwer(user,searching_user_name);
			display.displaySuccess("Followed Successfully");
		}
		else {
			user.getFollowing().remove(searching_user_name);//virat,sachin
			searchedUser.getFollowers().remove(user.getName());//virat followers sai
			removeAllPostsToUnfollower(user,searchedUser);
			display.displaySuccess("Unfollowed Successfully");
		}
	}
//	if sai  unfollows virat . virat posts should not be viewed in sai feeds
	private void removeAllPostsToUnfollower(User user, User searchedUser) {
		Set<Integer> allPostsOfUnFollowedUser = searchedUser.getSelfPosts();
		//removing post 
		for(Integer post: allPostsOfUnFollowedUser) {
			user.getFeedPosts().remove(post);
		}
	}
//	if Sai newly follows virat all virat posts should be viewed by sai 
	private void addAllPostsToNewFollwer(User user, String searching_user_name) {
		Set<Integer> selfPosts = instagram.getUsers().get(searching_user_name).getSelfPosts();
		for(Integer selfPost:selfPosts) {
			user.setFeedPosts(selfPost);
		}
	}
// 	create post
	@Override
	public void createPost(User user, String content,LocalDateTime time) {
		int postId = instagram.getPostId();
		instagram.setPostId(++postId);
		Post post = new Post(postId, user.getUser_name(), content, time);
		
		user.setSelfPosts(postId);
		instagram.setPost(post);
		addPostToFollwers(user,postId);
	}
//	if sai post an content it should reflect to all his followers
	private void addPostToFollwers(User user, int postId) {
		Set<String> followers = user.getFollowers();
		// user can view his own post
//		user.setFeedPosts(postId);
		for(String follower:followers) {
			User u = instagram.getUsers().get(follower);
			u.setFeedPosts(postId);
		}
	}
// 	view posts limited to 10
	@Override
	public void viewPosts(User user) {
		int i=0;
		Set<Integer> postIds = user.getFeedPosts();
		Post post = null;
		//No posts for display
		if(postIds.size() == 0) {
			display.displayError("No Posts available");
			return;
		}
		
		for(Integer postId: postIds) {
			post = instagram.getPosts().get(postId);
			String time = post.getPostCreatedTime().format(instagram.getFormat());
			display.displayPost(post.getPost_owner(),post.getContent(),time);
			i++;
			if(i == instagram.getMaxPostInFeeds())
				break;
		}
	}
	
	
}
