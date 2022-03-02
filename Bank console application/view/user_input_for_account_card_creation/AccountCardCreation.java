package user_input_for_account_card_creation;
import java.util.*;

import account.CreateAccount;
import account.IAccount;
import card.CreateCard;
import card.ICard;
import customer.Customer;
public class AccountCardCreation {
	static Scanner sc = new Scanner(System.in);
	
	
	public ICard getAccountCreationType() {
		CreateAccount createAccount = new CreateAccount();
		CreateCustomer createCustomer = new CreateCustomer();
		CreateCard createCard = new CreateCard();
		
		System.out.println("select 1-> creating savings account");
		int opt = sc.nextInt(); 
		
		Customer customer = createCustomer.createCustomer();
		IAccount account = null;
		if(opt == 1) {
			 account = createAccount.createAccount(customer);
		}
		System.out.println("Select 1 to create debit card");
		opt = sc.nextInt();
		ICard card = null;
		if(opt == 1) {
			card = createCard.createCard(account,customer);
		}
			
		return card;
	}
}
