package user_inputs;

import java.time.LocalDateTime;
import java.util.*;


import display.Display;
import instagram.Instagram;
import instagram_operations.IInstagramOperations;
import instagram_operations.InstagramOperations;
import user.User;

public class UserInputs {
	Instagram instagram = null;
	IInstagramOperations operations = null;
	Display display = new Display();
	Scanner sc = new Scanner(System.in);
	
	public UserInputs(Instagram instagram) {
		this.instagram = instagram;
		this.operations = new InstagramOperations(this.instagram);
	}
	//landing page 
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
//	Get input for registration of user
	private void resgistrationInputs() {
		boolean loop = true;
		sc.nextLine();
		System.out.println("Enter your name");
		String name = sc.nextLine();
		String user_name = null;
		
		while(loop) {
			System.out.println("Enter user name ");
			user_name = sc.next();
			boolean isUserTaken = operations.checkIfUserNameIsCorrect(user_name);
			if(isUserTaken)
				display.displayError(user_name+" already taken please try different name");
			else 
				loop = false;
		}
		loop = true; 
		sc.nextLine();
		String password = null ;
		while(loop) {
			System.out.println("Enter password to set ");
			 password = sc.nextLine();
			boolean isValidPassword = operations.checkIfPasswordIsValid(password);
			if(isValidPassword)
				loop = false;
			}
		
		User user = operations.registerUser(name, user_name, password);
		
			operations.loginUser(user.getUser_name(), password);
		askOptions(user);
	}
//	Get user name and password for login
	private void loginInputs() {
		boolean loop = true;
		String user_name = null;
		boolean isUserAvailable = true;
		while(loop) {
			System.out.println("Enter user name");
			user_name = sc.next();
			isUserAvailable = operations.checkIfUserNameIsCorrect(user_name);
			if(!isUserAvailable) {
				display.displayError(user_name+" NOT EXISTS");
				loop = true;
			}
			else
				loop = false;
		}
		loop = true;
		while(loop) {
			System.out.println("Enter password ");
			String password = sc.next();
			
			User user = operations.loginUser(user_name, password);
			if(user == null)
				loop = true;
			else {
				loop = false;
				askOptions(user);
			}
		}
	}
		
	
	
	private void askOptions(User user) {
		boolean loop = true;
		while(loop) {
			System.out.println("Select option\n"
					+ "1-> Post content\n"
					+ "2-> Follow or UnFollow\n"
					+ "3-> View Posts\n"
					+ "4-> Log out");
			int opt = sc.nextInt();
			switch (opt) {
			case 1: postContent(user);
				break;
			case 2: followOrUnfollow(user);
				break;
			case 3: operations.viewPosts(user);
				break;
			case 4: System.out.println("Logging out");
				loop = false;
				break;
			default: System.out.println("Enter correct option");
				break;
			}
		}
	}
// 	post content
	private void postContent(User user) {
		System.out.println("Enter the content you need to post");
		sc.nextLine();
		String content = sc.nextLine();
		LocalDateTime time = LocalDateTime.now();
		operations.createPost(user, content, time);
		
	}
//	follow  or unfollow user
	private void followOrUnfollow(User user) {
		System.out.println("Enter user name");
		String searching_user_name = sc.next();
		
		 String status = operations.checkFollowOrUnfollowStatus(user, searching_user_name);
		 if(status != null) {
			 System.out.println("Do you want to "+status+" "+searching_user_name);
			 System.out.println("press 1->YES\n"
			 		+ "2->NO");
			 int opt = sc.nextInt();
			 if(opt == 1)
				 operations.changeFollowStatus(status,user,searching_user_name);
		 }
		 else
			 display.displayError(searching_user_name +" not found ");
	}
// For testing purpose
	public void createUsers() {
		operations.registerUser("Sai Deepak", "sai", "sai");
		operations.registerUser("Sachin Tendulkar", "sachin", "sachin");
		operations.registerUser("Virat Kholi", "virat", "virat");
	}
}
