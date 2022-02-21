package product;

public class Dvd implements IProduct{
	String dvdName = "Why OOPS?";
	String authName = "Alan Kay";
	double review = 4.9;
	@Override
	public void getProduct() {
		System.out.println(dvdName+" got the DVD authour is "+authName);
	}

	@Override
	public void getSample() {
		System.out.println("Looking at the context of dvd named "+dvdName);
	}

	@Override
	public void seeReviews() {
		System.out.println("The review for book "+dvdName+" is "+review);
		
	}
	@Override
	public String toString() {
		return this.dvdName;
	}
}
