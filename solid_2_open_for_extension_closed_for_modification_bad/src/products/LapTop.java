package products;

public class LapTop {
	String proccessor;
	boolean isFingerPrintAvailable;
	String chargerCableType;
	int cost;
	double gst;
	String brand;
	public LapTop() {
		this.brand = "Lenova";
		this.gst = 18;
		this.cost = 50000;
	}
	public void watchMovie() {
		System.out.println("Watch the movie");
	}
	public void doCoding() {
		System.out.println("Open eclipse and do coding");
	}
	public void openMail() {
		System.out.println("Check the mail");
	}
	
	public double calculatePriceIncludingGst() {
		double tax = cost * (this.gst/100);
		double totalCost = tax + cost;
		return totalCost;
	}
}
