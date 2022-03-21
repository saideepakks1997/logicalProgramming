package connection_obj;

public class Domestic extends ConnectionObj{
	private int freeUnits = 100;
	
	public String toString() {
		String val = "--------------Category 1(Below 100 units)---------\n"
				+ "No charges free\n"
				+ "--------------Category 2(Below 200 units)---------\n"
				+ "0 to 100 units :-   free\n"
				+ "Next 100 units :-  1.5 rupees per unit\n"
				+ "--------------Category 3(Below 500 units)---------\n"
				+ "0 to 100 units:-   free\\n"
				+ "100 to 200 units:-  3 rupees per unit\n"
				+ "200 to 500 units:-  4 rupees per unit\n"
				+ "--------------Category 4(Above 500 units)---------\n"
				+ "0 to 100 units:-   free\\n"
				+ "100 to 200 units:-  3.5 rupees per unit\n"
				+ "200 to 500 units:-  4.5 rupees per unit\n"
				+ "Above 500 units:-  6.6 rupees per unit";
		return val;
	}
	
	public int getFreeUnits() {
		return freeUnits;
	}
	public void setFreeUnits(int freeUnits) {
		this.freeUnits = freeUnits;
	}

}
