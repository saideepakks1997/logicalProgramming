package connection;

public enum TypeOfConnection {
//	domestic,
//	ltCommercial,
//	publicWorkshop,
//	cottageAndTinyInstries,
//	privateHospitals,
//	govermentHospitalInstitutions,
//	publicLightTown,
//	temporarySupply,
//	powerLooms,
	Domestic("domestic",2),
	ltCommercial("ltCommercial",5);
	
	private final String key;
    private final Integer value;

    TypeOfConnection(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    public Integer getValue() {
        return value;
    }
	
}
