package order;

public class BillCalculation {
	private Order order;
    public BillCalculation(Order order)
    {
        this.order = order;
    }
  
    public void calculateBill()
    {
        int totalAmt = 300 *  this.order.getQuantity();
        this.order.setTotalBillAmt(totalAmt);
        System.out.println("Order Summary \n"
		+ this.order.getItemName()+ " ordered\n"
		+ "Nos "+this.order.getQuantity()
		+"Amount :-"+this.order.getTotalBillAmt()
        		);
    }
    
    public Order getOrder() {
    	return this.order;
    }
    
    public void setOrder(Order order) {
    	this.order = order;
    }
}
