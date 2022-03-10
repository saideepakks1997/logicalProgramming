package profile_operations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import comparator.PostObjComparator;
import instagram.Instagram;
import post.Post;
import user.InstaUser;

public class ProfileOperations implements IProfileOperations{
	public Instagram instagram = null;
	public InstaUser profile_owner = null;

	public ProfileOperations(Instagram instagram, InstaUser profile_owner) {
		this.instagram = instagram;
		this.profile_owner = profile_owner;
	}

	public ProfileOperations(Instagram instagram) {
			this.instagram = instagram;
	}

	//	check if user searched is followed or unfollowed.
	@Override
	public String checkFollowOrUnfollowStatus( String searched_user_name) {
		InstaUser searchedProfile = instagram.getUsers().get(searched_user_name);
		String status = null; 
		if(searchedProfile != null) {
			
			if(this.profile_owner.getFollowing().contains(searchedProfile.getUser_name())) {
				status = "You are Following "+searched_user_name+"\n Do you want to Un follow";
				return status;
			}
			else {
				status = "You are Not Following "+searched_user_name+"\n Do you want to follow";
				return status;
			}
		}
		status = "";
		return status;
	}
	
	@Override
	public String changeFollowStatus(String searching_user_name) {
		InstaUser searchedUser = instagram.getUsers().get(searching_user_name);
	//	While follow update profile owner and also searchedUser(i.e)Add following for
	//  profile owner and add follower for searchedUser
		String status = (profile_owner.getFollowing().contains(searching_user_name)) ? "UN FOLLOW":"FOLLOW";
		if(status == "FOLLOW") {
			profile_owner.setFollowing(searching_user_name);
			searchedUser.setFollower(profile_owner.getUser_name());
			addAllPostsToNewFollwer(profile_owner,searchedUser);
			return "Followed Successfully";
		}
		else {
			profile_owner.getFollowing().remove(searching_user_name);//virat,sachin
			searchedUser.getFollowers().remove(profile_owner.getName());//virat followers sai
			removeAllPostsToUnfollower(profile_owner,searchedUser);
			return "Unfollowed Successfully";
		}
	}
//	if Sai newly follows virat all virat posts should be viewed by sai 
	private void addAllPostsToNewFollwer(InstaUser profile_owner, InstaUser searchedUser) {
		Set<Post> selfPosts = searchedUser.getSelfPostObjs();
		for(Post post:selfPosts) {
			profile_owner.setFeedPostObjs(post);
		}
	}
	
//	if sai  unfollows virat . virat posts should not be viewed in sai feeds
	private void removeAllPostsToUnfollower(InstaUser profile_owner, InstaUser searchedUser) {
		Set<Post> postsOfUnFollwedUser = searchedUser.getSelfPostObjs();
		for(Post post:postsOfUnFollwedUser) {
			profile_owner.getFeedPostObjs().remove(post);
		}
	}
	
// 	view posts limited to 10
	@Override
	public Set<Post> getPosts(InstaUser user) {
		int i=0;
		
		Set<Post> allFeedPosts = user.getFeedPostObjs();
		Post post = null;
		//No posts for display
		if(allFeedPosts.size() == 0) {
			return null;	
		}
		else if(allFeedPosts.size() > 0) {
			Set<Post>  feedPosts= new TreeSet<>(new PostObjComparator());
			for(Post p: allFeedPosts) {
				feedPosts.add(p);
				i++;
				if(i == instagram.getMaxPostInFeeds())
					break;
			}
			return feedPosts;
		}
		return null;
	}

	@Override
	public InstaUser getMyProfile() {
		return this.profile_owner;
	}

	@Override
	public String getProfileFields() {
		String bio = profile_owner.getBio() == null ? " ":profile_owner.getBio();
		String dob = profile_owner.getDob() == null ? " ":profile_owner.getDob().toString();
		String email = profile_owner.getEmail() == null ? " ":profile_owner.getEmail();
		String phNo = profile_owner.getPhNo() == null ? " ":profile_owner.getPhNo().toString();
		String website = profile_owner.getWebsite() == null ? " ":profile_owner.getWebsite();
		String profile = "1.Name:-"+profile_owner.getName()+"\n"
				+ "2.User Name:-"+profile_owner.getUser_name()+"\n"
				+ "3.Bio:- "+ bio+"\n"
				+ "4.Date of birth:- "+dob+"\n"
				+ "5.Email:- "+email+"\n"
				+ "6.Phone no:- "+phNo+"\n"
				+ "7.Website:- "+website+"\n"
				+ "8.Back to previous menu";
		return profile;
	}

	@Override
	public void setField(Object updateField, String fieldName) {
		switch(fieldName) {
			case "name": this.profile_owner.setName((String)updateField);
				break;
			case "user_name": this.profile_owner.setUser_name((String)updateField);
				break;
			case "bio": this.profile_owner.setBio((String)updateField);
				break;
			case "dob": 
				break;
			case "email": this.profile_owner.setEmail((String)updateField);
				break;
			case "phNo": this.profile_owner.setPhNo((Long)updateField);
				break;
			case "website": this.profile_owner.setWebsite((String)updateField);
				break;
		}
		
	}

	@Override
	public void setProfileOwner(InstaUser profile_owner) {
		this.profile_owner = profile_owner;
	}

}
