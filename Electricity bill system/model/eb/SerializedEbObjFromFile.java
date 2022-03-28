package eb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class SerializedEbObjFromFile {
	private static SerializedEbObjFromFile serialize = new SerializedEbObjFromFile();
	public static SerializedEbObjFromFile getObj() {
		return serialize;
	}
	private SerializedEbObjFromFile() {}
	
	private File ebFile = new File("C:\\file handling examples\\electricity bill board\\Electricity board.txt");
	private File tempFile = new File("C:\\file handling examples\\electricity bill board\\temp.txt");
	
	public ElectricityBoard getEbObj() {
		ElectricityBoard eb = null;
		if(!ebFile.exists() || ebFile.length() == 0) 
			eb = new ElectricityBoard();
		else 
			eb = getExistingObjFromFile();
		return eb;
	}


	private ElectricityBoard getExistingObjFromFile() {
		ElectricityBoard  eb = null;
		try(
				FileInputStream fis = new FileInputStream(this.ebFile);
				ObjectInputStream mis = new ObjectInputStream(fis);
			){
				eb = (ElectricityBoard)mis.readObject();
				mis.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return eb;
	}
	
	public void updateEbFile(ElectricityBoard newEbObj) {
		try(
				FileOutputStream fos = new FileOutputStream(this.ebFile);
				ObjectOutputStream  mos = new ObjectOutputStream(fos);
			){
				mos.writeObject(newEbObj);
				mos.flush();
				mos.close();				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
