package connection_obj;


public  class ConnectionObj {
	protected double charges;
	
	@Override
	public String toString() {
		String val = "All units :- "+this.charges +" rupees per unit";
		return val;
	}
	
	public double calculateBill(double units) {
		return this.charges * units;
	}
}
