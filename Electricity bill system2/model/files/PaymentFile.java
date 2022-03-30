package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.time.LocalDate;

import bill.Payment;
import consumer.Consumer;

public class PaymentFile {
	
	CommonProperties common = CommonProperties.getObj();
	File paymentFile = null;
	public PaymentFile() {
		setFile();
	}
	private void setFile() {
		File objFile = new File("All Files");
		objFile.mkdir();
		this.paymentFile = new File(objFile+File.separator+"Consumer File.txt");
		
	}

	
	
	public void createPayment(Payment payment) {
		try(
				FileOutputStream fos = new FileOutputStream(this.paymentFile,true);
				PrintStream ps = new PrintStream(fos);
				){
			if(!this.paymentFile.exists() || this.paymentFile.length()==0) {
				ps.print("id,");
				ps.print("payableAmount,");
				ps.print("isPaid,");
				ps.print("dueDate,");
				ps.print("unitsConsumed");
				ps.println();
			}
			printIntoFile(payment, ps);
			ps.println();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void printIntoFile(Payment payment, PrintStream ps) {
		long id = payment.getId();
		double payableAmount = payment.getPayableAmount();
		boolean isPaid = payment.isPaid();
		String dueDate = payment.getDueDate().toString();
		double unitsConsumed = payment.getUnitsConsumed();
		ps.print(id+",");
		ps.print(payableAmount+"payableAmount,");
		ps.print(isPaid+",");
		ps.print(dueDate+"dueDate,");
		ps.print(unitsConsumed+"unitsConsumed");
		
	}
	public void updatePayment(Payment payment) {
		if(this.paymentFile.exists() && this.paymentFile.length() > 0) {
			try(
					FileOutputStream fos = new FileOutputStream(common.getTempFile());
					PrintStream ps = new PrintStream(fos);
					FileReader fis = new FileReader(this.paymentFile);
					BufferedReader bis = new BufferedReader(fis);
					){
				String currLine = bis.readLine();
				String[] record = currLine.split(",");
				int targetIndex = common.getIndex(record, "id");
				currLine = bis.readLine();
				while(currLine!= null) {
					record = currLine.split(",");
					if(Long.parseLong(record[targetIndex]) == payment.getId()) {
						printIntoFile(payment, ps);
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
				this.paymentFile.delete();
				common.getTempFile().renameTo(this.paymentFile);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
