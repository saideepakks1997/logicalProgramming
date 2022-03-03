package card;

import account.*;

public class DebitCard extends Card{
	private int cvv;
	private int atmPin ;
//	private DebitCardType cardType;
	
	
	public DebitCard(long cardNo,int cvv,String expiryDate,int atmPin) {
		this.cardNo = cardNo;
		this.setCvv(cvv);
		this.expiryDate = expiryDate;
		this.atmPin = atmPin;
//		this.cardType = DebitCardType.MasterCardDebitCard;
	}
	
//	pin validation
	public boolean validatePin(int pin) {
		return this.atmPin == pin;
	}
	
	public void setPin(int pin) {
		this.atmPin = pin;
	}
	
	public SavingsAccount getAccount() {
		return (SavingsAccount) this.accountInfo;
	}
	
	public void setAccount(Account account) {
		this.accountInfo = (this.accountInfo == null) ? account:this.accountInfo;
	}

	
}
