package add_or_update_files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import files_details.FileDetails;

public class GetSeriesNo {
//	private long billNoSeries = 11111111l;
//	private long connNoSeries = 22222l;
//	private long consumerNoSeries = 1;
//	private long requestNoSeries = 1;
	FileDetails path = FileDetails.getFiles();
	
	public long getSeries(String field) {
		createNumberSeriesFile();
		FileReader fr = null;
		BufferedReader br = null;
		String curLine = null;
		long curSeriesNo = 0;
		try {
			fr = new FileReader(path.getNumberSeriesPath());
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			curLine = br.readLine();
			while(curLine != null) {
				String str[] = curLine.split("=");
				if(str[0].equals(field)) {
					curSeriesNo = Long.parseLong(str[1]);
					br.close();
					updateSeries(field,curSeriesNo + 1);
					break;
				}
				curLine = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return curSeriesNo;
	}
	private void updateSeries(String field, long setNum) {
//		Path pa = Paths.get(path.file);
		FileReader fr = null;
		PrintWriter pw = null;
		try {
			fr = new FileReader(path.getNumberSeriesPath());
			BufferedReader br = new BufferedReader(fr);
			pw = new PrintWriter(path.getTempFile());
			
			String line = br.readLine();
			
			while(line != null) {
				String[] strs = line.split("="); 
				if(!strs[0].equals(field)) {
					pw.println(line);
				}
				else {
					pw.println(field+"="+setNum);
				}
				line = br.readLine();
			}
			br.close();
			pw.flush();
			pw.close();
			
			path.getNumberSeriesPath().delete();
			path.getTempFile().renameTo(path.getNumberSeriesPath());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createNumberSeriesFile()  {
		if(!path.getNumberSeriesPath().exists() || path.getNumberSeriesPath().length() == 0) {
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(path.getNumberSeriesPath());
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
}