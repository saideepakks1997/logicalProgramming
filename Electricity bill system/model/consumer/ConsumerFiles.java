package consumer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import connection.Connection;
import eb.ElectricityBoard;
import files.FileDetails;
import input_output_streams.MyObjectInputStream;
import input_output_streams.MyObjectOutputStream;

public class ConsumerFiles {
	ElectricityBoard eb = null;
	FileDetails files = FileDetails.getFiles();
	public ConsumerFiles(ElectricityBoard eb) {
		this.eb = eb;
	}

	public void addCustomer(Consumer consumer) {
		try (FileOutputStream fos = new FileOutputStream(files.getConsumerFile());
				MyObjectOutputStream  mos = new MyObjectOutputStream(fos);){
			mos.writeObject(consumer);
			mos.flush();
			System.out.println("Hi-->"+consumer.getName());
			System.out.println("Hi-->"+consumer.getConnections().get(0l));
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
////		updateConusumer(updatedConsumer);
//		try(
//			FileOutputStream fos = new FileOutputStream(files.getTempFile());
//			MyObjectOutputStream  mos = new MyObjectOutputStream(fos);
//			FileInputStream fis = new FileInputStream(files.getConsumerFile());
//			MyObjectInputStream mis = new MyObjectInputStream(fis);
//		){
//			while(fis.available() != 0) {
//				Consumer consumer = (Consumer)mis.readObject();
//				if(updatedConsumer.getConsumerNO() == consumer.getConsumerNO()) {
//					mos.writeObject(updatedConsumer);
//				}
//				else
//					mos.writeObject(consumer);
//			}
//			mos.flush();
//			mos.close();
//			mis.close();
//			System.out.println(files.getConsumerFile().delete());
//			System.out.println(files.getTempFile().renameTo(files.getConsumerFile()));
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		
	}

	public void updateConusumer(Consumer updatedConsumer) {
		
		try(
			FileOutputStream fos = new FileOutputStream(files.getTempFile());
			MyObjectOutputStream  mos = new MyObjectOutputStream(fos);
			FileInputStream fis = new FileInputStream(files.getConsumerFile());
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
			System.out.println(files.getConsumerFile().delete());
			System.out.println(files.getTempFile().renameTo(files.getConsumerFile()));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
