package eb;

public interface Tarrifs {
	

	public static int domesFreeUnits = 100;
	public static double domes200lessThan200 = 1.5;
	public static double domes500lessThan200 = 2;
	public static double domes500above200 = 3;
	public static double domesAbove500lessThan200 = 3.5;
	public static double domesAbove500lessThan500 = 4.6;
	public static double domesAbove500Above500 = 6.6;
	
	public static int commercialMinUnits = 100;
	public static double commercialChargesBelowMin = 5;
	public static double commercialChargesAboveMin = 8.05;
	
	
	public static int workshopMinUnits = 120;
	public static double workshopChargesBelowMin = 2.85;
	public static double workshopChargesAboveMin = 5.75;
	
	public static int cottageMinUnits = 500;
	public static double cottageChargesBelowMin = 4;
	public static double cottageChargesAboveMin = 4.6;
	
	public static int loomsMinUnits = 1000;
	public static double loomsChargesBelowMin = 2.3;
	public static double loomsChargesAboveMin = 4.6;
	
	public static double industrialMetroCharges = 6.35;
	
	public static double tempSupplyCharges = 12;
	
	public static double lightTowncharges = 6.35;
	
	public static double govnPlacesCharges = 5.75;
	
	public static double privateHospitalCharges = 7.5;
	
	
}
