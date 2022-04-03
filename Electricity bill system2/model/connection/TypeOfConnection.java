package connection;


public enum TypeOfConnection {
	Domestic("Domestic"),
	LtCommercial("Lt Commercial"),
	PublicWorkshop("Public workshop"),
	CottageAndTinyIndustries("Cottage industries"),
	PowerLooms ("Power looms"),
	PublicLightsVillageAndIndustrialmetro("public village lights"),//same
	TemporarySupply("Temporary Supply"),//same
	PublicLightTown("Public Light Town"),//same
	GovnAidedPlaces("Govn Aided Places"),//same
	PrivateHostpitalInstitution("Private Hostpital Institution");//same
	
	 private String value;

	    public String getResponse() {
	        return value;
	    }

	    private TypeOfConnection(String value) {
	    	this.value = value;
		}
	        
}
