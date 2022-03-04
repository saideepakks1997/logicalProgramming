package card;
//It can be debit card or credit card

import account.*;
public class Card {
	protected Long cardNo;
	private int cvv;
	private int atmPin;
	protected String expiryDate;
	protected Account accountInfo;
	
//	private DebitCardType cardType;
	
	public Account getAccount(){
		return this.accountInfo;
		}
	public void setAccount(Account account) {
		this.accountInfo = account;
	}
	
	//validate atm pin
	public boolean validatePin(int pin){
		return this.atmPin == pin;
	}
	
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public void setPin(int pin) {
		this.atmPin = pin;
	}
	
	public Long getCardNo() {
		return this.cardNo;
	}
	
	
}
