package eb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import connection.Connection;
import consumer.Consumer;
import files.FileDetails;
import input_output_streams.MyObjectInputStream;

public class LoadData {
	FileDetails files = FileDetails.getFiles();
	ElectricityBoard eb = null;
	public LoadData(ElectricityBoard eb) {
		this.eb = eb;
	}
//	request,connection,consumer
	public void loadRequest() {
		try(
				FileInputStream fis = new FileInputStream(files.getRequestsFile());
				MyObjectInputStream mis = new MyObjectInputStream(fis);
				){
			
			while(fis.available() != 0) {
				RequestObj request = (RequestObj)mis.readObject();
				this.eb.setRequests(request);
			}
		}
		catch(FileNotFoundException e) {
//			e.printStackTrace();
		}
		catch(IOException e) {
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		}
		
	}
	public void loadConnection() {
		try(
				FileInputStream fis = new FileInputStream(files.getConnectionFile());
				MyObjectInputStream mis = new MyObjectInputStream(fis);
				){
			
			while(fis.available() != 0) {
				Connection conn = (Connection)mis.readObject();
				System.out.println("connection units ->"+conn.getCurrentUnit());
				this.eb.setConnections(conn);
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void loadConsumer() {
		try(
				FileInputStream fis = new FileInputStream(files.getConsumerFile());
				MyObjectInputStream mis = new MyObjectInputStream(fis);
				){
			
			while(fis.available() != 0) {
				Consumer consumer = (Consumer)mis.readObject();
				addConnections(consumer.getConnections());
				addRequests(consumer.getNotifis());
				this.eb.setConsumerMapping(consumer);
				this.eb.setConsumers(consumer);
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void addRequests(Map<RequestObj, String> notifis) {
		Set<RequestObj> requests = notifis.keySet();
		for(RequestObj req: requests) {
			if(!req.isRequestCompleted())
				this.eb.setRequests(req);
		}
		
	}
	private void addConnections(Map<Long, Connection> conns) {
		Collection<Connection> connections =  conns.values();
		for(Connection con: connections) {
			this.eb.setConnections(con);
		}
		
	}
	public void loadData() {
		this.loadConsumer();
//		this.loadConnection();
//		this.loadRequest();
		
	}
}
