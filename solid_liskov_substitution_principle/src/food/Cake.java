package food;

import java.util.*;

public class Cake extends PasteryFood{
	public Cake() {
		ingredians.add("Egg");
		ingredians.add("Milk");
		ingredians.add("Flour");
		ingredians.add("Cream");
	}
	@Override
	public void gatherIngrediens() {
		System.out.println("These ingredians gathered are below");
		for(String s:ingredians) {
			System.out.print(s+" ");
		}
		
	}

	@Override
	public void startCook() {
		System.out.println("Keep the cake in the oven");
		System.out.println("After bake add apply cream and toppings");
	}
	
}
