package main;

import eb.ElectricityBoard;
import files.ConnectionFiles;
import files.ElectricityBoardFile;
import main_view.MainView;

public class Main {
	public static void main(String args[])  {
//		ConnectionFiles files = new ConnectionFiles();
//		ElectricityBoard eb = new ElectricityBoard();
		ElectricityBoardFile ebFile = new ElectricityBoardFile();
		ElectricityBoard eb = ebFile.getElectricityBoardObj();
		MainView view = new MainView(eb);
	    view.askOptions();
	}
}
