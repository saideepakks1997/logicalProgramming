package main;

import connection.TypeOfConnection;
import eb.ElectricityBoard;
import eb.RequestStatus;
import main_view.MainView;

public class Main {
	public static void main(String args[]) {
		
		System.out.println(TypeOfConnection.Domestic);
		ElectricityBoard eb = new ElectricityBoard();
		MainView view = new MainView(eb);
		view.askOptions();
	}
}
