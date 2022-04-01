package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;

import connection.Connection;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.ElectricityBoard;
import eb.RequestObj;

public class RequestObjFiles {
	ElectricityBoardFile ebFile = new ElectricityBoardFile();

	CommonProperties common = CommonProperties.getObj();
	String fileName = "Request Object File.txt";
	File requestFile = common.setFile(fileName);
	File tempFile = new File("temp.txt");
	
	

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
		String field = "requestNoSeries";
		try(
				FileOutputStream fos = new FileOutputStream(this.requestFile,true);
				PrintStream ps = new PrintStream(fos);
				){
			if(!this.requestFile.exists() || this.requestFile.length()==0) {
				addFields(ps);
				
			}
			printIntoFile(request, ps);
			ebFile.updateSeriesNo(field);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addFields(PrintStream ps) {
		ps.print("statusNo,");
		ps.print("requestNo,");
		ps.print("consumerNo,");
		ps.print("serviceNo,");
		ps.print("connType,");
		ps.print("requestedTime,");
		ps.print("lastUpdatedTime,");
		ps.print("address,");
		ps.print("isNewConnectionReq,");
		ps.print("isRequestCompleted");
		ps.println();
		
	}

	public void updateRequest(RequestObj request) {
		if(this.requestFile.exists() && this.requestFile.length() > 0) {
			try(
					FileOutputStream fos = new FileOutputStream(tempFile);
					PrintStream ps = new PrintStream(fos);
					FileReader fis = new FileReader(this.requestFile);
					BufferedReader bis = new BufferedReader(fis);
					){
				addFields(ps);
				String currLine = bis.readLine();
				String[] record = currLine.split(",");
				int targetIndex = common.getIndex(record, "requestNo");
				currLine = bis.readLine();
				while(currLine!= null) {
					record = currLine.split(",");
					if(Long.parseLong(record[targetIndex]) == request.getRequestNo()) {
						printIntoFile(request, ps);
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
				tempFile.renameTo(this.requestFile);
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
			ps.println();
	}

	public void loadRequests(ElectricityBoard eb, String[] requests, Consumer consumer) {
		if(this.requestFile.exists()) {
			FileReader fis = null;
			BufferedReader bis = null;
			PaymentFile paymentFile = new PaymentFile();
			BillFiles billFiles = new BillFiles();
			try{
				 fis = new FileReader(this.requestFile);
				 bis = new BufferedReader(fis);
				
				String currLine = bis.readLine();
				String record[] = currLine.split(",");

				
				int statusNoIndex = common.getIndex(record, "statusNo");
				int requestNoIndex = common.getIndex(record, "requestNo");
				int consumerNoIndex = common.getIndex(record, "consumerNo");
				int serviceNoIndex = common.getIndex(record, "serviceNo");
				int connTypeIndex = common.getIndex(record, "connType");
				int addressIndex = common.getIndex(record, "address");
				int requestedTimeIndex = common.getIndex(record, "requestedTime");
				int updatedTimeIndex = common.getIndex(record, "lastUpdatedTime");
				int isNewConnIndex = common.getIndex(record, "isNewConnectionReq");
				int isReqCompletedIndex= common.getIndex(record, "isRequestCompleted");
				if(!requests[0].equals("null")) {
					for(String req:requests) {
						String[] keyValue = req.split("=");//0 is request no and 1 is notification
						currLine = bis.readLine();
						while(currLine != null) {
							record = currLine.split(",");
							
							Long requestNo = Long.parseLong(record[requestNoIndex]);
							if(Long.parseLong(keyValue[0]) == requestNo) {
//								
								 int statusNo = Integer.parseInt(record[statusNoIndex]);
								 long consumerNo = Long.parseLong(record[consumerNoIndex]);
								 long serviceNo = Long.parseLong(record[serviceNoIndex]);
								 TypeOfConnection connType = TypeOfConnection.valueOf(record[connTypeIndex]);
								 LocalDateTime requestedTime = LocalDateTime.parse(record[requestedTimeIndex]);
								 LocalDateTime lastUpdatedTime = LocalDateTime.parse(record[updatedTimeIndex]);;
								 String address = record[addressIndex];
								 boolean isNewConnectionReq = Boolean.parseBoolean(record[isNewConnIndex]);
								 boolean isRequestCompleted = Boolean.parseBoolean(record[isReqCompletedIndex]);
								 
								RequestObj request = new RequestObj(statusNo, requestNo, consumerNo, serviceNo, connType, address, requestedTime,lastUpdatedTime , isNewConnectionReq, isRequestCompleted);
								if(isRequestCompleted) {
									consumer.setNotifis(request, keyValue[1]);
								}
								else {
									consumer.setNotifis(request, keyValue[1]);
									eb.setRequests(request);
								}
								bis.close();
								break;
							}
							currLine = bis.readLine();
						}
						fis = new FileReader(this.requestFile);
						bis = new BufferedReader(fis);
						bis.readLine();
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					bis.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
