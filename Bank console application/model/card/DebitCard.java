package card;

import account.*;
import bank.Bank;

public class DebitCard implements ICard{
	private Long cardNo;
	private int cvv;
	private String expiryDate;
	private IAccount accountInfo;
	private int atmPin ;
	private DebitCardType cardType;
	
	
	public DebitCard(long cardNo,int cvv,String expiryDate,int atmPin) {
		this.cardNo = cardNo;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
		this.atmPin = atmPin;
		this.cardType = DebitCardType.MasterCardDebitCard;
	}
	
//	pin validation
	public boolean validatePin(int pin) {
		return this.atmPin == pin;
	}
	@Override
	public void setPin(int pin) {
		this.atmPin = pin;
	}
	@Override
	public IAccount getAccount() {
		return this.accountInfo;
	}

	@Override
	public void setAccount(IAccount account) {
		this.accountInfo = (this.accountInfo == null) ? account:this.accountInfo;
	}
}
