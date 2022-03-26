package add_or_update_files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import connection.Connection;
import consumer.Consumer;
import eb.ElectricityBoard;
import input_output_streams.MyObjectInputStream;
import input_output_streams.MyObjectOutputStream;

public class ConsumerFiles {
	private File consumerFile = new File("C:\\file handling examples\\electricity bill board\\consumers.txt");
	private File tempFile = new File("C:\\file handling examples\\electricity bill board\\temp.txt");
	
	ElectricityBoard eb = null;
	public ConsumerFiles(ElectricityBoard eb) {
		this.eb = eb;
	}

	public void addCustomer(Consumer consumer) {
		try (FileOutputStream fos = new FileOutputStream(this.consumerFile,true);
				MyObjectOutputStream  mos = new MyObjectOutputStream(fos);){
			mos.writeObject(consumer);
			mos.flush();
			this.eb.setConsumers(consumer);
			this.eb.setConsumerMapping(consumer);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 catch (IOException e) {
			 e.printStackTrace();
		 }
		
	}

	public void updateConusumer(Connection conn) {
		Consumer updatedConsumer = conn.getConsumer();
		updateConusumer(updatedConsumer);
	}

	public void updateConusumer(Consumer updatedConsumer) {
		try(
			FileOutputStream fos = new FileOutputStream(this.tempFile);
			MyObjectOutputStream  mos = new MyObjectOutputStream(fos);
			FileInputStream fis = new FileInputStream(this.consumerFile);
			MyObjectInputStream mis = new MyObjectInputStream(fis);
		){
			while(fis.available() != 0) {
				Consumer consumer = (Consumer)mis.readObject();
				if(updatedConsumer.getConsumerNO() == consumer.getConsumerNO()) {

					mos.writeObject(updatedConsumer);
				}
				else
					mos.writeObject(consumer);
			}
			mos.flush();
			mos.close();
			mis.close();
			this.consumerFile.delete();
			this.tempFile.renameTo(this.consumerFile);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
