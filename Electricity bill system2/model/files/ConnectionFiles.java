package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import bill.Bill;
import bill.Payment;
import connection.Connection;
import connection.TypeOfConnection;
import consumer.Consumer;
import eb.ElectricityBoard;
import factory.ConnectionFactoryObj;

public class ConnectionFiles implements IConnectionFiles{
	ElectricityBoardFile ebFile = new ElectricityBoardFile();
	CommonProperties common = CommonProperties.getObj();
	ConnectionFactoryObj connFactory = new ConnectionFactoryObj();
	
	String fileName = "Connection File.txt";
	File connFile = common.setFile(fileName);
	File tempFile = new File("temp.txt");
	
	//Add new connection to file system
	public void createConnection(Connection con)
		{
		String field = "connNoSeries";
		try(
				FileOutputStream fos = new FileOutputStream(this.connFile,true);
				PrintStream ps = new PrintStream(fos);
				){
			if(!this.connFile.exists() || this.connFile.length()==0) {
				addFields(ps);
				
			}
			printIntoFile(con, ps);
			ebFile.updateSeriesNo(field);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addFields(PrintStream ps) {
		ps.print("serviceNo,");
		ps.print("connectionType,");
		ps.print("currentUnits,");
		ps.print("consumerNo,");
		ps.print("connAddress,");
		ps.print("pendingPayments,");
		ps.print("bills");
		ps.println();
	}
	
	
	private void printIntoFile(Connection con, PrintStream ps) {
		long serviceNo  = con.getServiceNo();
		String connectionType = con.getConnectionType().toString();
		Long currentUnits = con.getCurrentUnit();
		Long consumerNo = con.getConsumer().getConsumerNO();
		String connAddress = con.getConnAddress();
		String pendingPayments = getPendingPaymentIds(con);
		String bills = getBillNos(con);
		
		pendingPayments = (pendingPayments.length() == 0)?null:pendingPayments;
		bills = (bills.length() == 0)?null:bills;
		
		ps.print(serviceNo+",");
		ps.print(connectionType+",");
		ps.print(currentUnits+",");
		ps.print(consumerNo+",");
		ps.print(connAddress+",");
		ps.print(pendingPayments+",");
		ps.print(bills);
		ps.println();
	}
	//update connection
	public void updateConnection(Connection con) {
		if(this.connFile.exists() && this.connFile.length() > 0) {
			try(
					FileOutputStream fos = new FileOutputStream(this.tempFile);
					PrintStream ps = new PrintStream(fos);
					FileReader fis = new FileReader(this.connFile);
					BufferedReader bis = new BufferedReader(fis);
					){
				addFields(ps);
				String currLine = bis.readLine();
				String[] record = currLine.split(",");
				int targetIndex = common.getIndex(record, "serviceNo");
				currLine = bis.readLine();
				while(currLine!= null) {
					record = currLine.split(",");
					if(Long.parseLong(record[targetIndex]) == con.getServiceNo()) {
						printIntoFile(con, ps);
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
				this.tempFile.renameTo(this.connFile);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	
	//get all bills of particular connection return as string
	private String getBillNos(Connection con) {
		String result = "";
		List<Bill> bills = con.getBills();
		for(Bill b:bills) {
			result += b.getBillNo()+"/";
		}
		return result;
	}
	
	//get all pending payments return as string
	private String getPendingPaymentIds(Connection con) {
		String result = "";
		List<Payment> payments = con.getPendingPayments();
		for(Payment p:payments) {
			result += p.getId()+"/";
		}
		return result;
	}
	//Load connection into the application at beginning of application
	public void loadConnection(ElectricityBoard eb, String[] connNos,Consumer consumer) {
		if(this.connFile.exists()) {
			FileReader fis = null;
			BufferedReader bis = null;
			PaymentFile paymentFile = new PaymentFile();
			BillFiles billFiles = new BillFiles();
			try{
				 fis = new FileReader(this.connFile);
				 bis = new BufferedReader(fis);
				
				String currLine = bis.readLine();
				String record[] = currLine.split(",");
				
				int connNoIndex = common.getIndex(record, "serviceNo");
				int connTypeIndex = common.getIndex(record, "connectionType");
				int currUnitsIndex = common.getIndex(record, "currentUnits");
				int addressIndex = common.getIndex(record, "connAddress");
				int paymentIndex = common.getIndex(record, "pendingPayments");
				int billsIndex = common.getIndex(record, "bills");
				
				if(!connNos[0].equals("null")) {
					for(String conn:connNos) {
						currLine = bis.readLine();
						
						while(currLine != null) {
							record = currLine.split(",");
							Long serviceNo = Long.parseLong(record[connNoIndex]);
							if(Long.parseLong(conn) == serviceNo) {
								TypeOfConnection connType = TypeOfConnection.valueOf(record[connTypeIndex]);
								long currUnits = Long.parseLong(record[currUnitsIndex]);
								String connAddress = record[addressIndex];
								
								Connection con = connFactory.getConnectionObj(serviceNo, connType, connAddress, consumer); 
								con.setCurrentUnit(currUnits);
								eb.setConnections(con);
								consumer.setConnection(con);
								paymentFile.loadPayment(con,record[paymentIndex].split("/"));
								billFiles.loadBills(con,record[billsIndex].split("/"));
								bis.close();
								break;
							}
							currLine = bis.readLine();
						}
						fis = new FileReader(this.connFile);
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
