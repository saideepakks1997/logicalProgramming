package main;

import bank.Bank;
import card.*;
import user_inputs.GetUserInputs;

public class MainApplication {
	public static void main(String[] args) {
		Bank bank = new Bank();
		GetUserInputs inputs = new GetUserInputs(bank);
		Card card = null;
		
		//create customer account and card
		inputs.askCustomer();
		
		//get card using card no
		card = inputs.getCard();
		
		//atm screen
		inputs.atmScreen(card);
	}
}
