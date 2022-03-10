package common_view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import post.Post;

public class CommonView {
	Scanner sc  = new Scanner(System.in);
	public void displayMessege(Object messege) {
		System.out.println("-----------------------");
		System.out.println(messege);
		System.out.println("-----------------------");
	}
	
	public void displayPasswordError(List<String> errors) {
		System.out.println("-------------------------");
		for(String error:errors)
			System.out.println(error);
		
		System.out.println("-------------------------");
	}
	
	public void displayPosts(Post post,int postId) {
		System.out.println("-------------------------");
		System.out.println("Post id "+postId);
		System.out.println(post);
		System.out.println("-------------------------");
	}
	public void displayProfileStatus(String user_name, String status) {
		System.out.println("You are "+status+" "+user_name);
	}
	
	public int getOption(Scanner inp) {
		boolean loop = true;
		int opt = 0;
	while(loop) {
		loop = false;
		try {
			opt = sc.nextInt();
			
		}
		catch(InputMismatchException exception){
			displayMessege("Wrong input enter correct input(i.e) number");
			System.out.println("Re enter the option");
			loop = true;
			sc = new Scanner(System.in);
			}
		
	}
		
		return opt;
	}
	
	
	public int getOption() {
		boolean loop = true;
		int opt = 0;
		while(loop) {
			loop = false;
			try {
				opt = sc.nextInt();
			
			}
			catch(InputMismatchException exception){
				displayMessege("Wrong input enter correct input(i.e) number");
				System.out.println("Re enter the option");
				loop = true;
				sc = new Scanner(System.in);
			}
		}
		
		return opt;
	}
}
