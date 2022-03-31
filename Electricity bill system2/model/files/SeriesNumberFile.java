package files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import eb.ElectricityBoard;

public class SeriesNumberFile {
	CommonProperties common = CommonProperties.getObj();
	String fileName = "Series No.txt";
	File serieseNoFile = common.setFile(fileName);
	
	
//	private long billNoSeries = 111111;
//	private long connNoSeries = 22222;
//	private long consumerNoSeries = 1;
//	private long requestNoSeries= 1;
//	private long paymentIdSeries = 1;
	public void createSeriesNoFile(ElectricityBoard eb) {
		try(
				FileOutputStream fos = new FileOutputStream(this.serieseNoFile);
				PrintStream ps = new PrintStream(fos);
				){
			if(!this.serieseNoFile.exists() || this.serieseNoFile.length()==0) {
				ps.print("billNoSeries,");
				ps.print("connNoSeries,");
				ps.print("consumerNoSeries,");
				ps.print("requestNoSeries,");
				ps.print("paymentIdSeries");
				ps.println();
			}
			printIntoFile(eb, ps);
			ps.println();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void printIntoFile(ElectricityBoard eb, PrintStream ps) {
		ps.print(eb.getBillNoSeries()+",");
		ps.print(eb.getConnNoSeries()+",");
		ps.print(eb.getConsumerNoSeries()+",");
		ps.print(eb.getRequestNoSeries()+",");
		ps.print(eb.getPaymentIdSeries());

		
	}
}
