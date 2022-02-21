package product;

public class Book {
	String bookName = "Solid principle-A Clean Code";
	String authName = "Uncle Bob";
	double review = 4.6;

	public void getProduct() {
		System.out.println(bookName+" got the book which is written by "+authName);
	}

	public void getSample() {
		System.out.println("Looking at the context of book named "+bookName);
		
	}

	
	public void seeReviews() {
		System.out.println("The review for book "+bookName+" is "+review);
		
	}
	@Override
	public String toString() {
		return this.bookName;
		}

}
