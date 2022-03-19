package connection_obj;

public class Domestic extends ConnectionObj{
	private int freeUnits = 100;
	
	@Override
	public String getTarrif() {
		return null;
		// TODO Auto-generated method stub
		
	}
	public int getFreeUnits() {
		return freeUnits;
	}
	public void setFreeUnits(int freeUnits) {
		this.freeUnits = freeUnits;
	}

}
