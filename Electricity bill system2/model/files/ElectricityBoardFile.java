package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

import eb.ElectricityBoard;

public class ElectricityBoardFile implements IElectricityBoardFile{
	CommonProperties common = CommonProperties.getObj();
	
	String fileName = "Electricity board file.txt";
	File ebFile = common.setFile(fileName);
	File tempFile = new File("temp.txt");
	
	public ElectricityBoard getElectricityBoardObj() {
		ElectricityBoard eb = new ElectricityBoard();
		if(!this.ebFile.exists() || this.ebFile.length() == 0) {
			createSeriesNoFile(eb);
			return eb;
		}
		else {
			loadSeriesNo(eb);
			loadConsumers(eb);
		}
		return eb;
	}
	private void loadConsumers(ElectricityBoard eb) {
		ConsumerFiles consumerFile = new ConsumerFiles();
		consumerFile.loadConsumersToEb(eb);
		
		
	}
	private void loadSeriesNo(ElectricityBoard eb) {
		try (
				FileReader fis = new FileReader(this.ebFile);
				BufferedReader bis = new BufferedReader(fis);
				){
			String currLine = bis.readLine();
			String[] record = currLine.split("=");
			eb.setBillNoSeries(Long.parseLong(record[1]));
			
			currLine = bis.readLine();
			record = currLine.split("=");
			eb.setConnectionNoSeries(Long.parseLong(record[1]));
			
			currLine = bis.readLine();
			record = currLine.split("=");
			eb.setConsumerNoSeries(Long.parseLong(record[1]));
			
			currLine = bis.readLine();
			record = currLine.split("=");
			eb.setRequestNoSeries(Long.parseLong(record[1]));
			
			currLine = bis.readLine();
			record = currLine.split("=");
			eb.setPaymentIdSeries(Long.parseLong(record[1]));
			bis.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createSeriesNoFile(ElectricityBoard eb) {
		try(
				FileOutputStream fos = new FileOutputStream(this.ebFile);
				PrintStream ps = new PrintStream(fos);
				){
			if(!this.ebFile.exists() || this.ebFile.length()==0) {
				ps.println("billNoSeries="+eb.getBillNoSeries());
				ps.println("connNoSeries="+eb.getConnNoSeries());
				ps.println("consumerNoSeries="+eb.getConsumerNoSeries());
				ps.println("requestNoSeries="+eb.getRequestNoSeries());
				ps.println("paymentIdSeries="+eb.getPaymentIdSeries());
				ps.println();
				ps.flush();
				ps.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	private void printIntoFile(ElectricityBoard eb, PrintStream ps) {
//		ps.print(eb.getBillNoSeries()+",");
//		ps.print(eb.getConnNoSeries()+",");
//		ps.print(eb.getConsumerNoSeries()+",");
//		ps.print(eb.getRequestNoSeries()+",");
//		ps.print(eb.getPaymentIdSeries());
//		ps.println();
//	}
	
	public void updateSeriesNo(String field) {
		try(
				FileOutputStream fos = new FileOutputStream(this.tempFile);
				PrintStream ps = new PrintStream(fos);
				FileReader fis = new FileReader(this.ebFile);
				BufferedReader bis = new BufferedReader(fis);
				){
			String currLine = bis.readLine();
			
			while(currLine != null) {
				String[] record = currLine.split("=");
				if(record[0].equals(field)) {
					Long val = Long.parseLong(record[1]);
					val++;
					ps.println(field+"="+val);
				}
				else {
					ps.println(currLine);
				}
				currLine = bis.readLine();
			}
			ps.flush();
			ps.close();
			bis.close();
			this.ebFile.delete();
			this.tempFile.renameTo(this.ebFile);
		}
		catch(Exception e) {
			
		}
	}
}
