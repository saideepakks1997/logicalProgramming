package connection;

import connection_obj.*;

public enum TypeOfConnection {
	Domestic{
		int freeUnits = 100;
		DomesticConnection obj = new DomesticConnection(freeUnits);
		
		
		@Override
		public DomesticConnection getObj() {
			return obj;
		}
	},
	LtCommercial{
		public double minUnits = 100;
		public double chargesBelowMin = 5;
		public double chargesAboveMin = 8.05;
		
		SplitChargesConnection conObj = new SplitChargesConnection(minUnits, chargesBelowMin, chargesAboveMin);


		@Override
		public SplitChargesConnection getObj() {
			return conObj;
		}
	},
	PublicWorkshop{
		public double minUnits = 120;
		public double chargesBelowMin = 2.85;
		public double chargesAboveMin = 5.75;
		
		SplitChargesConnection conObj = new SplitChargesConnection(minUnits, chargesBelowMin, chargesAboveMin);
		
		
		@Override
		public SplitChargesConnection getObj() {
			return conObj;
		}
	},
	CottageAndTinyIndustries{
		public double minUnits = 500;
		public double chargesBelowMin = 4;
		public double chargesAboveMin = 4.6;
		
		SplitChargesConnection conObj = new SplitChargesConnection(minUnits, chargesBelowMin, chargesAboveMin);

		@Override
		public SplitChargesConnection getObj() {
			return conObj;
		}
	},
	PowerLooms {
		public int minUnits = 1000;
		public double chargesBelowMin = 2.3;
		public double chargesAboveMin = 4.6;
		
		SplitChargesConnection conObj = new SplitChargesConnection(minUnits, chargesBelowMin, chargesAboveMin);

		@Override
		public SplitChargesConnection getObj() {
			return conObj;
		}
	},
	PublicLightsVillageAndIndustrialmetro{
		public double charges = 6.35;
		
		SameChargesConnection conObj = new SameChargesConnection(charges);
		
		@Override
		public SameChargesConnection getObj() {
			return conObj;
		}
	},//same
	TemporarySupply{
		public double charges = 12;
		
		SameChargesConnection conObj = new SameChargesConnection(charges);

		@Override
		public SameChargesConnection getObj() {
			return conObj;
		}
	},//same
	PublicLightTown{
		public double charges = 6.35;
		
		SameChargesConnection conObj = new SameChargesConnection(charges);

		@Override
		public SameChargesConnection getObj() {
			return conObj;
		}
	},//same
	GovnAidedPlaces{
		public double charges = 5.75;

		SameChargesConnection conObj = new SameChargesConnection(charges);

		@Override
		public SameChargesConnection getObj() {
			return conObj;
		}
	},//same
	PrivateHostpitalInstitution{
		public double charges = 7.5;
		
		SameChargesConnection conObj = new SameChargesConnection(charges);

		@Override
		public SameChargesConnection getObj() {
			return conObj;
		}
	};//same
	
	public abstract ConnectionObj getObj();
}
