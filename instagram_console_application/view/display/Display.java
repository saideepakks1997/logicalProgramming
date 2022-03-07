package display;

import java.time.LocalDateTime;

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

	public void displayPost(String content, LocalDateTime postCreatedTime) {
		System.out.println("-------------------------");
		System.out.println(content);
		System.out.println("Created on "+postCreatedTime);
		System.out.println("-------------------------");
		
	}
}
