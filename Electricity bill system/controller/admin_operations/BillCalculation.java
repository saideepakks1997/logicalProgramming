package admin_operations;

import connection.TypeOfConnection;

public class BillCalculation {
	public double calculateBill(TypeOfConnection connType,double units) {
		if(connType == TypeOfConnection.Domestic) {
			double costPerUnit = TypeOfConnection.Domestic.getValue();
			return costPerUnit * units;
		}
		else if(connType == TypeOfConnection.ltCommercial) {
			double costPerUnit = TypeOfConnection.ltCommercial.getValue();
			return costPerUnit * units;
		}
		return 0;
	}
}
