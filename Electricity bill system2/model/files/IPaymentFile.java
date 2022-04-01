package files;

import bill.Payment;
import connection.Connection;

public interface IPaymentFile {
	public void createPayment(Payment payment);
	public void updatePayment(Payment payment);
	public void loadPayment(Connection con, String[] payments);
	public Payment getPayment(Long paymentId);
}
