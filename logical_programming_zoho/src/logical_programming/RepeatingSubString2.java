package logical_programming;

public class RepeatingSubString2 {
	public static void main(String args[]) {
		int a[] = {1,0,0,1,0,1,1,1};
//		int a[] = {1,0};
//		int a[] = {1,1,1,0,0,0,1};
//		int a[] = {1,1,1,1,1,1,0};
//		int a[] = {0,1,1,1,1,1,1,0};
//		int a[] = {0,0,0,0,0,0,0};
		int start = 0,end = 0,maxLength = 0;
		int zeros = 0,ones = 0;
		
		for(int i=0; i<a.length; i++) {
			zeros = 0;
			ones=0;
			int dummy = (a[i] == 0)?zeros++:ones++;
			for(int j=i+1; j<a.length;j++) {
				dummy = (a[j] == 0)?zeros++:ones++;
				int len = j-i+1;
				if(ones == zeros && len > maxLength) {
					start = i;
					end = j;
					maxLength = len;
				}
			}
		}
		if(maxLength > 1)
			System.out.println(start+" to "+end);
		else
			System.out.println("No sub Arrays found");
	}
}
