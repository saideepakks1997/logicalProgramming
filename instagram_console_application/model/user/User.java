package user;

import java.time.LocalDate;

public class User {
	protected String name;
	protected String website;
	protected String email;
	protected Long phNo;
	protected Gender gender;
	protected LocalDate dob;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhNo() {
		return phNo;
	}
	public void setPhNo(Long l) {
		this.phNo = l;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
}
