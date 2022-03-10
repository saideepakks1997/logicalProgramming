package post_operations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import post.Comment;
import post.Post;
import user.InstaUser;

public interface IPostOperations {

	public void createPost( String content, LocalDateTime time,Set<String> tags);
	public Set<Post> getPosts();
	public void addLikeToPost(Post post, String user_name);
	public void removeLikeFromPost(Post post, String user_name);
	public List<Comment> getAllComments(Post post);
	public Set<String> getLikedUsers(Post post);
	public void setProfileOwner(InstaUser profile_owner);
	public List<Post> getSelfPosts();
	public String deletePost(Post post);
	public List<String> getTaggedUsers();
	public String deleteTag(Post post, String taggedUser);
}
