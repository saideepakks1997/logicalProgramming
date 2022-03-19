package bill_calculation_operations;

import connection.TypeOfConnection;

public  interface ICalculateBill {
		public double calculteBillAmount(double units,TypeOfConnection type);
}
