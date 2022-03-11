package common_operations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CommonOperations implements ICommonOperations{
	@Override
	public LocalDate getValidDate(String dob) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			LocalDate time = LocalDate.parse(dob, formatter);
			return time;
		}
		catch (Exception e) {
			return null;
		}
	}
}
