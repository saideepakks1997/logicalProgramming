package files;

import java.io.File;

public class ElectricityBoardFile {
	CommonProperties common = CommonProperties.getObj();
	File paymentFile = null;
	public ElectricityBoardFile() {
		setFile();
	}
	private void setFile() {
		File objFile = new File("All Files");
		objFile.mkdir();
		this.paymentFile = new File(objFile+File.separator+"Consumer File.txt");
		
	}
}
