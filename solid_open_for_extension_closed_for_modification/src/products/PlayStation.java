package products;

public class PlayStation extends Product{

	double version;
	boolean isTriplePlayerAvailable;
	public PlayStation() {
		this.brand = "Sony";
		this.gst = 20;
		this.cost = 20000;
	}

	public void playGame() {
		System.out.println("Start the game");
	}
	public void insertMemoryCard() {
		System.out.println("Insert memory card for saving your play");
	}
	public void insertJoyStick() {
		System.out.println("Insert one more joy stick he will join too");
	}
}
