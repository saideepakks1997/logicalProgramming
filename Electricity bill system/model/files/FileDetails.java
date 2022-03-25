package files;

import java.io.File;

public class FileDetails {
	private File consumerFile = new File("C:\\file handling examples\\electricity bill board\\consumers.txt");
	private File connectionFile = new File("C:\\file handling examples\\electricity bill board\\connections.txt");
	private File billFile = new File("C:\\file handling examples\\electricity bill board\\bills.txt");
	private File paymentsFile = new File("C:\\file handling examples\\electricity bill board\\payments.txt");
	private File requestsFile = new File("C:\\file handling examples\\electricity bill board\\requests.txt");
	private File numSeriesFile = new File("C:\\file handling examples\\electricity bill board\\numSeries.txt");
	private File tempFile = new File("C:\\file handling examples\\electricity bill board\\temp.txt");
	
	private static FileDetails fileConnection = new FileDetails();
	public static FileDetails getFiles() {
		return fileConnection;
	}
	private FileDetails() {}
	
	public File getConsumerFile() {
		return consumerFile;
	}
	public File getConnectionFile() {
		return connectionFile;
	}
	public File getBillFile() {
		return billFile;
	}
	public File getPaymentsFile() {
		return paymentsFile;
	}
	public File getRequestsFile() {
		return requestsFile;
	}
	public File getNumSeriesFile() {
		return numSeriesFile;
	}
	public File getTempFile() {
		return tempFile;
	}
	
}
