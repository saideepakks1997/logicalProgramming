package bill_calculation_operations;

import connection.TypeOfConnection;
import connection_obj.ConnectionObj;
import connection_obj.CottageAndTinyIndustries;
import connection_obj.GovnAidedPlaces;
import connection_obj.LtCommercial;
import connection_obj.PowerLooms;
import connection_obj.PrivateHostpitalInstitution;
import connection_obj.PublicLightTown;
import connection_obj.PublicLightsVillageAndIndustrialmetro;
import connection_obj.PublicWorkshop;
import connection_obj.TemporarySupply;

public class DiffrentUnitCalculation implements ICalculateBill{

	@Override
	public double calculteBillAmount(double units, TypeOfConnection type) {
		ConnectionObj obj = type.getObj();
		
		double minUnits = 0;
		double chargesBelowMin = 0;
		double chargesAboveMin = 0;
		
		if(obj instanceof CottageAndTinyIndustries) {
			CottageAndTinyIndustries subObj = (CottageAndTinyIndustries)obj;
			minUnits = subObj.getMinUnits();
			chargesBelowMin = subObj.getChargesBelowMin();
			chargesAboveMin = subObj.getChargesAboveMin();
			
		}
		else if(obj instanceof PowerLooms) {
			PowerLooms subObj = (PowerLooms)obj;
			minUnits = subObj.getMinUnits();
			chargesBelowMin = subObj.getChargesBelowMin();
			chargesAboveMin = subObj.getChargesAboveMin();
		}
		else if(obj instanceof PublicWorkshop) {
			PublicWorkshop subObj = (PublicWorkshop)obj;
			minUnits = subObj.getMinUnits();
			chargesBelowMin = subObj.getChargesBelowMin();
			chargesAboveMin = subObj.getChargesAboveMin();
		}
		else if(obj instanceof LtCommercial) {
			LtCommercial subObj = (LtCommercial)obj;
			minUnits = subObj.getMinUnits();
			chargesBelowMin = subObj.getChargesBelowMin();
			chargesAboveMin = subObj.getChargesAboveMin();
		}
		if(units <= minUnits) {
			return chargesBelowMin * units;
		}
		else {
			return chargesAboveMin * units;
		}
	}
	
}
