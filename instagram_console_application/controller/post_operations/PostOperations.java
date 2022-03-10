package post_operations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import comparator.PostObjComparator;
import instagram.Instagram;
import post.Comment;
import post.Post;
import user.InstaUser;

public class PostOperations implements IPostOperations{
	Instagram instagram = null;
	InstaUser profile_owner = null;
	
	public PostOperations(Instagram instagram) {
		this.instagram = instagram;
	}
	@Override
	public void createPost( String content, LocalDateTime time,Set<String> tags) {
		Post post = new Post( profile_owner.getUser_name(), content, time);
		if(tags != null && tags.size()> 0)
			post.setTags(tags);
		profile_owner.setSelfPostObjs(post);
		addPostToFollwers(profile_owner,post);
	}
	
	private void addPostToFollwers(InstaUser user,Post post) {
		Set<String> followers = user.getFollowers();
		
		// user can view his own post
		user.setFeedPostObjs(post);
		for(String follower:followers) {
			InstaUser u = instagram.getUsers().get(follower);
			u.setFeedPostObjs(post);
		}
	}
	
	@Override
	public Set<Post> getPosts() {
		int i=0;
		
		Set<Post> allFeedPosts = this.profile_owner.getFeedPostObjs();
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
	public void addLikeToPost(Post post, String user_name) {
		post.setLike(user_name);
		}

	@Override
	public void removeLikeFromPost(Post post, String user_name) {
		post.getLikes().remove(user_name);
	}

	@Override
	public List<Comment> getAllComments(Post post) {
		return post.getComments();
	}

	@Override
	public Set<String> getLikedUsers(Post post) {
		
		return post.getLikes();
	}
	@Override
	public void setProfileOwner(InstaUser profile_owner) {
		this.profile_owner = profile_owner;
	}
	
	@Override
	public List<Post> getSelfPosts() {
		List<Post> selfPosts = new ArrayList<Post>(this.profile_owner.getSelfPostObjs());
		return selfPosts;
	}
	@Override
	public String deletePost(Post post) {
		profile_owner.getSelfPostObjs().remove(post);
		Set<String> followers = profile_owner.getFollowers();
		for(String follower: followers) {
			InstaUser user = instagram.getUsers().get(follower);
			user.getFeedPostObjs().remove(post);
		}
		return "Post deleted successfully";
	}
	@Override
	public List<String> getTaggedUsers() {
		
		return null;
	}
	@Override
	public String deleteTag(Post post, String taggedUser) {
		post.getTags().remove(taggedUser);
		return "Tag removed successfully";
	}

}
