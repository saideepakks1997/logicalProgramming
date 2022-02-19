package phone;

public class Iphone implements Phone{
	String name;
	String color;
	int cameraResolution = 32;
	int internalMemory = 64;
	double currentVersion = 13.0;
	public Iphone(String name,String color) {
		this.name = name;
		this.color = color;
	}
	@Override
	public void openCamera() {
		System.out.println("Camera with "+this.cameraResolution+"MP has opened");
	}

	@Override
	public void downloadFile(int fileSize) {
		this.internalMemory -= fileSize;
			System.out.println("File has been downloaded ");
			System.out.println("Remaining space "+this.internalMemory);
		}

	@Override
	public void updateNextVersion() {
		System.out.println("Current version is "+this.currentVersion);
		this.currentVersion += 1;
		System.out.println("Updated to "+this.currentVersion);
	}

}
