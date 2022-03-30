package connection;

import connection_obj.*;

public enum TypeOfConnection {
	Domestic("Domestic"){
		int freeUnits = 100;
		DomesticConnection obj = new DomesticConnection(freeUnits);
		
		@Override
		public DomesticConnection getObj() {
			return obj;
		}
	},
	LtCommercial("Lt Commercial"){
		public double minUnits = 100;
		public double chargesBelowMin = 5;
		public double chargesAboveMin = 8.05;
		
		SplitChargesConnection conObj = new SplitChargesConnection(minUnits, chargesBelowMin, chargesAboveMin);


		@Override
		public SplitChargesConnection getObj() {
			return conObj;
		}

	},
	PublicWorkshop("Public workshop"){
		public double minUnits = 120;
		public double chargesBelowMin = 2.85;
		public double chargesAboveMin = 5.75;
		
		SplitChargesConnection conObj = new SplitChargesConnection(minUnits, chargesBelowMin, chargesAboveMin);
		
		
		@Override
		public SplitChargesConnection getObj() {
			return conObj;
		}

	},
	CottageAndTinyIndustries("Cottage industries"){
		public double minUnits = 500;
		public double chargesBelowMin = 4;
		public double chargesAboveMin = 4.6;
		
		SplitChargesConnection conObj = new SplitChargesConnection(minUnits, chargesBelowMin, chargesAboveMin);

		@Override
		public SplitChargesConnection getObj() {
			return conObj;
		}

	},
	PowerLooms ("Power looms"){
		public int minUnits = 1000;
		public double chargesBelowMin = 2.3;
		public double chargesAboveMin = 4.6;
		
		SplitChargesConnection conObj = new SplitChargesConnection(minUnits, chargesBelowMin, chargesAboveMin);

		@Override
		public SplitChargesConnection getObj() {
			return conObj;
		}

	},
	PublicLightsVillageAndIndustrialmetro("public village lights"){
		public double charges = 6.35;
		
		SameChargesConnection conObj = new SameChargesConnection(charges);
		
		@Override
		public SameChargesConnection getObj() {
			return conObj;
		}

	},//same
	TemporarySupply("Temporary Supply"){
		public double charges = 12;
		
		SameChargesConnection conObj = new SameChargesConnection(charges);

		@Override
		public SameChargesConnection getObj() {
			return conObj;
		}

	},//same
	PublicLightTown("Public Light Town"){
		public double charges = 6.35;
		
		SameChargesConnection conObj = new SameChargesConnection(charges);

		@Override
		public SameChargesConnection getObj() {
			return conObj;
		}
	},//same
	GovnAidedPlaces("Govn Aided Places"){
		public double charges = 5.75;

		SameChargesConnection conObj = new SameChargesConnection(charges);

		@Override
		public SameChargesConnection getObj() {
			return conObj;
		}
	},//same
	PrivateHostpitalInstitution("Private Hostpital Institution"){
		public double charges = 7.5;
		
		SameChargesConnection conObj = new SameChargesConnection(charges);

		@Override
		public SameChargesConnection getObj() {
			return conObj;
		}

	};//same
	
	 private String value;

	    public String getResponse() {
	        return value;
	    }

	    private TypeOfConnection(String value) {
	    	this.value = value;
		}
	        
	    
	
	public abstract ConnectionObj getObj();
}
