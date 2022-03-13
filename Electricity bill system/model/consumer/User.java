package consumer;

import java.time.LocalDate;

public class User {
	protected String name;
	protected String emailId;
	protected long phoNo;
	protected String address;
	protected String gender;
	protected LocalDate dob;
	protected String user_name;
	private String password;
	
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
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}
}
