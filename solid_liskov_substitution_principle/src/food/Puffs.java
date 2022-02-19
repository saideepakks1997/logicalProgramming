package food;

public class Puffs extends PasteryFood{
	public Puffs() {
		ingredians.add("Batter");
		ingredians.add("Mixed vegetable masala");
		ingredians.add("Oil");
		ingredians.add("Salt");
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
		System.out.println("Stuff the masala in the batter and fold it");
		System.out.println("keep in the oven wait for 30 mins");
	}

}
