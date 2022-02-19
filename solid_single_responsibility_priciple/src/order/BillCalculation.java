package order;

public class BillCalculation {
	private Order order;
    public BillCalculation(Order order)
    {
        this.order = order;
    }
  
    public void calculateBill()
    {
        int totalAmt = 300 *  this.order.quantity;
        this.order.totalBillAmt = totalAmt;
        System.out.println("Order Summary \n"
		+ this.order.itemName+ " ordered\n"
		+ "Nos "+this.order.quantity
		+"Amount :-"+this.order.totalBillAmt
        		);
    }
}
