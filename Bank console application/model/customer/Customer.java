package customer;

import java.util.*;

import account.*;
import bank.*;
import card.*;

import main.MainApplication;

public class Customer {
	private String name;
	private String dob;
	private long adharCardNo;
	private String address;
	private long phnNo;
	
	public Account accont;
	public Card debitCard;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public long getAdharCardNo() {
		return adharCardNo;
	}
	public void setAdharCardNo(long adharCardNo) {
		this.adharCardNo = adharCardNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhnNo() {
		return phnNo;
	}
	public void setPhnNo(long phnNo) {
		this.phnNo = phnNo;
	}
	
}
