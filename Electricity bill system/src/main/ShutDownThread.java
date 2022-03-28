package main;

import eb.ElectricityBoard;
import eb.SerializedEbObjFromFile;

public class ShutDownThread extends Thread{
	SerializedEbObjFromFile serialize = SerializedEbObjFromFile.getObj();
	ElectricityBoard eb = null;
	public ShutDownThread(ElectricityBoard eb) {
		this.eb = eb;
	}

	@Override
	public void run() {
		this.serialize.updateEbFile(eb);
		System.out.println("doone");
	}
}
