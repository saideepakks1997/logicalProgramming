package display;

import java.time.LocalDateTime;
import java.util.List;

public class Display {
	public void displayError(String error) {
		System.out.println("--------------------");
		System.out.println(error);
		System.out.println("--------------------");
	}

	public void displaySuccess(String success) {
		System.out.println("--------------------");
		System.out.println(success);
		System.out.println("--------------------");
	}

	public void displayProfileStatus(String user_name, String status) {
		System.out.println("You are "+status+" "+user_name);
	}

	public void displayPost(String postOwner, String content, String postCreatedTime) {
		System.out.println("-------------------------");
		System.out.println("Posted by --->"+postOwner);
		System.out.println(content);
		System.out.println("Created on "+postCreatedTime);
		System.out.println("-------------------------");
		
	}
	
	public void displayPasswordError(List<String> errors) {
		System.out.println("-------------------------");
		for(String error:errors)
			System.out.println(error);
		
		System.out.println("-------------------------");
	}
}
