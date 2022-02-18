package logical_programming;

public class BalloonProblem {
	public static void main(String args[]) {
//		char a[] = {'R','G','B','G','B','G','R'};
//		char a[] = {'R','R','B','R','G'};
		 char a[] = {'B', 'G', 'B', 'R', 'G', 'R', 'B', 'G', 'R', 'R', 'B'};
		 
		char appearace[] = new char[3];
		appearace[0] = a[0];
		
		fillAppearanceArray(appearace,a);
		
		int mid = 0,start=0,end=a.length-1;
		while(mid <= end) {
			if(a[mid] == appearace[0]) 
				swap(a,start++,mid++);
			else if(a[mid] == appearace[1]) 
				mid++;
			else
				swap(a,end--,mid);
		}
		for(int k=0; k<a.length; k++)
			System.out.print(a[k]+" ");
	}
	private static void fillAppearanceArray(char[] appearace, char[] a) {
		int curr = 0;
		for(int i=0; i<a.length && curr < 3; i++) {
//			
			if(curr == 0 && appearace[curr] != a[i]) {
				appearace[1] = a[i];
				curr++;
			}
			else if(appearace[0] != a[i] && appearace[1] != a[i]) {
				appearace[2] = a[i];
			}
		}
	}
	private static void swap(char[] a, int i, int pivot) {
		char temp = a[i];
		a[i] = a[pivot];
		a[pivot] = temp;
	}
}
