package main;

import eb.ElectricityBoard;
import files.ConnectionFiles;
import main_view.MainView;

public class Main {
	public static void main(String args[])  {
//		ConnectionFiles files = new ConnectionFiles();
		ElectricityBoard eb = new ElectricityBoard();
		MainView view = new MainView(eb);
	    view.askOptions();
	}
}
