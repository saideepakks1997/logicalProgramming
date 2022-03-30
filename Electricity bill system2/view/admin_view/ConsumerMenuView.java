package admin_view;

import common_view.CommonView;
import common_view.DisplayView;
import common_view.UserInputView;
import consumer_view.ConsumerLoginView;

import eb.ElectricityBoard;

public class ConsumerMenuView {
	DisplayView display = new DisplayView();
	CommonView commonView = null;
	ConsumerLoginView consumerView = null;
	UserInputView input = new UserInputView();

	
	String user_name = null;
	boolean isAdmin = true;
	public ConsumerMenuView(ElectricityBoard eb) {
		this.commonView = new CommonView(eb);
		this.consumerView = new ConsumerLoginView(eb);
	}

	public void askOptions(int consumerNo) {
		boolean loop = true;
		
		this.consumerView.consumerNo = consumerNo;
		while(loop) {
			display.displayMessege("Enter option \n"
					+ "1->View Connection details\n"
					+ "2->Pay bills\n"
					+ "3->View pendings transaction\n"
					+ "4->View transactions\n"
					+ "9->Go back to previous menu");
			Integer opt = input.getInt();
			try {
				switch (opt) {
				case 1: consumerView.viewConnectionDetails();
					break;
				case 2: consumerView.payBillForParticularConsumer(isAdmin);
					break;
				case 3: consumerView.viewPendingTransactions();
					break;
				case 4: consumerView.viewAllBills();
					break;
				case 9: display.displayMessege("Going back to previous menu");
					loop = false;
					break;
				default:display.displayMessege("Enter correct option");
					break;
				}
			}
			catch(NullPointerException e) {
				return;
			}
		}
		
	}
}
