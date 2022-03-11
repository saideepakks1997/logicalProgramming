package common_operations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import instagram.Instagram;
import user.InstaUser;

public class CommonOperations implements ICommonOperations{
	Instagram instagram = null;
	InstaUser profile_owner = null;
	
	public CommonOperations(Instagram instagram) {
		this.instagram  = instagram;
	}
	@Override
	public List<String> getSearchedUsers(String searching_user_name) {
		Set<String> user_names = instagram.getUsers().keySet();
		Set<String> result_users = new TreeSet<>();
		int len = searching_user_name.length();
		for(String user:user_names) {
			String temp_user = null;
			try {
				temp_user = user.substring(0,len);
			}
			catch (StringIndexOutOfBoundsException e) {
				
			}
			if(searching_user_name.equalsIgnoreCase(temp_user) 
					&& 
				!profile_owner.getUser_name().equals(user) )
				result_users.add(user);
		}
		return new ArrayList<>(result_users);
	}
	@Override
	public void setProfileOwner(InstaUser profile_owner) {
		this.profile_owner = profile_owner;
	}
	@Override
	public LocalDate getValidDate(String dob) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			LocalDate time = LocalDate.parse(dob, formatter);
			return time;
		}
		catch (Exception e) {
			return null;
		}
	}
}
