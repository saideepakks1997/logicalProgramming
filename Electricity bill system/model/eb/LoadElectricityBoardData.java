package eb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import connection.Connection;
import consumer.Consumer;
import files_details.FileDetails;
import input_output_streams.MyObjectInputStream;

public class LoadElectricityBoardData {
	FileDetails files = FileDetails.getFiles();
	ElectricityBoard eb = null;
	public LoadElectricityBoardData(ElectricityBoard eb) {
		this.eb = eb;
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

}
