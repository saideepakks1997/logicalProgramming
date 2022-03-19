package bill_calculation_operations;

import connection.TypeOfConnection;
import connection_obj.ConnectionObj;
import connection_obj.*;

public class AllUnitsCalculation implements ICalculateBill{

	@Override
	public double calculteBillAmount(double units, TypeOfConnection type) {
		ConnectionObj obj = type.getObj();
		double charges = 0;
		if(obj instanceof PublicLightTown) {
			PublicLightTown subObj = (PublicLightTown)obj;
			charges = subObj.getCharges();
		}
		else if(obj instanceof PrivateHostpitalInstitution) {
			PrivateHostpitalInstitution subObj = (PrivateHostpitalInstitution)obj;
			charges = subObj.getCharges();
		}
		else if(obj instanceof GovnAidedPlaces) {
			GovnAidedPlaces subObj = (GovnAidedPlaces)obj;
			charges = subObj.getCharges();
		}
		else if(obj instanceof TemporarySupply) {
			TemporarySupply subObj = (TemporarySupply)obj;
			charges = subObj.getCharges();
		}
		else if (obj instanceof PublicLightsVillageAndIndustrialmetro) {
			PublicLightsVillageAndIndustrialmetro subObj = (PublicLightsVillageAndIndustrialmetro)obj;
			charges = subObj.getCharges();
		}
		return units*charges;
	}
	
}
