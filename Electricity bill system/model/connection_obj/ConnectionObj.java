package connection_obj;

public abstract class ConnectionObj {
	protected double charges;
	
	public String getTarrif(){
		String val = "All units :- "+this.charges;
		return val;
	}
}
