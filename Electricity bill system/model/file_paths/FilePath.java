package file_paths;
import java.io.*;
public class FilePath {
//	String file = "c:\\file handling examples\\electricity bill board\\number_series.txt";
	File numberSeriesPath = new File("c:\\file handling examples\\electricity bill board\\number_series.txt");
	private File tempFile = new File("c:\\file handling examples\\electricity bill board\\temp.txt");
	private File consumer_table = new File("c:\\file handling examples\\electricity bill board\\consumer_table.txt");
	
	public void createNumberSeriesFile()  {
		if(!numberSeriesPath.exists()) {
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(numberSeriesPath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			pw.println("billNoSeries=11111111");
			pw.println("connNoSeries=22222");
			pw.println("consumerNoSeries=1");
			pw.println("requestNoSeries=1");
			pw.flush();
			pw.close();
		}
	}

	public File getConsumer_table() {
		return consumer_table;
	}

	public void setConsumer_table(File consumer_table) {
		this.consumer_table = consumer_table;
	}

	public File getTempFile() {
		return tempFile;
	}

	public void setTempFile(File tempFile) {
		this.tempFile = tempFile;
	}

	public void createCustomerTable() {
		if(!this.getConsumer_table().exists()) {
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(getConsumer_table());
				pw.println("consumerNo"+","+"name"+","+"email"+","+"phoNo"+","+"address"+","+"user_name"+","+"password");
				pw.flush();
			}
			catch (Exception e) {
				System.out.println("Exception handled");
			}
			finally {
				pw.close();
			}
		}
	}
}
