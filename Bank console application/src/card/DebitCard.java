package card;

import account.IAccount;
import account.SavingAccount;

public class DebitCard extends Card{
	
	
	@Override
	public String getDetails() {
		return "1->Card No:- "+cardNo+"\n"
				+ "2->cvv:- "+cvv+"\n"
				+ "3->Expiry date:-"+expiryDate;
	}
	
	public DebitCard(long cardNo,int cvv,String expiryDate) {
		this.cardNo = cardNo;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
	}

	@Override
	public IAccount getAccount() {
		return this.accountInfo;
	}

	@Override
	public void setAccount(IAccount account) {
		this.accountInfo = account;
		
	}
	
}
