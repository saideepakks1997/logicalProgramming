package payment_options;

public enum ConsumerPaymentOptions {
	UPI("upi "),
	DebitCard("Debit card"),
	CreditCard("Credit card"),
	NetBanking("Net Banking");
	
	
	private String displayName;
	private ConsumerPaymentOptions(String displayName) {
		this.displayName = displayName;
	}
	public String displayName() {
		return this.displayName;
	}
	public String toString() {
		return this.displayName;
	}
}
