package connection_obj;

public class TemporarySupply extends ConnectionObj{

	public TemporarySupply() {
		this.charges = 12;
	}
	
	public double getCharges() {
		return this.charges;
	}
	public void setCharges(double charges) {
		this.charges = charges;
	}
}
