package connection;

import connection_obj.ConnectionObj;
import connection_obj.*;

public enum TypeOfConnection {
	Domestic{
		Domestic obj = new Domestic();

		
		@Override
		public Domestic getObj() {
			
			return obj;
		}
	},
	LtCommercial{
		LtCommercial obj = new LtCommercial();
		public double minUnits = 100;
		public double chargesBelowMin = 5;
		public double chargesAboveMin = 8.05;
		
		
		public String getTarrif() {
			String val = "less than "+minUnits+" "+chargesBelowMin+"\n"
					+ "greater than "+minUnits+" "+chargesAboveMin;
			return val;
		}


		@Override
		public LtCommercial getObj() {
			// TODO Auto-generated method stub
			return obj;
		}
	},
	PublicWorkshop{
		PublicWorkshop obj = new PublicWorkshop();
		public double minUnits = 120;
		public double chargesBelowMin = 2.85;
		public double chargesAboveMin = 5.75;
		
		public String getTarrif() {
			String val = "less than "+minUnits+" "+chargesBelowMin+"\n"
					+ "greater than "+minUnits+" "+chargesAboveMin;
			return val;
		}

		@Override
		public PublicWorkshop getObj() {
			// TODO Auto-generated method stub
			return obj;
		}
	},
	CottageAndTinyIndustries{
		CottageAndTinyIndustries obj = new CottageAndTinyIndustries();
		public double minUnits = 500;
		public double chargesBelowMin = 4;
		public double chargesAboveMin = 4.6;
		
		public String getTarrif() {
			String val = "less than "+minUnits+" units"+chargesBelowMin+"\n"
					+ "greater than "+minUnits+" units"+chargesAboveMin;
			return val;
		}

		@Override
		public CottageAndTinyIndustries getObj() {
			// TODO Auto-generated method stub
			return obj;
		}
	},
	PowerLooms {
		PowerLooms obj = new PowerLooms();

		@Override
		public PowerLooms getObj() {
			// TODO Auto-generated method stub
			return obj;
		}
	},
	PublicLightsVillageAndIndustrialmetro{
		PublicLightsVillageAndIndustrialmetro obj = new PublicLightsVillageAndIndustrialmetro();
		public double charges = 6.35;
		public String getTarrif() {
			String val = "All units  "+charges;
			return val;
		}
		@Override
		public PublicLightsVillageAndIndustrialmetro getObj() {
			// TODO Auto-generated method stub
			return obj;
		}
	},//same
	TemporarySupply{
		TemporarySupply obj = new TemporarySupply();
		public double charges = 12;
		public String getTarrif() {
			String val = "All units  "+charges;
			return val;
		}
		@Override
		public TemporarySupply getObj() {
			// TODO Auto-generated method stub
			return obj;
		}
	},//same
	PublicLightTown{
		PublicLightTown obj = new PublicLightTown();
		public double charges = 6.35;
		public String getTarrif() {
			String val = "All units  "+charges;
			return val;
		}
		@Override
		public PublicLightTown getObj() {
			// TODO Auto-generated method stub
			return obj;
		}
	},//same
	GovnAidedPlaces{
		GovnAidedPlaces obj = new GovnAidedPlaces();
		public double charges = 5.75;
//		public ConnectionObj obj = new LtCommercial();
		public String getTarrif() {
			String val = "All units  "+charges;
			return val;
		}
		@Override
		public GovnAidedPlaces getObj() {
			// TODO Auto-generated method stub
			return obj;
		}
	},//same
	PrivateHostpitalInstitution{
		PrivateHostpitalInstitution obj = new PrivateHostpitalInstitution();
		public double charges = 7.5;
		public String getTarrif() {
			String val = "All units  "+charges;
			return val;
		}
		@Override
		public PrivateHostpitalInstitution getObj() {
			
			return obj;
		}
	};//same
	
	public abstract ConnectionObj getObj();
}
