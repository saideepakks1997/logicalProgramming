package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;

import bill.Payment;
import connection.Connection;

public class PaymentFile implements IPaymentFile{
	ElectricityBoardFile ebFile = new ElectricityBoardFile();

	CommonProperties common = CommonProperties.getObj();
	String fileName = "payment file.txt";
	File paymentFile = common.setFile(fileName);
	File tempFile = new File("temp.txt");
	
	
	public void createPayment(Payment payment) {
		String field = "paymentIdSeries";
		try(
				FileOutputStream fos = new FileOutputStream(this.paymentFile,true);
				PrintStream ps = new PrintStream(fos);
				){
			if(!this.paymentFile.exists() || this.paymentFile.length()==0) {
				addFields(ps);
				
			}
			printIntoFile(payment, ps);
			ps.flush();
			ps.close();
			ebFile.updateSeriesNo(field);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private void addFields(PrintStream ps) {
		ps.print("id,");
		ps.print("payableAmount,");
		ps.print("isPaid,");
		ps.print("dueDate,");
		ps.print("unitsConsumed");
		ps.println();
	}

	private void printIntoFile(Payment payment, PrintStream ps) {
		long id = payment.getId();
		double payableAmount = payment.getPayableAmount();
		boolean isPaid = payment.isPaid();
		String dueDate = payment.getDueDate().toString();
		Long unitsConsumed = payment.getUnitsConsumed();
		ps.print(id+",");
		ps.print(payableAmount+",");
		ps.print(isPaid+",");
		ps.print(dueDate+",");
		ps.print(unitsConsumed);
		ps.println();
		
	}
	public void updatePayment(Payment payment) {
		if(this.paymentFile.exists() && this.paymentFile.length() > 0) {
			try(
					FileOutputStream fos = new FileOutputStream(tempFile);
					PrintStream ps = new PrintStream(fos);
					FileReader fis = new FileReader(this.paymentFile);
					BufferedReader bis = new BufferedReader(fis);
					){
				addFields(ps);
				String currLine = bis.readLine();
				String[] record = currLine.split(",");
				int targetIndex = common.getIndex(record, "id");
				currLine = bis.readLine();
				while(currLine!= null) {
					record = currLine.split(",");
					if(Long.parseLong(record[targetIndex]) == payment.getId()) {
						printIntoFile(payment, ps);
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
				this.paymentFile.delete();
				tempFile.renameTo(this.paymentFile);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
//	long id,double payableAmount,boolean isPaid, double unitsConsumed,LocalDate dueDate
	public void loadPayment(Connection con, String[] payments) {
		if(this.paymentFile.exists()) {
			FileReader fis = null;
			BufferedReader bis = null;
			try{
				fis = new FileReader(this.paymentFile);
				bis = new BufferedReader(fis);
				String currLine = bis.readLine();
				String record[] = currLine.split(",");
				int idIndex = common.getIndex(record, "id");
				int amountIndex = common.getIndex(record, "payableAmount");
				int isPaidIndex = common.getIndex(record, "isPaid");
				int unitsIndex = common.getIndex(record, "unitsConsumed");
				int dateIndex = common.getIndex(record, "dueDate");
				if(!payments[0].equals("null")) {
					for(String p: payments) {
							currLine = bis.readLine();
							while(currLine != null) {
								record = currLine.split(",");
								Long id = Long.parseLong(record[idIndex]);
								if(id == Long.parseLong(p)) {
									Double amount = Double.parseDouble(record[amountIndex]);
									Boolean isPaid = Boolean.parseBoolean(record[isPaidIndex]);
									Long unitsConsumed = Long.parseLong(record[unitsIndex]);
									LocalDate dueDate = LocalDate.parse(record[dateIndex]);
									
									Payment payment = new Payment(id, amount, isPaid, unitsConsumed, dueDate);
									con.setPendingPayments(payment);
									bis.close();
									break;
								}
								currLine = bis.readLine();
								if(currLine == null)
									bis.close();
							}
						
						fis = new FileReader(this.paymentFile);
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

	public Payment getPayment(Long paymentId) {
		try(
				FileReader fis = new FileReader(this.paymentFile);
				BufferedReader bis = new BufferedReader(fis);
				){
			String currLine = bis.readLine();
			String record[] = currLine.split(",");
			int idIndex = common.getIndex(record, "id");
			int amountIndex = common.getIndex(record, "payableAmount");
			int isPaidIndex = common.getIndex(record, "isPaid");
			int unitsIndex = common.getIndex(record, "unitsConsumed");
			int dateIndex = common.getIndex(record, "dueDate");
			currLine = bis.readLine();
			while(currLine != null) {
				record = currLine.split(",");
				if(Long.parseLong(record[idIndex]) == paymentId) {
					Long id = Long.parseLong(record[idIndex]);
					Double amount = Double.parseDouble(record[amountIndex]);
					Boolean isPaid = Boolean.parseBoolean(record[isPaidIndex]);
					Long unitsConsumed = Long.parseLong(record[unitsIndex]);
					LocalDate dueDate = LocalDate.parse(record[dateIndex]);
					
					Payment payment = new Payment(id, amount, isPaid, unitsConsumed, dueDate);
					bis.close();
					return payment;
				}
				currLine = bis.readLine();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
