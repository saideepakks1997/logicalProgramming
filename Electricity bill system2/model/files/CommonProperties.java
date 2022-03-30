package files;

import java.io.File;

public class CommonProperties {
	private File tempFile = null;
	
	private static CommonProperties common = new CommonProperties();
	
	public static CommonProperties getObj() {
		return common;
	}
	
	private CommonProperties() {
		setFile();
	}
	
	public int getIndex(String[] record, String field) {
		int index = -1;
		for(int i=0; i<record.length; i++) {
			if(record[i].equals(field))
				index = i;
		}
		return index;
	}

	public File getTempFile() {
		return tempFile;
	}
	
	private void setFile() {
		File objFile = new File("All Files");
		objFile.mkdir();
		tempFile = new File(objFile+File.separator+"temp.txt");
	}
}
