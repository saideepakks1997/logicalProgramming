package main;

import product.Book;
import product.Dvd;
import shelf.Shelf;

public class Main {
	public static void main(String args[]) {
		Dvd dvd = new Dvd();
		Book book = new Book();
		
		Shelf shelf = new Shelf();
		System.out.println("Adding products");
		System.out.println("---------------------------------------");
		shelf.addDvd(dvd);
		shelf.addBook(book);
		System.out.println("---------------------------------------");
		
		System.out.println("Customize shelf");
		System.out.println("---------------------------------------");
		shelf.customizeShelf();
		System.out.println("---------------------------------------");
		
		System.out.println("Display Products");
		System.out.println("---------------------------------------");
		shelf.displayBooks(shelf);
		shelf.displayDvds(shelf);
		System.out.println("---------------------------------------");
		
		System.out.println("Remove Products");
		System.out.println("---------------------------------------");
		shelf.removeBook(0);
		shelf.removeDvd(0);
		System.out.println("---------------------------------------");
	}
}
