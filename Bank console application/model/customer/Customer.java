package customer;

import java.util.*;

import account.*;
import card.*;

import main.MainApplication;

public class Customer {
	
	private long cust_id;
	private String name;
	private String dob;
	private Gender gender;
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
	
	//An customer can have multiple accounts of same branch or 
		//different branch but cust_id remains the same
	private List<IAccount> accounts = new ArrayList<>();
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
		return this.accounts.get(0);
	}
	public void setAccont(IAccount accont) {
		this.accounts.add(accont);
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
	public String getProof() {
		return proof;
	}
	public void setProof(String proof) {
		this.proof = proof;
	}
	public String getProofNO() {
		return proofNO;
	}
	public void setProofNO(String proofNO) {
		this.proofNO = proofNO;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
}
