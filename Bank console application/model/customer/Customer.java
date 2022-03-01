package customer;

import java.util.*;

import account.*;
import bank.*;
import card.*;

import main.MainApplication;

public class Customer {
	
	private long cust_id;
	private String name;
	private String dob;
	private String gender;
	//adharcard,pan card,lisence etc.
	private String proof;
	private String proofNO;
	private String address;
	private long phnNo;
	private String emailId;
	private String nationality;
	//private sector,public sector,government sector,self employed
	//others etc.
	private String occupation;
	
	private String religion;
	private String category;
	private String education;
	
	//An customer can have multiple accounts of same branch or 
		//different branch but cust_id remains the same
	private List<IAccount> accont = new ArrayList<>();
	private List<ICard> cards = new ArrayList<ICard>();
	
	
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
//	public long getAdharCardNo() {
//		return proff;
//	}
//	public void setAdharCardNo(long adharCardNo) {
//		this.adharCardNo = adharCardNo;
//	}
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
	public IAccount getAccont() {
		return this.accont.get(0);
	}
	public void setAccont(IAccount accont) {
		this.accont.add(accont);
	}
	public ICard getDebitCard() {
		return null;
	}
	public void setDebitCard(ICard debitCard) {
		this.cards.add(debitCard);
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getCust_id() {
		return cust_id;
	}
}
