package account;
import java.util.*;

import customer.*;
public interface IJointAccount {
	public List<Nominee> getNominees();
	public List<Customer> getCustomers();
	
	public ModeOfOperation getMode();
	public void setMode(ModeOfOperation mode);
}
