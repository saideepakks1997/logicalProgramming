//Sorting the array and finding kth largest and kth smallest element
//int a[] = {26,1,4,23,9,67};//1,4,9,23,26,67
package logical_programming;

import java.util.ArrayList;
import java.util.Scanner;

public class FindKthLargestAndSmallestElement {
	static Scanner sc = new Scanner(System.in);
	public static void main(String args[]) {
		int a[] = getInputFromUser();
		
		System.out.println("Enter k value ");
		int k = sc.nextInt();
//		
		int result[] = new int[2];
		//Merge sort
		mergeSort(a,0,a.length-1);
		
//		for(int i=0; i<a.length; i++)
//			System.out.print(a[i]+" ");
		
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
		System.out.println(sum);
		list.add(sum*pro);
		return list.stream().mapToInt(i->i).toArray();
	}
	
	private static int[] findLargeAndSmallElement(int[] a, int k) {
		int n = a.length;
		if(k > n) {
			System.out.println("Enter valid number exitting");
			System.exit(0);
		}
		return new int[] {a[k-1],a[n-k]};
	}

	private static void mergeSort(int[] a,int left,int right) {
		        if (left < right) {
		        	int mid =left+ (right-left)/2;
		        	mergeSort(a, left, mid); 
		            mergeSort(a, mid + 1, right);
		            merge(a, left, mid, right);
		        }
		}
	
	private static void merge(int[] a, int left, int mid, int right) {
		int leftLen = mid - left + 1;
		int rightLen = right - mid;
		
		int[] leftArr = new int[leftLen];
		int[] rightArr = new int[rightLen];
		
		for(int i=0; i<leftLen; i++)
			leftArr[i] = a[left + i];
		for(int i=0; i<rightLen; i++)
			rightArr[i] = a[mid + 1 + i];
		
		int i = 0, j = 0;
		  
        int k = left;
        while (i < leftLen && j < rightLen) {
            if (leftArr[i] <= rightArr[j]) {
                a[k] = leftArr[i];
                i++;
            }
            else {
                a[k] = rightArr[j];
                j++;
            }
            k++;
        }
  
        while (i < leftLen) {
            a[k] = leftArr[i];
            i++;
            k++;
        }
        
        while (j < rightLen) {
            a[k] = rightArr[j];
            j++;
            k++;
        }
	}
}
