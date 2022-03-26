package main;

import eb.ElectricityBoard;
import main_view.MainView;

public class Main {
	public static void main(String args[])  {
		ElectricityBoard eb = new ElectricityBoard();
		MainView view = new MainView(eb);
		view.askOptions();
	}
}
