package profile_operations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import post.Post;
import user.InstaUser;

public interface IProfileOperations {
	public void createPost(InstaUser user, String content,LocalDateTime time);
	public String checkFollowOrUnfollowStatus(InstaUser user, String user_name);
	public String changeFollowStatus(InstaUser user,String searching_user_name);
	public Set<Post> getPosts(InstaUser user);
	public List<String> getSearchedUsers(String searching_user_name);
}
