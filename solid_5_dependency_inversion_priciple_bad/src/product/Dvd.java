package product;

public class Dvd {
	String dvdName = "Why OOPS?";
	String authName = "Alan Kay";
	double review = 4.9;
	public void getProduct() {
		System.out.println(dvdName+" got the DVD authour is "+authName);
	}

	public void getSample() {
		System.out.println("Looking at the context of dvd named "+dvdName);
	}

	
	public void seeReviews() {
		System.out.println("The review for book "+dvdName+" is "+review);
		
	}
	@Override
	public String toString() {
		return this.dvdName;
	}
}
