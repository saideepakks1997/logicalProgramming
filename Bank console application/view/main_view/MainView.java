package main_view;

import atm_center.AtmMachine;
import atm_center.IAtmMachine;
import atm_view.AtmView;
import bank.Bank;
import bank_view.BankView;
import common_view.CommonView;

public class MainView {
	
	CommonView commonView = new CommonView();
	BankView bankView = null;
	AtmView atmView = null;
	
	public MainView(Bank bank) {
		this.bankView = new BankView(bank);
		this.atmView = new AtmView(bank);
	}
	
	public void askCustomer() {
		boolean loop = true;
		while(loop) {
			commonView.displayMessege("Enter option\n"
					+ "1->Go to bank\n"
					+ "2->Go to ATM\n"
					+ "3->Exit");
			int opt = commonView.getOption();
			switch (opt) {
			case 1: bankView.askCustomerBankOptions(); 
				break;
			case 2: atmView.atmScreen();
				break;
			case 3: loop = false;
				commonView.displayMessege("Thank you");
				break;
			default: commonView.displayMessege("Enter correct option");
				break;
			}
		}
		
	}
}
