package instagram_operations;

import java.time.LocalDateTime;
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
//	Register user
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
//	login the user
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
			user.getFollowing().remove(searching_user_name);
			searchedUser.getFollowers().remove(searchedUser.getName());
			removeAllPostsToUnfollower(user,searching_user_name);
			display.displaySuccess("Unfollowed Successfully");
		}
	}
//	if sai  unfollows deepak . deepak posts should not be viewed in sai feeds
	private void removeAllPostsToUnfollower(User user, String searching_user_name) {
		Set<Integer> allPostsOfUser = user.getSelfPosts();
		User follower = instagram.getUsers().get(searching_user_name);
		for(Integer post: allPostsOfUser) {
			follower.getFeedPosts().remove(post);
		}
		
	}
//	if Sai newly follows deepak all deepak posts should be viewed by sai 
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
		Set<Integer> postsId = user.getFeedPosts();
		Post post = null;
		for(Integer postId: postsId) {
			post = instagram.getPosts().get(postId);
			
			display.displayPost(post.getContent(),post.getPostCreatedTime());
			i++;
			if(i == 10)
				break;
		}
	}
}
