package eb;


public enum RequestStatus {
	status1("Requested"),
	status2("Application Accepted and moved to AE"),
	status3("Background verification of documents started"),
	status4("Background verification done and Applicatin approved");
	
	private String displayName;
	private RequestStatus(String displayName) {
		this.displayName = displayName;
	}
	public String displayName() {
		return this.displayName;
	}
	public String toString() {
		return this.displayName;
	}
}
