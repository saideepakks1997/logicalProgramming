package connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import eb.ElectricityBoard;
import files.FileDetails;
import input_output_streams.MyObjectInputStream;
import input_output_streams.MyObjectOutputStream;

public class ConnectionFiles {
	ElectricityBoard eb = null;
	FileDetails files = FileDetails.getFiles();
	public ConnectionFiles(ElectricityBoard eb) {
		this.eb = eb;
	}
	public void addConnection(Connection connection) {
		try (FileOutputStream fos = new FileOutputStream(files.getConnectionFile());
				MyObjectOutputStream  mos = new MyObjectOutputStream(fos);){
			mos.writeObject(connection);
			mos.flush();
			this.eb.setConnections(connection);
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			 e.printStackTrace();
		 }
		
	}
	public void updateConnection(Connection updatedConn) {
		try(
			FileOutputStream fos = new FileOutputStream(files.getTempFile());
			MyObjectOutputStream  mos = new MyObjectOutputStream(fos);
			FileInputStream fis = new FileInputStream(files.getConnectionFile());
			MyObjectInputStream mis = new MyObjectInputStream(fis);
		){
			while(fis.available() != 0) {
				Connection conn = (Connection)mis.readObject();
				if(conn.getServiceNo() == updatedConn.getServiceNo()) {
					mos.writeObject(updatedConn);
				}
				else
					mos.writeObject(conn);
			}
			mos.flush();
			mos.close();
			mis.close();
			System.out.println(files.getConnectionFile().delete());
			System.out.println(files.getTempFile().renameTo(files.getConnectionFile()));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
