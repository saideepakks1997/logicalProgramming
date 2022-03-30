package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import eb.RequestObj;

public class RequestObjFiles {
	CommonProperties common = CommonProperties.getObj();
	File requestFile = null;
	
	public RequestObjFiles() {
		setFile();
	}
	
	private void setFile() {
		File objFile = new File("All Files");
		objFile.mkdir();
		this.requestFile = new File(objFile+File.separator+"Request Object File.txt");
	}
//	private int statusNo;
//	private long requestNo;
//	private long consumerNo;
//	private long serviceNo;
//	private TypeOfConnection connType;
//	private LocalDateTime requestedTime;
//	private LocalDateTime lastUpdatedTime;
//	private String address;
//	private boolean isNewConnectionReq;//new connection request or change of status request
//	//if rejected or accepted returns true if the status is in the middle returns false
//	private boolean isRequestCompleted;
	
	public void createRequest(RequestObj request) {
		try(
				FileOutputStream fos = new FileOutputStream(this.requestFile,true);
				PrintStream ps = new PrintStream(fos);
				){
			if(!this.requestFile.exists() || this.requestFile.length()==0) {
				ps.print("statusNo,");
				ps.print("requestNo,");
				ps.print("consumerNo,");
				ps.print("serviceNo,");
				ps.print("connType,");
				ps.print("requestedTime,");
				ps.print("lastUpdatedTime,");
				ps.print("address,");
				ps.print("isNewConnectionReq,");
				ps.print("isNewConnectionReq,");
				ps.println("isRequestCompleted");
			}
			printIntoFile(request, ps);
			ps.println();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateRequest(RequestObj request) {
		if(this.requestFile.exists() && this.requestFile.length() > 0) {
			try(
					FileOutputStream fos = new FileOutputStream(common.getTempFile());
					PrintStream ps = new PrintStream(fos);
					FileReader fis = new FileReader(this.requestFile);
					BufferedReader bis = new BufferedReader(fis);
					){
				String currLine = bis.readLine();
				String[] record = currLine.split(",");
				int targetIndex = common.getIndex(record, "requestNo");
				currLine = bis.readLine();
				while(currLine!= null) {
					record = currLine.split(",");
					if(Long.parseLong(record[targetIndex]) == request.getRequestNo()) {
						printIntoFile(request, ps);
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
				this.requestFile.delete();
				common.getTempFile().renameTo(this.requestFile);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void printIntoFile(RequestObj request, PrintStream ps) {
		 int statusNo = request.getStatusNo();
		 long requestNo = request.getRequestNo();
		 long consumerNo = request.getConsumerNo();
		 long serviceNo = request.getServiceNo();
		 String connType = request.getConnType().toString();
		 String requestedTime = request.getRequestedTime().toString();
		 String lastUpdatedTime = request.getLastUpdatedTime().toString();
		 String address = request.getAddress();
		 boolean isNewConnectionReq = request.isNewConnectionReq();
		 boolean isRequestCompleted = request.isRequestCompleted();
		 
		 ps.print(statusNo+",");
			ps.print(requestNo+",");
			ps.print(consumerNo+",");
			ps.print(serviceNo+",");
			ps.print(connType+",");
			ps.print(requestedTime+",");
			ps.print(lastUpdatedTime+",");
			ps.print(address+",");
			ps.print(isNewConnectionReq+",");
			ps.print(isRequestCompleted+"");
	}
}
