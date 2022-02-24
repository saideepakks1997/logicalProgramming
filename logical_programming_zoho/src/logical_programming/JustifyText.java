/*
 * [
"This       is      an",
"example of text",
"justification.      "
]
 * */
package logical_programming;

import java.util.ArrayList;
import java.util.Arrays;

public class JustifyText {
	
	 public static void main(String args[]) {
//		 String words[] = {"This", "is", "an", "example", "of", "text", "justification."};
		 String words[] = {"a","b","c","d","test","wonderfully","exceptionally","e","f"};
		 ArrayList<String> listWords = new ArrayList<>(Arrays.asList(words));
		 int L = 12;
//		 String words [] = {"Hi","I","am","developer"};
		 int left = 0;
		 ArrayList<String> list = new ArrayList<>();
		 
		 while(left < listWords.size()) {
			 int right = findRight(left,listWords,L);
			 String str = "";
				 str = textJustification(left,right,listWords,L);
			 list.add(str);
			 left = right + 1;
		 }
		 for(String s:list)
			 System.out.println(s);
		 
	 }
	 
	//Find how many words can be inserted in a row
	private static int findRight(int left, ArrayList<String> listWords, int L) {
		int right = left;
		int sum = listWords.get(right++).length();
		
		while(
				right < listWords.size()
				&& 
				sum + 1 + listWords.get(right).length() <= L
				) {
			sum += 1 + listWords.get(right++).length();
			
		}
		return right-1;
	}
	private static String textJustification(int left, int right, ArrayList<String> listWords, int L) {
		//if only one word in row
		if(right - left == 0) {
			
			try{
				return padResultWithSpace(listWords.get(left),L);
			}
			catch (Exception e) {
				System.out.println("Got exception for text "+listWords.get(left)+" and handled\n");
				String str = padResultWithSpace(
						listWords.get(left)
						.substring(0,L), L);
				listWords.add(left+1, "-"+listWords.get(left).substring(L));
				return str;
			}
		}
			
		boolean isLastLine = (right == listWords.size()-1)?true:false;
		//no of spaces by default for eg if 3 words present then numsSpace = 2
		int numSpaces = right - left;//2
		//total number of spaces to be filled
		int totalSpace = L - wordsLength(left,right,listWords);//8
		
		//evenly distributed space
		String space = isLastLine ? " ":addSpace(totalSpace/numSpaces);
		
		int remainder = isLastLine?0:(totalSpace % numSpaces);
		
		StringBuilder result = new StringBuilder();
		for(int i=left; i<=right; i++) {
			result.append(listWords.get(i))
				  .append(space)
				  .append(remainder-- > 0 ? " " : "");
		}
		return padResultWithSpace(result.toString().trim(), L);
	}

	private static int wordsLength(int left, int right, ArrayList<String> listWords) {
		int length = 0;
		for(int i=left; i<=right ; i++)
			length += listWords.get(i).length();
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
