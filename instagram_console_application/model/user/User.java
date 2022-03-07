package user;

import java.util.*;

import comparator.PostsComparator;

public class User {
	private static final Comparator<Integer> PostsComparator = new PostsComparator();
	private String user_name;
	private String password;
	private String name;
	private String website;
	private String bio;
	private String email;
	private String phNo;
	private Gender gender;
	
//	private Set<User> following = new TreeSet();
//	private Set<User> followers = new TreeSet();
	
	private Set<String> followings = new TreeSet<String>();
	private Set<String> followers = new TreeSet<String>();
	
	private Set<Integer> selfPosts = new TreeSet<Integer>(PostsComparator);
	private Set<Integer> feedPosts = new TreeSet<Integer>(PostsComparator);
	
	public User(String name,String user_name,String password) {
		this.setUser_name(user_name);
		this.setPassword(password);
		this.setName(name);
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhNo() {
		return phNo;
	}
	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}

	public Set<String> getFollowing() {
		return followings;
	}

	public void setFollowing(String following) {
		this.followings.add(following);
	}

	public Set<String> getFollowers() {
		return followers;
	}

	public void setFollower(String follower) {
		this.followers.add(follower);
	}

	public Set<Integer> getSelfPosts() {
		return selfPosts;
	}

	public void setSelfPosts(Integer selfPostNO) {
		this.selfPosts.add(selfPostNO);
	}

	public Set<Integer> getFeedPosts() {
		return feedPosts;
	}

	public void setFeedPosts(Integer feedPost) {
		this.feedPosts.add(feedPost);
	}
}
