package shelf;

import java.util.*;

import product.IProduct;

public class Shelf {
//	IProduct product;
	List<IProduct> products = new ArrayList<>();
	
	public void addProduct(IProduct product) {
		products.add(product);
		System.out.println("Product has been added successfully \n"
				+ product.getClass().getSimpleName()+" Name :-"+product.toString());
	}
	
	public void removeProduct(int productPlace) {
		System.out.println("Product has been removed successfully\n"
				+ ""+products.get(productPlace));
		products.remove(productPlace);
		
	}
	
	public void displayProduts(Shelf shelf) {
		int i = 1;
		for(IProduct product: shelf.products)
			System.out.println((i++)+"."+product.toString());
	}
	public void customizeShelf() {
		System.out.println("Shelf has been customized ");
	}
}
