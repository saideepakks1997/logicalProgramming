package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import connection.Connection;
import consumer.Consumer;
import eb.ElectricityBoard;
import eb.RequestObj;

public class ConsumerFiles {
	ElectricityBoardFile ebFile = new ElectricityBoardFile();

	CommonProperties common = CommonProperties.getObj();
	String fileName = "Consumer File.txt";
	File consumerFile = common.setFile(fileName);
	File tempFile = new File("temp.txt");
	
	
//	consumerNo,name,email,phoNo,address,user_name,password,connections,request
	public void createConsumer(Consumer consumer) {
		String field = "consumerNoSeries";
		try(
				FileOutputStream fos = new FileOutputStream(this.consumerFile,true);
				PrintStream ps = new PrintStream(fos);
				){
			if(!this.consumerFile.exists() || this.consumerFile.length()==0) {
				
				addFields(ps);
			}
			printIntoFile(consumer, ps);
			ebFile.updateSeriesNo(field);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addFields(PrintStream ps) {
		ps.print("consumerNo,");
		ps.print("name,");
		ps.print("email,");
		ps.print("phoNo,");
		ps.print("address,");
		ps.print("user_name,");
		ps.print("password,");
		ps.print("connections,");
		ps.print("requests");
		ps.println();
	}

	public void updateConsumer(Consumer consumer) {
		if(this.consumerFile.exists() && this.consumerFile.length() > 0) {
			try(
					FileOutputStream fos = new FileOutputStream(tempFile);
					PrintStream ps = new PrintStream(fos);
					FileReader fis = new FileReader(this.consumerFile);
					BufferedReader bis = new BufferedReader(fis);
					){
				addFields(ps);
				String currLine = bis.readLine();
				String[] record = currLine.split(",");
				int targetIndex = common.getIndex(record, "consumerNo");
				currLine = bis.readLine();
				while(currLine!= null) {
					record = currLine.split(",");
					if(Long.parseLong(record[targetIndex]) == consumer.getConsumerNO()) {
						printIntoFile(consumer, ps);
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
				this.consumerFile.delete();
				tempFile.renameTo(this.consumerFile);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	private void printIntoFile(Consumer consumer, PrintStream ps) {
		long consumerNo = consumer.getConsumerNO();
		String name = consumer.getName();
		String email = consumer.getEmailId();
		long phoNo = consumer.getPhoNo();
		String address = consumer.getAddress();
		String user_name = consumer.getUser_name();
		String password = consumer.getPassword();
		String connections = getConnectionNos(consumer);
		String requests = getRequests(consumer);
		
		connections = (connections.length() == 0)?null:connections;
		requests = (requests.length() == 0)?null:requests;
		
		ps.print(consumerNo+",");
		ps.print(name+",");
		ps.print(email+",");
		ps.print(phoNo+",");
		ps.print(address+",");
		ps.print(user_name+",");
		ps.print(password+",");
		ps.print(connections+",");
		ps.print(requests);
		ps.println();
		
	}
	private String getRequests(Consumer consumer) {
		String result = "";
		Map<RequestObj, String> requests = consumer.getNotifis();
		Set<RequestObj> keys = requests.keySet();
		
		Collection<String> values = requests.values();
		List<String> notifis = new ArrayList<String>(values);
		int i=0;
		for(RequestObj key:keys) {
			long requestNo = key.getRequestNo();
			String notification = notifis.get(i++);
			result += requestNo+"="+notification+"/";
		}
		return result;
	}
	private String getConnectionNos(Consumer consumer) {
		String result = "";
		Map<Long, Connection> connections = consumer.getConnections();
		Collection<Long> conns = connections.keySet();
		for(Long conNo:conns) {
			result += conNo+"/";
		}
		return result;
	}

	public void loadConsumersToEb(ElectricityBoard eb) {
		if(this.consumerFile.exists()) {
			ConnectionFiles connFile = new ConnectionFiles();
			RequestObjFiles reqFile = new RequestObjFiles();
//			long consumerNo, String name, String email, long phoNo,String address,user_name,password
			Consumer consumer = null;
			try(
					FileReader fis = new FileReader(this.consumerFile);
					BufferedReader bis = new BufferedReader(fis);
					){
				String currLine = bis.readLine();
				String record[] = currLine.split(",");
				int consumerNoIndex = common.getIndex(record, "consumerNo");
				int nameIndex = common.getIndex(record, "name");
				int emailIndex = common.getIndex(record, "email");
				int phoNoIndex = common.getIndex(record, "phoNo");
				int addressIndex = common.getIndex(record, "address");
				int user_nameIndex = common.getIndex(record, "user_name");
				int passwordIndex = common.getIndex(record, "password");
				int connectionsIndex = common.getIndex(record, "connections");
				int requestsIndex = common.getIndex(record, "requests");
				
				currLine = bis.readLine();
				record = currLine.split(",");
				while(currLine != null) {
					Long consumerNo = Long.parseLong(record[consumerNoIndex]);
					String name = record[nameIndex];
					String email = record[emailIndex];
					Long phoNo = Long.parseLong(record[phoNoIndex]); 
					String address = record[addressIndex];
					String user_name = record[user_nameIndex];
					String password = record[passwordIndex];
					String[] connNos = record[connectionsIndex].split("/");
					String[] requests = record[requestsIndex].split("/");
					if(record[user_nameIndex] == "null") {
						consumer = new Consumer(consumerNo, name, email, phoNo, address);
						eb.setConsumers(consumer);
					}
					else {
						consumer = new Consumer(consumerNo, name, email, phoNo, address,user_name, password);
						eb.setConsumers(consumer);
						eb.setConsumerMapping(consumer);
					}
					System.out.println(record[connectionsIndex]);
					connFile.loadConnection(eb,connNos,consumer);
					reqFile.loadRequests(eb, requests,consumer);
					currLine = bis.readLine();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
