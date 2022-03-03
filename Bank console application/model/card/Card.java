package card;
//It can be debit card or credit card

import account.*;
public class Card {
	protected Long cardNo;
	private int cvv;
	protected String expiryDate;
	protected Account accountInfo;
	private int atmPin ;
//	private DebitCardType cardType;
	
	public Account getAccount(){
		System.out.println(this.cardNo);
		System.out.println(this.accountInfo.getAccountNo());
		return this.accountInfo;
		}
	
	//get atm pin
	public boolean validatePin(int pin){
		return this.atmPin == pin;
	}
	
	public void setAccount(Account account) {
		// TODO Auto-generated method stub
		
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public Long getCardNo() {
		return this.cardNo;
	}
}
