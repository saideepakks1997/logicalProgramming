package payment_options;

public enum AdminPaymentOptions {
	Cash("Cash"),
	DemandDraft("Demand Draft"),
	Cheque("Cheque");
	
	private String displayName;
	private AdminPaymentOptions(String displayName) {
		this.displayName = displayName;
	}
	public String displayName() {
		return this.displayName;
	}
	public String toString() {
		return this.displayName;
	}
}
