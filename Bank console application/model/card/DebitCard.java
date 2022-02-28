package card;

import account.*;
import account.SavingAccount;
import bank.Bank;

public class DebitCard extends Card{
	
	
	@Override
	public String getDetails() {
		return "1->Card No:- "+cardNo+"\n"
				+ "2->cvv:- "+cvv+"\n"
				+ "3->Expiry date:-"+expiryDate;
	}
	
	public DebitCard(long cardNo,int cvv,String expiryDate,int atmPin) {
		this.cardNo = cardNo;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
		this.atmPin = atmPin;
	}

	@Override
	public Account getAccount() {
		return this.accountInfo;
	}

	@Override
	public void setAccount(Account account) {
		this.accountInfo = account;
		
	}
}
