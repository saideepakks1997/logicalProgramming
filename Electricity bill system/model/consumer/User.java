package consumer;

import java.time.LocalDate;

public class User {
	protected String name;
	protected String emailId;
	protected long phoNo;
	protected String address;
	protected String gender;
	protected LocalDate dob;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getPhoNo() {
		return phoNo;
	}
	public void setPhoNo(long phoNo) {
		this.phoNo = phoNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
}
