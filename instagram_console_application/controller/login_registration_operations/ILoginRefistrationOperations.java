package login_registration_operations;

import java.util.List;

import user.InstaUser;

public interface ILoginRefistrationOperations {
	public boolean checkIfUserNameIsCorrect(String user_name);
	public List<String> checkIfPasswordIsValid(String password);
	public InstaUser registerUser(String name,String user_name,String password);
	public InstaUser loginUser(String user_name, String password);
}		
