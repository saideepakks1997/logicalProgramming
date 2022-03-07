package instagram;

import java.util.*;

import post.Post;
import user.User;

public class Instagram {
	private int postId = 0;
	private int maxPostInFeeds = 10; 
	//user_name and user
	private Map<String,User> users = new HashMap<String, User>();
	//postId and post
	private Map<Integer, Post> posts = new HashMap<Integer, Post>();
	
	public Map<String,User> getUsers() {
		return users;
	}
	public void setUser(User user) {
		this.users.put(user.getUser_name(), user);
	}
//	Check if credentials are correct
	public User getValidUser(String user_name, String password) {
	//	if no user found on the user_name isValid returns null
		Boolean isValid = this.users.get(user_name).validatePassword(password);
		if(isValid != null && isValid == true)
			return this.users.get(user_name);
		return null;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public Map<Integer, Post> getPosts() {
		return posts;
	}
	public void setPost(Post post) {
		this.posts.put(post.getPostId(), post);
	}
	public int getMaxPostInFeeds() {
		return maxPostInFeeds;
	}
	public void setMaxPostInFeeds(int maxPosts) {
		this.maxPostInFeeds = maxPosts;
	}
}
