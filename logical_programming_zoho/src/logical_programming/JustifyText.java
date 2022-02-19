/*
 * [
"This       is      an",
"example of text",
"justification.      "
]
 * */
package logical_programming;

import java.util.ArrayList;

public class JustifyText {
	 public static void main(String args[]) {
		 String words[] = {"This", "is", "an", "example", "of", "text", "justification."};
		 int L = 16;
//		 String words [] = {"Hi","I","am","developer"};
		 int left = 0;
		 ArrayList<String> list = new ArrayList<>();
		 
		 while(left < words.length) {
			 int right = findRight(left,words,L);
			 String str = textJustification(left,right,words,L);
			 list.add(str);
			 left = right + 1;
		 }
		 for(String s:list)
			 System.out.println(s);
		 
	 }
	 
	//Find how many words can be inserted in a row
	private static int findRight(int left, String[] words, int L) {
		int right = left;
		int sum = words[right++].length();
		
		while(
				right < words.length 
				&& 
				sum + 1 + words[right].length() <= L
				) {
			sum += 1 + words[right++].length();
			
		}
		return right-1;
	}
	private static String textJustification(int left, int right, String[] words, int L) {
		//if only one word in row
		if(right - left == 0)
			return padResultWithSpace(words[left],L);
		
		boolean isLastLine = (right == words.length-1)?true:false;
		//no of spaces by default for eg if 3 words present then numsSpace = 2
		int numSpaces = right - left;
		//total number of spaces to be filled
		int totalSpace = L - wordsLength(left,right,words);
		
		//evenly distributed space
		String space = isLastLine ? " ":addSpace(totalSpace/numSpaces);
		
		int remainder = isLastLine?0:(totalSpace % numSpaces);
		
		StringBuilder result = new StringBuilder();
		for(int i=left; i<=right; i++) {
			result.append(words[i])
				  .append(space)
				  .append(remainder-- > 0 ? " " : "");
		}
		return padResultWithSpace(result.toString().trim(), L);
	}

	private static int wordsLength(int left, int right, String[] words) {
		int length = 0;
		for(int i=left; i<=right ; i++)
			length += words[i].length();
		return length;
	}

	private static String padResultWithSpace(String str, int L) {
		String result = addSpace(L - str.length());
		return str+result;
	}
	//It returns space string
	private static String addSpace(int len) {
		String str = new String(
				new char[len]).replace('\0', ' ');
		return str;
	}
		 
}
