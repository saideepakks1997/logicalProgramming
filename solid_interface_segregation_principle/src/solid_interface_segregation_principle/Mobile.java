package solid_interface_segregation_principle;

public class Mobile implements TouchComponent{

	@Override
	public void validate() {
		System.out.println("Validate is common for both components");
	}

	@Override
	public void touch() {
		System.out.println("On touching the component it enters inside");
		
	}

	@Override
	public void swipe() {
		System.out.println("on swiping goes to next page");
		
	}

}
