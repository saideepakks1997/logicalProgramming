package tables;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import file_paths.FilePath;

public class ConsumerTable {
	FilePath path = new FilePath();
	public boolean addConsumer(long consumerNo, String name, String email, long phoNo,String address){
		
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try {
			
			fw = new FileWriter(path.getConsumer_table(),true);
			pw = new PrintWriter(fw);
			
			
			
			pw.println(consumerNo+","+name+","+email+","+phoNo+","+address);
			fw.close();
			pw.flush();
			pw.close();
		}
		catch(IOException e) {
			return false;
		}
		return true;
	}
	
	public boolean updateConsumer(long consumerNo,String user_name, String password) {
		FileReader fr = null;
		BufferedReader br = null; 
		PrintWriter pw = null;
		try {
			fr = new FileReader(path.getConsumer_table());
			br = new BufferedReader(fr);
			pw = new PrintWriter(path.getTempFile());
			
			String curLine = br.readLine();
			pw.println(curLine);
			String[] fields = curLine.split(",");
			int cNoIndex = getFieldIndex(fields,"consumerNo");
			curLine = br.readLine();
			while(curLine != null) {
				String[] record = curLine.split(",");

				long curConsNo = Long.parseLong(record[cNoIndex]);
				if(curConsNo != consumerNo) {
					pw.println(curLine);
				}
				else {
					pw.println(curLine+","+user_name+","+password);
				}
				curLine = br.readLine();
			}
			br.close();
			pw.flush();
			pw.close();
			path.getConsumer_table().delete();
			path.getTempFile().renameTo(path.getConsumer_table());
		}
		catch(IOException e) {
			return false;
		}
		return true;
	}
	
	private int getFieldIndex(String[] record, String field) {
		int index = -1;
		for(int i=0; i<record.length; i++) {
			if(record[i].equals(field))
				index = i;
		}
		return index;
	}

	public boolean checkCredential(String user_name, String password) throws IOException {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(path.getConsumer_table());
			br = new BufferedReader(fr);
			String curLine = br.readLine();
			String[] fields = curLine.split(",");
			
			int user_name_index = getFieldIndex(fields, "user_name");
			int password_index = getFieldIndex(fields, "password");
			curLine = br.readLine();
			while(curLine != null) {
				String record[] = curLine.split(",");
				int len = record.length;
				if(
						len > user_name_index
						&&
						record[user_name_index].equals(user_name)
						&&
						len > password_index
						&&
						record[password_index].equals(password)
					) {
						System.out.println("Entered");
						return true;
					}
					
				curLine = br.readLine();
				
			}
		}
		catch(Exception e) {}
		finally {
			br.close();
		}
		return false;
	}
}
