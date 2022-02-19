package logical_programming;

 
import java.util.*;

class ReapeatingSubString {
	 public static void main(String[] args) {
		 String str = "However Everyday is a day";
//		 String str = "my name my n";
//		 String str = "i love my country india";
//		 String str = "china china";
//		 String str = "tahil istahiss";
//		 String str = "hello google whats ur statsa googleeee";
//		 String str = "my thailand name is thiliname land";
//		 String str = "i am from inindia";
		 

		 str = str.toLowerCase();
		 solve(str);
   }
	
    public static void solve(String str){
    	int length = str.length();
       if(length <= 3){
           return ;
       }
       HashSet<String> hset = new HashSet<>();
       int ptr  = 0;//ptr
       while(ptr<length-2){
           if(str.charAt(ptr)!= ' ' && str.charAt(ptr+1) != ' '){ //to eliminate space
               int x = ptr+2;
               //inner loop for checking
               while(x<length && str.charAt(ptr)!= ' ' && str.charAt(ptr+1) != ' '){
                  int wordLen =2;
                  String tmp = "";
                  while(x+wordLen-1 < str.length()){
                	  if(
            			str.charAt(ptr+wordLen-1) == ' '
            			||
            			str.charAt(x+wordLen-1) == ' '
            			  ) 
                	  {
                		  break;
                	  }
                	  String str1 = str.substring(ptr,ptr+wordLen);
                	  String str2 = str.substring(x,x+wordLen);
                      if(str1.equals(str2)) {
                    	  tmp = str.substring(ptr,ptr+wordLen);
                    	  wordLen++;
                      }
                      else
                    	  break;      
                  }
                  if(tmp.length()>1){
                	  ptr = ptr+wordLen-1;
                      hset.add(tmp);
                  }
                   x++;
               }
           }
           ptr++;
       }
       if(!hset.isEmpty()) {
    	  System.out.println(hset);  
       }
       else
    	   System.out.println("none");
    }
   
}
