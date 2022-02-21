package logical_programming;
import java.util.*;
public class TrapSequence {
	static int y ;
	public static void main(String args[]) {
		//input from user
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter n1 value");
		int n1 = sc.nextInt();
		System.out.println("Enter n2 value");
		int n2 = sc.nextInt();
		System.out.println("Enter y value");
		y = sc.nextInt();
		
		ArrayList<Integer> list = new ArrayList<>();
		if(n1 <= n2) {
			for(int i=n1; i<=n2;i++) {
				int val = findTrap(i,i);
				if(val != -1)
					list.add(val);
			}
		}
		else {
			System.out.println("yo man");
			for(int i=n2; i<=n1;i++) {
				int val = findTrap(i,i);
				if(val != -1)
					list.add(val);
			}
		}
		
		System.out.println(list);
	}

	private static int findTrap(int i,int x) {
		int small = Math.min(i, y);
		int large = Math.max(i, y);
		if(i==y) {
			y += 2;
			return x;
		}
		else if(large % small == 0) {
			y += 2;
			return x;
		}
		else if(i<10) {
			y -= 1;
			if(y<3)
				y=3;
			return -1;
		}
		return findTrap(findSum(i),x);
	}
	//finding sum
	private static int findSum(int val) {
		int sum = 0;
		while(val != 0) {
			sum += val % 10;
			val /= 10;
		}
		return sum;
		
	}
}
