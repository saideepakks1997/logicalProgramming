package shelf;

import java.util.ArrayList;
import java.util.List;

import product.*;

public class Shelf {
	List<Book> books = new ArrayList<>();
	List<Dvd> dvds = new ArrayList<>();
	//operations for book
	public void addBook(Book book) {
		books.add(book);
		System.out.println("Product has been added successfully \n"
				+ book.getClass().getSimpleName()+" Name :-"+books.toString());
	}
	
	public void removeBook(int productPlace) {
		System.out.println("Product has been removed successfully\n"
				+ ""+books.get(productPlace));
		books.remove(productPlace);
		
	}
	
	public void displayBooks(Shelf shelf) {
		int i = 1;
		for(Book book: shelf.books)
			System.out.println((i++)+"."+book.toString());
	}
	public void customizeShelf() {
		System.out.println("Shelf has been customized ");
	}
	//operations for dvd	
	public void addDvd(Dvd dvd) {
		dvds.add(dvd);
		System.out.println("Product has been added successfully \n"
				+ dvd.getClass().getSimpleName()+" Name :-"+dvds.toString());
	}
	
	public void removeDvd(int productPlace) {
		System.out.println("Product has been removed successfully\n"
				+ ""+dvds.get(productPlace));
		dvds.remove(productPlace);
		
	}
	
	public void displayDvds(Shelf shelf) {
		int i = 1;
		for(Dvd dvd: shelf.dvds)
			System.out.println((i++)+"."+dvd.toString());
	}

}
