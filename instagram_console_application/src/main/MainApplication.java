package main;

import instagram.Instagram;
import main_view.MainView;

public class MainApplication {
	public static void main(String args[]) {
		Instagram instagram = new Instagram();
		MainView mainView = new MainView(instagram);
		mainView.landingPage();
	}
}
