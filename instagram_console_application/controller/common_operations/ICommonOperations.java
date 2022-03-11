package common_operations;

import java.time.LocalDate;
import java.util.List;

import user.InstaUser;

public interface ICommonOperations {
	public List<String> getSearchedUsers(String searching_user_name);
	public void setProfileOwner(InstaUser profile_owner);
	public LocalDate getValidDate(String dob);
	
}
