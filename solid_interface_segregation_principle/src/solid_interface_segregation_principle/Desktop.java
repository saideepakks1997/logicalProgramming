package solid_interface_segregation_principle;

public class Desktop implements NonTouchComponent{

	@Override
	public void validate() {
		System.out.println("Validate is common for both components");
	}

	@Override
	public void onMouseHover() {
		System.out.println("On mousehover the screen enlarged");
	}

}
