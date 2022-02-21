package products;

public class Mobile {
	int cameraResolution;
	int BatteryInfo;
	String brand;
	double gst;
	int cost;
	
	public Mobile() {
		this.brand = "Samsung";
		this.gst = 10;
		this.cost = 35000;
	}
	public void openCamera() {
		System.out.println("Open front camera and take selfie");
	}
	public void listenMusic() {
		System.out.println("Open jio music and listen to song");
	}
	public void browse() {
		System.out.println("Switch on the internet to browse");
	}
	
	public double calculatePriceIncludingGst() {
		double tax = cost * (gst/100);
		double totalCost = tax + cost;
		return totalCost;
	}
}
