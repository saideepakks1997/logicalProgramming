package main;

import bank.Bank;
import card.*;
import main_view.MainView;

public class MainApplication {
	public static void main(String[] args) {
		Bank bank = new Bank();
		MainView mainView = new MainView(bank);
		mainView.askCustomer();
	}
}
