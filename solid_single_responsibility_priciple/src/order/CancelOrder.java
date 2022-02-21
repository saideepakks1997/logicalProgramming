package order;

public class CancelOrder {
	private Order order;
		public void cancelOrder() {
			order.setOrderId(-1);
			System.out.println(order.getItemName()+" has been cancelled successfully");
		}
		
		public Order getOrder() {
			return order;
		}
		public void setOrder(Order order) {
			this.order = order;
		}
}
