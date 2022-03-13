package bill;

import java.time.LocalDate;

public class Bill {
	private long billNo;
	private double payableAmount;
	private LocalDate dueDate;
	private int unitsConsumed;
	private LocalDate paymentDate;
	
	public long getBillNo() {
		return billNo;
	}
	public void setBillNo(long billNo) {
		this.billNo = billNo;
	}
	public double getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(double payableAmount) {
		this.payableAmount = payableAmount;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public int getUnitsConsumed() {
		return unitsConsumed;
	}
	public void setUnitsConsumed(int unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
}
