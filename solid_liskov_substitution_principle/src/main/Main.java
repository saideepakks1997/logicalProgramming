package main;

import java.util.Scanner;

import food.Cake;
import food.PasteryFood;
import food.Puffs;

public class Main {
	static Scanner sc = new Scanner(System.in);
	public static void main(String args[]) {
		System.out.println("select the food u want to cook\n"
				+ "1->Puffs\n"
				+ "2->Cake\n");
		int opt = sc.nextInt();
		PasteryFood food = null;
		switch (opt) {
		case 1: food = new Puffs();
			cookFood(food);
			break;
		case 2:	food = new Cake();
			cookFood(food);
			break;
		default:
			break;
		}
	}
	private static void cookFood(PasteryFood food) {
		food.swtichOnOven();
		food.gatherIngrediens();
		food.startCook();
	}
}
