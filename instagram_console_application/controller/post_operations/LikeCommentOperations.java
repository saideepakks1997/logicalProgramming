package post_operations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import instagram.Instagram;
import post.Comment;
import post.Post;
import user.InstaUser;

public class LikeCommentOperations implements ILikeCommentOperation{
	Instagram instagram = null;
	Post post = null;
	InstaUser profile_owner = null;
	
//	public LikeCommentOperations(Instagram instagram, Post post,InstaUser profile_owner) {
//		this.instagram = instagram;
//		this.post = post;
//		this.profile_owner = profile_owner;
//	}

	public LikeCommentOperations(Instagram instagram) {
		this.instagram = instagram;
	}

	@Override
	public String getPostLikeStatus() {
		boolean isLiked = post.getLikes().contains(profile_owner.getUser_name());
		String status = (isLiked == true) ?
				"liked do you want to unlike it": "Not liked do you want to like it";
		return status;
	}
	
	@Override
	public String addOrRemoveLike() {
		String user_name = profile_owner.getUser_name();
		boolean isLiked = post.getLikes().contains(user_name);
		if(isLiked) {
			post.getLikes().remove(user_name);
			return "Unliked Successfully";
		}
		else {
			post.setLike(user_name);
			return "Liked Successfully";
		}
			
	}
	

	@Override
	public List<Comment> getAllComments() {
		return post.getComments();
	}

	@Override
	public Set<String> getLikedUsers() {
		
		return post.getLikes();
	}

	@Override
	public String addComment(String content, LocalDateTime time) {
		Comment comment = new Comment(profile_owner.getName(),content,time);
		post.setComments(comment);
		return "Post has been commented successfully";
		
	}

	@Override
	public void setPostProfileOwner(Post post, InstaUser profile_owner) {
		this.post = post;
		this.profile_owner = profile_owner;
	}

	

	
	
}
