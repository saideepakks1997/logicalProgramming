package bill_calculation_operations;

public abstract class CalculateBill {
	double chargesPerUnit;
		public double calculteBillAmount(double units) {
			return this.chargesPerUnit * units;
		}
}
