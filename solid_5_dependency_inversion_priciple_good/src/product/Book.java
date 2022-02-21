package product;

public class Book implements IProduct{
	String bookName = "Solid principle-A Clean Code";
	String authName = "Uncle Bob";
	double review = 4.6;

	@Override
	public void getProduct() {
		System.out.println(bookName+" got the book which is written by "+authName);
	}

	@Override
	public void getSample() {
		System.out.println("Looking at the context of book named "+bookName);
		
	}

	@Override
	public void seeReviews() {
		System.out.println("The review for book "+bookName+" is "+review);
		
	}
	@Override
	public String toString() {
		return this.bookName;
		}

}
