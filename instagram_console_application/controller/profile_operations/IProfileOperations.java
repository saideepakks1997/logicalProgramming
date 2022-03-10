package profile_operations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import post.Post;
import user.InstaUser;

public interface IProfileOperations {
	public void setProfileOwner(InstaUser profile_owner);
	public String checkFollowOrUnfollowStatus( String user_name);
	public String changeFollowStatus(String searching_user_name);
	public Set<Post> getPosts(InstaUser user);
//	public List<String> getSearchedUsers(String searching_user_name);
	public InstaUser getMyProfile();
	public String getProfileFields();
	public void setField(Object updateField, String fieldName);
}
