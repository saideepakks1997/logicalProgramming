package logical_programming;

import java.util.*;
public class LargestSubArray {
	public static void main(String args[]) {
		int start = Integer.MAX_VALUE;
		int end = Integer.MAX_VALUE;
//		int a[] = {1,0,1,1,1,0,0,0};
//		int a[] = {1,0,1,1,1,0,0};
//		int a[] = {1,1,1,1,1};
//		int a[] = {0,0,1,0,1,0,1};
		int a[] = {1,0};
//		int a[] = {1,0,1,1,1,0,0,0};
		HashMap<Integer,Integer> map = new HashMap<>();//[{0,-1},[1,0],[]
		map.put(0, -1);
		int max_length = 0;
		int currSum = 0;
		for(int i=0; i<a.length; i++) {
			if(a[i] == 0) 
				currSum -= 1;//0
			
			else 
				currSum += 1;
			
			if(map.containsKey(currSum)) {
				//i=1,-1
				int len = i-map.get(currSum);//0
				if(len >= max_length) {
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
