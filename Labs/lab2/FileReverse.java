/*	Nicholas Pappas
	nhpappas@ucsc.edu
	ID: 1558554
	Assignment: lab2
*/
// Lex.java
// Illustrates file IO and List implementation
import java.io.*;
import java.util.Scanner;
class Lex{

 public static void main(String[] args) throws IOException{ 
   Scanner in = null;
   Scanner in2 = null;
   PrintWriter out = null;
   String line = null;
   int i, n, lineNumber = 0;
 // check number of command line arguments is at least 2
   if(args.length < 2){
      System.out.println("Usage: FileCopy <input file> <output file>");
      System.exit(1);
    }
 // open files
   in = new Scanner(new File(args[0]));
   in2 = new Scanner(new File(args[0]));
   out = new PrintWriter(new FileWriter(args[1]));
 // read lines from in
   while( in.hasNextLine() ){ 
      lineNumber++;				// Count num lines in file so we can
   }							// allocate an array of that size.
   String[] words = new String[lineNumber]; // <<<<< This one.
   List A = new List();
   lineNumber = 0;				// Reuse lineNumber as an index for line
   while( in2.hasNextLine() ){  // trim leading and trailing spaces, then add one
      line = in2.nextLine().trim() + " "; // trailing space so split works on blank lines
	  words[lineNumber] = line;		//load lines from file into the array we allocated	   
	  lineNumber++;
   }
   A.prepend(0);				//Put an initial element in the list to compareTo
   for(j=1; j < lineNumber; j++){ //Step each element through the list to find its place
	  n = 0; A.moveBack();
	  while(n == 0){
	     if(word[j].compareTo(word[A.index()]) < 0){	//j comes before and isn't one of these cases
            if(A.length() == 1 || A.index() == 0){ A.insertBefore(j); n = -1;
			}else{ A.movePrev(); //move the cursor. Check again incase we can insert.
			   if(word[j].compareTo(word[A.index()]) >= 0){ A.insertAfter(j); n = -1; }
			}
	     }else(word[j].compareTo(word[A.index()]) > 0){	//j comes after and isn't one of these cases
		    if(A.length() == 1 || A.index() == A.length()-1){ A.insertAfter(j); n = -1; 
		    }else{ A.moveNext(); //move the cursor. Check again incase we can insert.
			   if(word[j].compareTo(word[A.index()]) <= 0){ A.insertBefore(j); n = -1; }
			}
		 } //The while loop will run this logic untill the element is inserted.
	  } 
   }
   
 // close files
   in.close();
   in2.close();
   out.close();
   }
}