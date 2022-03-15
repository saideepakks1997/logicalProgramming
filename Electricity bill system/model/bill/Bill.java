package bill;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Bill {
	private long billNo;
	private Payment payment;
	private LocalDateTime paymentDate;
	
	public Bill(long billNo, Payment payment) {
		this.setBillNo(billNo);
		this.setPayment(payment);
		this.setPaymentDate(LocalDateTime.now());
	}
	
	public String toString() {
		String bill = "Bill No :-"+this.getBillNo()+"\n"
				+ "Paid amount :-"+this.getPayment().getPayableAmount()+"\n"
				+ "Units consumed :-"+this.getPayment().getUnitsConsumed()+"\n"
				+ "Paid date :- "+this.getPaymentDate();
		return bill;
	}
	
	public long getBillNo() {
		return billNo;
	}
	public void setBillNo(long billNo) {
		this.billNo = billNo;
	}
	public Payment getPayment() {
		return this.payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}
}
