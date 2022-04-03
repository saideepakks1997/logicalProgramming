package eb;

public class Tarrifs {
	

	private int domesFreeUnits = 100;
	private double domes200lessThan200 = 1.5;
	private double domes500lessThan200 = 2;
	private double domes500above200 = 3;
	private double domesAbove500lessThan200 = 3.5;
	private double domesAbove500lessThan500 = 4.6;
	private double domesAbove500Above500 = 6.6;
	
	private int commercialMinUnits = 100;
	private double commercialChargesBelowMin = 5;
	private double commercialChargesAboveMin = 8.05;
	
	
	private int workshopMinUnits = 120;
	private double workshopChargesBelowMin = 2.85;
	private double workshopChargesAboveMin = 5.75;
	
	private int cottageMinUnits = 500;
	private double cottageChargesBelowMin = 4;
	private double cottageChargesAboveMin = 4.6;
	
	private int loomsMinUnits = 1000;
	private double loomsChargesBelowMin = 2.3;
	private double loomsChargesAboveMin = 4.6;
	
	private double industrialMetroCharges = 6.35;
	
	private double tempSupplyCharges = 12;
	
	private double lightTowncharges = 6.35;
	
	private double govnPlacesCharges = 5.75;
	
	private double privateHospitalCharges = 7.5;
	
	public int getDomesFreeUnits() {
		return domesFreeUnits;
	}

	public double getDomes200lessThan200() {
		return domes200lessThan200;
	}

	public double getDomes500lessThan200() {
		return domes500lessThan200;
	}

	public double getDomes500above200() {
		return domes500above200;
	}

	public double getDomesAbove500lessThan200() {
		return domesAbove500lessThan200;
	}

	public double getDomesAbove500lessThan500() {
		return domesAbove500lessThan500;
	}

	public double getDomesAbove500Above500() {
		return domesAbove500Above500;
	}

	public int getCommercialMinUnits() {
		return commercialMinUnits;
	}

	public double getCommercialChargesBelowMin() {
		return commercialChargesBelowMin;
	}

	public double getCommercialChargesAboveMin() {
		return commercialChargesAboveMin;
	}

	public int getWorkshopMinUnits() {
		return workshopMinUnits;
	}

	public double getWorkshopChargesBelowMin() {
		return workshopChargesBelowMin;
	}

	public double getWorkshopChargesAboveMin() {
		return workshopChargesAboveMin;
	}

	public int getCottageMinUnits() {
		return cottageMinUnits;
	}

	public double getCottageChargesBelowMin() {
		return cottageChargesBelowMin;
	}

	public double getCottageChargesAboveMin() {
		return cottageChargesAboveMin;
	}

	public int getLoomsMinUnits() {
		return loomsMinUnits;
	}

	public double getLoomsChargesBelowMin() {
		return loomsChargesBelowMin;
	}

	public double getLoomsChargesAboveMin() {
		return loomsChargesAboveMin;
	}

	public double getIndustrialMetroCharges() {
		return industrialMetroCharges;
	}

	public double getTempSupplyCharges() {
		return tempSupplyCharges;
	}

	public double getLightTowncharges() {
		return lightTowncharges;
	}

	public double getGovnPlacesCharges() {
		return govnPlacesCharges;
	}

	public double getPrivateHospitalCharges() {
		return privateHospitalCharges;
	}
}
