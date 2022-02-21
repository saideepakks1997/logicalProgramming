/*
 	i/p :-  2, 5, 7
 	o/p :- 	5, 7, -1
 	
 	Input: 2, 4, 8, 90, 77, 54
	Output: 4, 8, 54, -1, -1, -1
	 
	
 * */
package logical_programming;
import java.util.*;
public class FindNextGreaterElement {

	public static void main(String[] args) {
//		int a[] = {2,5,7};
//		int a[] = {2, 4, 8, 90, 77, 54};
//		int a[] = {-1,2,6,1,10,23,4,5};//1 4 10 4 23 -1 5 -1
		int a[] = {0,2,5,8,-2};//{2,5,8,-1,-1}//{-2,0,2,5,8}
//		int a[] = {2,8,4,-3,0,-2};//{4,-1,-1,-1,-1,-1}
//		int a[] = {5,7,-1,3};
		int n = a.length;
		int result[] = new int[n];
		TreeSet<Integer> set = new TreeSet<>();
		
		for(int i= n-1; i>=0; i--) {
			Integer val = set.higher(a[i]);//{-2,}
			if(!set.isEmpty() && val != null)
				result[i] = val;
			else
				result[i] = -1;
			set.add(a[i]);
		}
		for(int i=0; i<n; i++)
			System.out.print(result[i]+" ");
	}
}
