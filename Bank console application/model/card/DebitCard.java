package card;

public class DebitCard extends Card{
	public DebitCard(long cardNo,int cvv,String expiryDate,int atmPin) {
		this.cardNo = cardNo;
		this.setCvv(cvv);
		this.expiryDate = expiryDate;
		this.setPin(atmPin);;
	}
}
