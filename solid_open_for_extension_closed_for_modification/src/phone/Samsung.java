package phone;

public class Samsung implements Phone{
	String name;
	String color;
	int cameraResolution = 16;
	int internalMemory = 128;
	double currentVersion = 7.0;
	public Samsung(String name,String color) {
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
