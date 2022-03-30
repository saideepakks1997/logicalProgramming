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
import eb.RequestObj;

public class ConsumerFiles {
	CommonProperties common = CommonProperties.getObj();
	File consumerFile = null;
	public ConsumerFiles() {
		setFile();
	}
	private void setFile() {
		File objFile = new File("All Files");
		objFile.mkdir();
		this.consumerFile = new File(objFile+File.separator+"Consumer File.txt");
		
	}
//	consumerNo,name,email,phoNo,address,user_name,password,connections,request
	public void createConsumer(Consumer consumer) {
		try(
				FileOutputStream fos = new FileOutputStream(this.consumerFile,true);
				PrintStream ps = new PrintStream(fos);
				){
			if(!this.consumerFile.exists() || this.consumerFile.length()==0) {
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
			printIntoFile(consumer, ps);
			ps.println();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateConsumer(Consumer consumer) {
		if(this.consumerFile.exists() && this.consumerFile.length() > 0) {
			try(
					FileOutputStream fos = new FileOutputStream(common.getTempFile());
					PrintStream ps = new PrintStream(fos);
					FileReader fis = new FileReader(this.consumerFile);
					BufferedReader bis = new BufferedReader(fis);
					){
				String currLine = bis.readLine();
				String[] record = currLine.split(",");
				int targetIndex = common.getIndex(record, "consumerNo");
				currLine = bis.readLine();
				while(currLine!= null) {
					record = currLine.split(",");
					if(Long.parseLong(record[targetIndex]) == consumer.getConsumerNO()) {
						printIntoFile(consumer, ps);
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
				this.consumerFile.delete();
				common.getTempFile().renameTo(this.consumerFile);
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
		
		ps.print(consumerNo+",");
		ps.print(name+",");
		ps.print(email+",");
		ps.print(phoNo+",");
		ps.print(address+",");
		ps.print(user_name+",");
		ps.print(password+",");
		ps.print(connections+",");
		ps.print(requests+"");
		
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
			result += requestNo+"="+notification+"|";
		}
		return result;
	}
	private String getConnectionNos(Consumer consumer) {
		String result = "";
		Map<Long, Connection> connections = consumer.getConnections();
		Collection<Long> conns = connections.keySet();
		for(Long conNo:conns) {
			result += conNo+"|";
		}
		return result;
	}
}
