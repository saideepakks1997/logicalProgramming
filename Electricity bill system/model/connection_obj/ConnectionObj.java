package connection_obj;

public abstract class ConnectionObj {
	protected double charges;
	
	@Override
	public String toString() {
		String val = "All units :- "+this.charges +" rupees per unit";
		return val;
	}
}
