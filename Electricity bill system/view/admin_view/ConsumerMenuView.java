package admin_view;

import common_operations.CommonOperations;
import common_operations.ICommonOperations;
import common_view.CommonView;
import consumer_operations.IConsumerOperations;
import consumer_view.ConsumerLoginView;

import eb.ElectricityBoard;

public class ConsumerMenuView {
	CommonView commonView = null;
	ConsumerLoginView consumerView = null;
	String user_name = null;
	public ConsumerMenuView(ElectricityBoard eb) {
		this.commonView = new CommonView(eb);
		this.consumerView = new ConsumerLoginView(eb);
	}

	public void askOptions(int consumerNo) {
		boolean loop = true;
		boolean isAdmin = true;
		this.consumerView.consumerNo = consumerNo;
		while(loop) {
			commonView.displayMessege("Enter option \n"
					+ "1->View Connection details\n"
					+ "2->Pay bills\n"
					+ "3->View pendings transaction\n"
					+ "4->View transactions\n"
					+ "9->Go back to previous menu");
			int opt = commonView.getInt();
			
			switch (opt) {
			case 1: consumerView.viewConnectionDetails();
				break;
			case 2: consumerView.payBill(isAdmin);
				break;
			case 3: consumerView.viewPendingTransactions();
				break;
			case 4: consumerView.viewAllBills();
				break;
			case 9: commonView.displayMessege("Going back to previous menu");
				loop = false;
				break;
			default:commonView.displayMessege("Enter correct option");
				break;
			}
		}
		
		
	}

}
