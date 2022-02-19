package food;

import java.util.ArrayList;
import java.util.List;

public abstract class PasteryFood {
	List<String> ingredians = new ArrayList<>();
	
	public void swtichOnOven() {
		System.out.println("Preheat the oven");
	}
	public abstract void gatherIngrediens();
	public abstract void startCook();
}
