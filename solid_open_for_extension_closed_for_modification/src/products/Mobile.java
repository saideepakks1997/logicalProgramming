package products;

public class Mobile extends Product{
	int cameraResolution;
	int BatteryInfo;
	
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
}
