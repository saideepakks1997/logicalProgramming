package products;

public abstract class Product {
		double cost;
		double gst;
		String brand;
		public double calculatePriceIncludingGst() {
			double tax = cost * (gst/100);
			double totalCost = tax + cost;
			return totalCost;
		}
}
