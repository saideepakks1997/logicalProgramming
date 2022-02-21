package main;

import product.Book;
import product.Dvd;
import product.IProduct;
import shelf.Shelf;

public class Main {
	public static void main(String args[]) {
		IProduct dvd = new Dvd();
		IProduct book = new Book();
		
		Shelf shelf = new Shelf();
		System.out.println("Adding products");
		System.out.println("---------------------------------------");
		shelf.addProduct(dvd);
		shelf.addProduct(book);
		System.out.println("---------------------------------------");
		
		System.out.println("Customize shelf");
		System.out.println("---------------------------------------");
		shelf.customizeShelf();
		System.out.println("---------------------------------------");
		
		System.out.println("Display Products");
		System.out.println("---------------------------------------");
		shelf.displayProduts(shelf);
		System.out.println("---------------------------------------");
		
		System.out.println("Remove Products");
		System.out.println("---------------------------------------");
		shelf.removeProduct(0);
		shelf.removeProduct(0);
		System.out.println("---------------------------------------");
	}
}
