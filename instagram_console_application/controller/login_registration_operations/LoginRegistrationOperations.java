package login_registration_operations;

import java.util.ArrayList;
import java.util.List;

import instagram.Instagram;
import user.InstaUser;

public class LoginRegistrationOperations implements ILoginRefistrationOperations{
	Instagram instagram = null;
	
	public  LoginRegistrationOperations(Instagram instagram) {
		this.instagram = instagram;
	}
	//Before registration user_name is unique
	@Override
	public boolean checkIfUserNameIsCorrect(String user_name) {
		return instagram.getUsers().containsKey(user_name);
	}
//	check if password follows all the standards
	@Override
	public List<String> checkIfPasswordIsValid(String password) {
		int lowerCaseCount = 0;
		int upperCaseCount = 0;
		int specialCharacterCount = 0;
		int numCount = 0;
		
		int len = password.length();
		List<String> errors = new ArrayList<>();
		if(!(len >= instagram.getPasswordMinLen() && len <= instagram.getPasswordMaxLen()))
			errors.add("password length should be between 8 and 12");
		if(password.contains(" "))
			errors.add("password should not contain spaces");
		
		for(int i=0; i<len; i++) {
			char a = password.charAt(i);
			if(upperCaseCount == 0 && a >= 65 && a <= 90)
				upperCaseCount++;
			if(lowerCaseCount == 0 && a >= 97 && a <= 122)
				lowerCaseCount++;
			if(numCount == 0 && a >= 48 && a <= 57)
				numCount++;
			
			if(
				specialCharacterCount == 0 
					&& 
			   (
				  (a >= 33 && a <= 47)
					||
				  (a >= 58 && a <= 64)
			   )
			   )
					specialCharacterCount++;
			
			if(lowerCaseCount > 0 && upperCaseCount > 0 && numCount > 0 && specialCharacterCount > 0)
				break;
			}
			if(lowerCaseCount == 0)
				errors.add("password should contain atleast one lowercase letter");
			if(upperCaseCount == 0)
				errors.add("password should contain atleast one uppercase letter");
			if(specialCharacterCount == 0)
				errors.add("password should contain atleast one special character letter");
			if(numCount == 0)
				errors.add("password should contain atleast one number");
			
		return errors;
	}
	
//	Register user
	@Override
	public InstaUser registerUser(String name, String user_name, String password) {
			InstaUser user = new InstaUser(name, user_name, password);
			instagram.setUser(user);
			return user;
		}
	
//	login the user
	@Override
	public InstaUser loginUser(String user_name, String password) {
		InstaUser user = instagram.getValidUser(user_name, password);
		return user;
	}
	
}
