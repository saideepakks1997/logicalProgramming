package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

import connection.Connection;

public class ConnectionFiles {
	CommonProperties common = CommonProperties.getObj();
	File connFile = null;
	public ConnectionFiles() {
		setFile();
	}

	private void setFile() {
		File objFile = new File("All Files");
		objFile.mkdir();
		this.connFile = new File(objFile+File.separator+"Connection File.txt");
		
	}
	
	
	public void createConnection(Connection con)
		{
		
		try(
				FileOutputStream fos = new FileOutputStream(this.connFile,true);
				PrintStream ps = new PrintStream(fos);
				){
			if(!this.connFile.exists() || this.connFile.length()==0) {
				ps.print("serviceNo,");
				ps.print("connectionType,");
				ps.print("currentUnits,");
				ps.print("consumerNo,");
				ps.print("connAddress");
				ps.println();
			}
			printIntoFile(con, ps);
			ps.println();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateConnection(Connection con) {
		if(this.connFile.exists() && this.connFile.length() > 0) {
			try(
					FileOutputStream fos = new FileOutputStream(common.getTempFile());
					PrintStream ps = new PrintStream(fos);
					FileReader fis = new FileReader(this.connFile);
					BufferedReader bis = new BufferedReader(fis);
					){
				String currLine = bis.readLine();
				String[] record = currLine.split(",");
				int targetIndex = common.getIndex(record, "serviceNo");
				currLine = bis.readLine();
				while(currLine!= null) {
					record = currLine.split(",");
					if(Long.parseLong(record[targetIndex]) == con.getServiceNo()) {
						printIntoFile(con, ps);
						ps.println();
					}
					else {
						ps.print(currLine);
						ps.println();
					}
					currLine = bis.readLine();
				}
				ps.flush();
				ps.close();
				bis.close();
				this.connFile.delete();
				common.getTempFile().renameTo(this.connFile);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void printIntoFile(Connection con, PrintStream ps) {
		long serviceNo  = con.getServiceNo();
		String connectionType = con.getConnectionType().toString();
		double currentUnits = con.getCurrentUnit();
		Long consumerNo = con.getConsumer().getConsumerNO();
		String connAddress = con.getConnAddress();
		
		ps.print(serviceNo+",");
		ps.print(connectionType+",");
		ps.print(currentUnits+",");
		ps.print(consumerNo+",");
		ps.print(connAddress);
		
	}

	
}
