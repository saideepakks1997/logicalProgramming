package card;

import account.*;
import bank.Bank;

public class DebitCard implements ICard{
	private long cardNo;
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
	}
	@Override
	public void setAccount(IAccount account) {
		this.accountInfo = account;
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
	public void setCardNo(long cardNo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setCvv(int cvv) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public IAccount getAccount() {
		// TODO Auto-generated method stub
		return this.accountInfo;
	}
}
