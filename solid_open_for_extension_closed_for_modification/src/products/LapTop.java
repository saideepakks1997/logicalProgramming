package products;

public class LapTop extends Product{
	String proccessor;
	boolean isFingerPrintAvailable;
	String chargerCableType;
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
}
