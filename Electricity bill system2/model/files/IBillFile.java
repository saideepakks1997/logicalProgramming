package files;

import bill.Bill;
import connection.Connection;

public interface IBillFile {
	public void createBill(Bill bill);
	public void loadBills(Connection con, String[] bills);
}
