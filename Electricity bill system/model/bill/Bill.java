package bill;

import java.io.Serializable;
import java.time.LocalDateTime;

import payment_options.ConsumerPaymentOptions;

public class Bill implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long billNo;
	private Payment payment;
	private LocalDateTime paymentDate;
	private String paidThrough;
	
	public Bill(long billNo, Payment payment,String paidThrough) {
		this.setBillNo(billNo);
		this.setPayment(payment);
		this.setPaymentDate(LocalDateTime.now());
		this.setPaidThrough(paidThrough);
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

	public String getPaidThrough() {
		return paidThrough;
	}

	public void setPaidThrough(String paidThrough) {
		this.paidThrough = paidThrough;
	}
}
