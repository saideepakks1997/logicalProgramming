package files_details;

import java.io.File;

public class FileDetails {
	private File consumerFile = new File("C:\\file handling examples\\electricity bill board\\consumers.txt");
	private File tempFile = new File("C:\\file handling examples\\electricity bill board\\temp.txt");
	private File numberSeriesPath = new File("c:\\file handling examples\\electricity bill board\\number_series.txt");
	private static FileDetails fileConnection = new FileDetails();
	public static FileDetails getFiles() {
		return fileConnection;
	}
	private FileDetails() {}
	
	public File getConsumerFile() {
		return consumerFile;
	}
	
	public File getTempFile() {
		return tempFile;
	}
	public File getNumberSeriesPath() {
		return numberSeriesPath;
	}
	
	
}
