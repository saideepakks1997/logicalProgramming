package file_operations;

import file_paths.FilePath;

public class FilleOperations {
	FilePath fp = new FilePath();
	public void createFile()  {
		fp.createNumberSeriesFile();
		fp.createCustomerTable();
	}
	
}
