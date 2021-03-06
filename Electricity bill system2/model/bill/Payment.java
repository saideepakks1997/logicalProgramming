package bill;

import java.time.LocalDate;

public class Payment {
	private long id;
	private double payableAmount;
	private boolean isPaid;
	private LocalDate dueDate;
	private Long unitsConsumed;
	
	public Payment(long id,double payableAmount, Long unitsConsumed) {
		this.id = id;
		this.payableAmount = payableAmount;
		this.isPaid = false;
		this.unitsConsumed = unitsConsumed;
		this.dueDate = LocalDate.now().plusDays(10);
	}
	public Payment(long id,double payableAmount,boolean isPaid, Long unitsConsumed,LocalDate dueDate) {
		this.id = id;
		this.payableAmount = payableAmount;
		this.isPaid = isPaid;
		this.unitsConsumed = unitsConsumed;
		this.dueDate = dueDate;
	}
	public String toString() {
		String payment = "Payable amount :- "+(double)Math.round(this.getPayableAmount() * 100) / 100 +"  "
				+ "Units Consumed :- "+this.getUnitsConsumed()+"  "
				+ "Due date :- "+this.getDueDate();
		return payment;
	}
	public double getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(double payableAmount) {
		this.payableAmount = payableAmount;
	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public Long getUnitsConsumed() {
		return unitsConsumed;
	}
	public void setUnitsConsumed(Long unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
