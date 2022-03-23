package main;

import eb.ElectricityBoard;
import file_operations.FilleOperations;
import main_view.MainView;

public class Main {
	
	public static void main(String args[])  {
		FilleOperations fp = new FilleOperations();
		fp.createFile();
		ElectricityBoard eb = new ElectricityBoard();
		MainView view = new MainView(eb);
		view.askOptions();
	}
}
