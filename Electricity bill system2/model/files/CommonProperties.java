package files;

import java.io.File;

public class CommonProperties {
	
	private static CommonProperties common = new CommonProperties();
	
	public static CommonProperties getObj() {
		return common;
	}
	
	private CommonProperties() {
	}
	
	public int getIndex(String[] record, String field) {
		int index = -1;
		for(int i=0; i<record.length; i++) {
			if(record[i].equals(field))
				index = i;
		}
		return index;
	}

	public File setFile(String fileName) {
		File objDir = new File("All Files");
		objDir.mkdir();
		return new File(objDir+File.separator+""+fileName);
	}
	
	
}
