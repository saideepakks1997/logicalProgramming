package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;

import bill.Bill;
import bill.Payment;
import connection.Connection;

public class BillFiles {
	ElectricityBoardFile ebFile = new ElectricityBoardFile();
	CommonProperties common = CommonProperties.getObj();
	String fileName = "Bill File.txt";
	File billFile = common.setFile(fileName);
	

	//Add bill in file system
	public void createBill(Bill bill) {
		String field = "billNoSeries";
		try(
				FileOutputStream fos = new FileOutputStream(this.billFile,true);
				PrintStream ps = new PrintStream(fos);
				){
			if(!this.billFile.exists() || this.billFile.length()==0) {
				addFields(ps);
			}
			printIntoFile(bill, ps);
			ebFile.updateSeriesNo(field);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addFields(PrintStream ps) {
		ps.print("billNo,");
		ps.print("payment,");
		ps.print("paymentDate,");
		ps.print("paidThrough");
		ps.println();
	}

	private void printIntoFile(Bill bill, PrintStream ps) {
		long billNo = bill.getBillNo();
		long paymentId = bill.getPayment().getId();
		String paymentDate = bill.getPaymentDate().toString();
		String paidThrough = bill.getPaidThrough();
		ps.print(billNo+",");
		ps.print(paymentId+",");
		ps.print(paymentDate+",");
		ps.print(paidThrough);
		ps.println();
	}
	

	//Load bills at starting of application
	public void loadBills(Connection con, String[] bills)  {
		if(this.billFile.exists()) {
			PaymentFile paymentFile = new PaymentFile();
			FileReader fis = null;
			BufferedReader bis = null;
			try{
				fis = new FileReader(this.billFile);
				bis = new BufferedReader(fis);
				
				String currLine = bis.readLine();
				String record[] = currLine.split(",");
				
				int billNoIndex = common.getIndex(record, "billNo");
				int paymentIdIndex = common.getIndex(record, "payment");
				int paymentDateIndex = common.getIndex(record, "paymentDate");
				int paidThroughIndex = common.getIndex(record, "paidThrough");
				
				if(!bills[0].equals("null")) {
					for(String b:bills) {
						currLine = bis.readLine();
						while(currLine != null) {
							record = currLine.split(",");
							Long billNo = Long.parseLong(record[billNoIndex]);
							if(Long.parseLong(b) == billNo) {
								Long payment = Long.parseLong(record[paymentIdIndex]);
								LocalDateTime paymentDate = LocalDateTime.parse(record[paymentDateIndex]);
								String paidThrough = record[paidThroughIndex];
								
								Payment payObj = paymentFile.getPayment(payment);
								
								Bill bill = new Bill(billNo, payObj, paymentDate, paidThrough);
								con.setBills(bill);
								bis.close();
								break;
							}
							currLine = bis.readLine();
						}
						fis = new FileReader(this.billFile);
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
