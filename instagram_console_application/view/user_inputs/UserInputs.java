package user_inputs;

import java.util.Scanner;

import instagram.Instagram;
import instagram_operations.IInstagramOperations;
import instagram_operations.InstagramOperations;
import user.User;

public class UserInputs {
	Instagram instagram = null;
	IInstagramOperations operations = null;
	Scanner sc = new Scanner(System.in);
	
	public UserInputs(Instagram instagram) {
		this.instagram = instagram;
		this.operations = new InstagramOperations(this.instagram);
	}
	
	public void landingPage() {
		boolean loop = true;
		while(loop) {
			System.out.println("1->Register user\n"
					+ "2->Login user\n"
					+ "3->Exit");
			int opt = sc.nextInt();
			switch(opt) {
				case 1:resgistrationInputs();
					break;
				case 2:loginInputs();
					break;
				case 3: System.out.println("Thank you");
					loop = false;
					break;
				default: System.out.println("Enter correct option");
					break;
			}
		}
	}

	private void resgistrationInputs() {
		sc.nextLine();
		System.out.println("Enter your name");
		String name = sc.nextLine();
		
		System.out.println("Enter user name ");
		String user_name = sc.next();
		
		System.out.println("Enter password to set ");
		String password = sc.next();
		
		operations.registerUser(name, user_name, password);
	}
	
	private void loginInputs() {
		System.out.println("Enter user name");
		String user_name = sc.next();
		
		System.out.println("Enter password ");
		String password = sc.next();
		
		User user = operations.loginUser(user_name, password);
		
		if(user != null)
			askOptions(user);
	}

	private void askOptions(User user) {
		boolean loop = true;
		while(loop) {
			System.out.println("Select option\n"
					+ "1-> Post content\n"
					+ "2-> Follow or UnFollow\n"
					+ "3-> View Posts");
			int opt = sc.nextInt();
			switch (opt) {
			case 1:
				break;
			case 2: followOrUnfollow(user);
				break;
			case 3:
				break;
			case 4: System.out.println("");
				break;
			default: System.out.println("Enter correct option");
				break;
			}
		}
	}

	private void followOrUnfollow(User user) {
		System.out.println("Enter user name");
		String user_name = sc.next();
		
		operations.viewProfile(user, user_name);
	}
		
}
