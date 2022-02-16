package logical_programming;

import java.util.*;
public class LargestSubArray {
	public static void main(String args[]) {
		int start = Integer.MAX_VALUE;
		int end = Integer.MAX_VALUE;
//		int a[] = {1,0,1,1,1,0,0,0};
//		int a[] = {1,0,1,1,1,0,0};
		int a[] = {1,0,1,1,1,0,0,0};
		HashMap<Integer,Integer> map = new HashMap<>();
		map.put(0, -1);
		int max_length = 0;
		int currSum = 0;
		for(int i=0; i<a.length; i++) {
			if(a[i] == 0) 
				currSum -= 1;
			
			else 
				currSum += 1;
			
			if(map.containsKey(currSum)) {
				int len = i-map.get(currSum);
				if(len > max_length) {
					start = map.get(currSum) + 1;
					end = i;
					max_length = len;
				}
			}
			else 
				map.put(currSum, i);
			
		}
		if(start != Integer.MAX_VALUE )
			System.out.println(start +" to "+ end);
		else 
			System.out.println("No sub arrays found");
	}
}
