//Sorting the array and finding kth largest and kth smallest element
//int a[] = {26,1,4,23,9,67};//1,4,9,23,26,67
package logical_programming;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class FindKthLargestAndSmallestElement {
	static Scanner sc = new Scanner(System.in);
	public static void main(String args[]) {
		int a[] = getInputFromUser();
		System.out.println("Enter k value ");
		int k = sc.nextInt();
	
		int result[] = new int[2];
		
		result = findLargeAndSmallElement(a,k);
		System.out.println(k+"th largest element is "+result[1]+"\n"
				+ k+"th smallest element is "+result[0]);
	}
	//Get input from user
	private static int[] getInputFromUser() {
		ArrayList<Integer> list = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the numbers");
		String str = sc.nextLine();
		int sum = 0;
		int pro = 1;
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) == ' ' ) {
				list.add(pro * sum);
				sum = 0;
				pro = 1;
			}
			else if(str.charAt(i) == '-') {
				pro = -1;
			}
			else {
				int val = str.charAt(i)-48;
				sum =  (sum * 10) + val;
			}
		}
		list.add(sum*pro);
		return list.stream().mapToInt(i->i).toArray();
	}
	
	private static int[] findLargeAndSmallElement(int[] a, int k) {
		TreeSet<Integer> set = new TreeSet<>();
		for(int i=0; i<a.length; i++) {
			set.add(a[i]);
		}
		int newArray[] = set.stream().mapToInt(I->I).toArray();
		
		if(k > newArray.length) {
			System.out.println("Enter valid number exitting");
			System.exit(0);//0,2,4,4,8
		}
		return new int[] {newArray[k-1],newArray[newArray.length-k]};
	}
}
