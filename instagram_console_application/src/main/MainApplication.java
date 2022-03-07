package main;

import instagram.Instagram;
import user_inputs.UserInputs;

public class MainApplication {
	public static void main(String args[]) {
		
		Instagram instagram = new Instagram();
		UserInputs inputs = new UserInputs(instagram);
		inputs.createUsers();
		inputs.landingPage();
	}

	
}
