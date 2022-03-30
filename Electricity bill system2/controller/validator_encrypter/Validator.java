package validator_encrypter;

import java.util.regex.*;
import java.util.regex.Pattern;

public class Validator {
	
	 private String PASSWORD_REGEX = 
			"((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,15})";
	 private String EMAIL_REGEX =  "^([A-Za-z\\d\\.-]+)@([A-Za-z\\d-]+)\\.([A-Za-z]{1,8})(\\.[A-Za-z] {1,8})?$";
	 private String PHONO_NO_REGEX = "(0|91)?[7-9][0-9]{9}";
	 private int maxChancesForValidation = 3;
				
		public boolean validatePassword(String password) {
			boolean isValid = validate(PASSWORD_REGEX, password);
			return isValid;
		}
		public boolean validateEmail(String email) {
			boolean isValid = validate(EMAIL_REGEX, email);
			return isValid;
		}
		public boolean validatePhoNo(Long phoNo) {
			boolean isValid = validate(PHONO_NO_REGEX, phoNo.toString());
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

