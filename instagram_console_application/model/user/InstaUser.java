package user;

import java.util.*;

import comparator.PostObjComparator;
import post.Post;

public class InstaUser extends User{
//	private static final Comparator<Integer> PostsComparator = new UserNameComparator();
	private String user_name;
	private String bio;
	private String password;
	
	private Set<String> followings = new TreeSet<String>();
	private Set<String> followers = new TreeSet<String>();
	
	
	private Set<Post> selfPostObjs = new TreeSet<Post>(new PostObjComparator());
	private Set<Post> feedPostObjs = new TreeSet<Post>(new PostObjComparator());
	
	public InstaUser(String name,String user_name,String password) {
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
	
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
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
	
	public Set<Post> getSelfPostObjs() {
		return selfPostObjs;
	}


	public void setSelfPostObjs(Post post) {
		this.selfPostObjs.add(post);
//		this.selfPostObjs = selfPostObjs;
	}


	public Set<Post> getFeedPostObjs() {
		return feedPostObjs;
	}


	public void setFeedPostObjs(Post post) {
		this.feedPostObjs.add(post);
	}
	
	public String toString() {
		String bio = this.getBio() == null ? " ":this.getBio();
		String dob = this.getDob() == null ? " ":this.getDob().toString();
		String email = this.getEmail() == null ? " ":this.getEmail();
		String phNo = this.getPhNo() == null ? " ":this.getPhNo().toString();
		String website = this.getWebsite() == null ? " ":this.getWebsite();
		String profile = "Name:-"+this.name+"\n"
				+ "1.User Name:-"+this.user_name+"\n"
				+ "2.No of Following :-"+this.getFollowing().size()+"\n"
				+ "3.No of Followers :-" + this.getFollowers().size()+"\n"
				+ "4.Bio:- "+ bio+"\n"
				+ "5.Date of birth:- "+dob+"\n"
				+ "6.Email:- "+email+"\n"
				+ "7.Phone no:- "+phNo+"\n"
				+ "8.Website:- "+website;
		return profile;
	}
}
