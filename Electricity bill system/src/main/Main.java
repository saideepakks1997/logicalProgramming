package main;

import eb.ElectricityBoard;
import eb.SerializedEbObjFromFile;
import main_view.MainView;

public class Main {
	public static void main(String args[])  {
		SerializedEbObjFromFile deserialize = SerializedEbObjFromFile.getObj();
		ElectricityBoard eb = deserialize.getEbObj(); 
//		 Runtime current = Runtime.getRuntime();
//	        current.addShutdownHook(new ShutDownThread(deserialize, eb));
		
		MainView view = new MainView(eb);
	       
		view.askOptions();
	}
}
