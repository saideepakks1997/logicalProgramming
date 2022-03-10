package post_operations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import post.Comment;
import post.Post;
import user.InstaUser;

public interface ILikeCommentOperation {

	public String getPostLikeStatus();

	public List<Comment> getAllComments();

	public Set<String> getLikedUsers();

	public String addOrRemoveLike();

	public String addComment(String content, LocalDateTime time);
	
	public void setPostProfileOwner(Post post,InstaUser profile_user);

}
