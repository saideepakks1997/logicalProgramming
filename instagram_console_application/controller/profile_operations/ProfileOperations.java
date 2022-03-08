package profile_operations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import comparator.PostObjComparator;
import comparator.PostIdComparator;
import instagram.Instagram;
import post.Post;
import user.InstaUser;

public class ProfileOperations implements IProfileOperations{
	Instagram instagram = null;
	
	public ProfileOperations(Instagram instagram) {
		this.instagram = instagram;
	}

// 	create post
	@Override
	public void createPost(InstaUser user, String content,LocalDateTime time) {
		int postId = instagram.getPostId();
		Post post = new Post(postId, user.getUser_name(), content, time);
		
		user.setSelfPosts(postId);
		instagram.setPost(post);
		addPostToFollwers(user,postId);
	}
	
//	if sai post an content it should reflect to all his followers
	private void addPostToFollwers(InstaUser user, int postId) {
		Set<String> followers = user.getFollowers();
		// user can view his own post
		user.setFeedPosts(postId);
		for(String follower:followers) {
			InstaUser u = instagram.getUsers().get(follower);
			u.setFeedPosts(postId);
		}
	}
	
//	check if user searched is followed or unfollowed.
	@Override
	public String checkFollowOrUnfollowStatus(InstaUser user, String user_name) {
		InstaUser profile = instagram.getUsers().get(user_name);
		String status = null; 
		if(profile != null) {
			if(user.getFollowing().contains(profile.getUser_name())) {
				status = "You are Following "+user_name+"\n Do you want to Un follow";
				return status;
			}
			else {
				status = "You are Not Following "+user_name+"\n Do you want to follow";
				return status;
			}
		}
		status = "";
		return status;
	}
	
	@Override
	public String changeFollowStatus(InstaUser user,String searching_user_name) {
		InstaUser searchedUser = instagram.getUsers().get(searching_user_name);
	//	While follow update profile owner and also searchedUser(i.e)Add following for
	//  profile owner and add follower for searchedUser
		String status = (user.getFollowing().contains(searching_user_name)) ? "UN FOLLOW":"FOLLOW";
		if(status == "FOLLOW") {
			user.setFollowing(searching_user_name);
			searchedUser.setFollower(user.getUser_name());
			
			addAllPostsToNewFollwer(user,searching_user_name);
			return "Followed Successfully";
		}
		else {
			user.getFollowing().remove(searching_user_name);//virat,sachin
			searchedUser.getFollowers().remove(user.getName());//virat followers sai
			removeAllPostsToUnfollower(user,searchedUser);
			return "Unfollowed Successfully";
		}
	}
//	if Sai newly follows virat all virat posts should be viewed by sai 
	private void addAllPostsToNewFollwer(InstaUser user, String searching_user_name) {
		Set<Integer> selfPosts = instagram.getUsers().get(searching_user_name).getSelfPosts();
		for(Integer selfPost:selfPosts) {
			user.setFeedPosts(selfPost);
		}
	}
	
//	if sai  unfollows virat . virat posts should not be viewed in sai feeds
	private void removeAllPostsToUnfollower(InstaUser user, InstaUser searchedUser) {
		Set<Integer> allPostsOfUnFollowedUser = searchedUser.getSelfPosts();
		//removing post 
		for(Integer post: allPostsOfUnFollowedUser) {
			user.getFeedPosts().remove(post);
		}
	}
	
// 	view posts limited to 10
	@Override
	public Set<Post> getPosts(InstaUser user) {
		int i=0;
		Set<Integer> postIds = user.getFeedPosts();
		Post post = null;
		//No posts for display
		if(postIds.size() == 0) {
			return null;	
		}
		else {
			Set<Post> posts = new TreeSet<Post>(new PostObjComparator());
			for(Integer postId: postIds) {
				post = instagram.getPosts().get(postId);
				posts.add(post);
				i++;
				if(i == instagram.getMaxPostInFeeds())
					break;
			}
			return posts;
		}
	}

	@Override
	public List<String> getSearchedUsers(String searching_user_name) {
		Set<String> user_names = instagram.getUsers().keySet();
		Set<String> result_users = new TreeSet<>();
		int len = searching_user_name.length();
		for(String user:user_names) {
			String temp_user = user.substring(0,len);
			if(searching_user_name.equalsIgnoreCase(temp_user))
				result_users.add(user);
		}
		return new ArrayList<>(result_users);
	}
}
