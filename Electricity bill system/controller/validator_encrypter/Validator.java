package validator_encrypter;

import java.util.regex.*;
import java.util.regex.Pattern;

public class Validator {
	
	 private String PASSWORD_REGEX = 
			"((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,15})";
		private   String EMAIL_REGEX = 
				"^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$" ;
	 private int maxChancesForValidation = 3;
				
		public boolean validatePassword(String password) {
			boolean isValid = validate(PASSWORD_REGEX, password);
			return isValid;
		}
		public boolean validateEmail(String email) {
			boolean isValid = validate(EMAIL_REGEX, email);
			return isValid;
		}
		private boolean validate(String regex, String field) {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(field);
			return matcher.matches();
		}
		public int getMaxChance() {
			return maxChancesForValidation;
		}
		public void setMaxChance(int maxChance) {
			this.maxChancesForValidation = maxChance;
		}
}
