package inheritance_hierarchical;

public class Bike {
	String fuelType = "Petrol";
	int cost;
	String modelName;
	int cc;
	int mielage;
	public void start() {
		System.out.println("Vehicle started");
	}
	public void applyBreak() {
		System.out.println("Break applied");
	}
	public void adjustTheMirror() {
		System.out.println("Mirror is adjusted");
	}
}
