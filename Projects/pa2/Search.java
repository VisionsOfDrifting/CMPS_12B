/*	Nicholas Pappas
	nhpappas@ucsc.edu
	ID: 1558554
	Assignment: pa2
*/
//-----------------------------------------------------------------------------
// Search.java
// The slick way to search  
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

class Search {

  // mergeSort()
   // sort subarray A[p...r]
   public static void mergeSort(String[] word, int[] lineNumber, int p, int r){
      int q;
      if(p < r) {
         q = (p+r)/2;
         // System.out.println(p+" "+q+" "+r);
         mergeSort(word, lineNumber, p, q);
         mergeSort(word, lineNumber, q+1, r);
         merge(word, lineNumber, p, q, r);
      }
   }

   // merge()
   // merges sorted subarrays A[p..q] and A[q+1..r]
   public static void merge(String[] word, int[] lineNumber, int p, int q, int r){
      int n1 = q-p+1;
      int n2 = r-q;
	  int[] lineNumL = new int[n1];
      int[] lineNumR = new int[n2];
      String[] L = new String[n1];
      String[] R = new String[n2];
      int i, j, k;

      for(i=0; i<n1; i++){
         L[i] = word[p+i];
		 lineNumL[i] = lineNumber[p+i];
      }
      for(j=0; j<n2; j++){ 
         R[j] = word[q+j+1];
		 lineNumR[j] = lineNumber[q+j+1];
      }

      i = 0; j = 0;
      for(k=p; k<=r; k++){
         if( i<n1 && j<n2 ){
            if( L[i].compareTo(R[j])<0){
               word[k] = L[i];
			   lineNumber[k] = lineNumL[i];
               i++;
            }else{
               word[k] = R[j];
			   lineNumber[k] = lineNumR[j];
               j++;
            }
         }else if( i<n1 ){
            word[k] = L[i];
			lineNumber[k] = lineNumL[i];
            i++;
         }else{ // j<n2
            word[k] = R[j];
			lineNumber[k] = lineNumR[j];
            j++;
         }
      }
   }
   
   // binarySearch()
   // pre: Array A[p..r] is sorted
  public static int binarySearch(String[] word, int[] lineNumber, int p, int r,  String target){
      int q;
	  //System.out.println(target);
      if(p > r) {
         return -1;
      }else{
         q = (p+r)/2;
		 //System.out.println(q);
		 //System.out.println(word[q]);
         if(target.compareTo(word[q].trim()) == 0){
            //System.out.println("1");
			return lineNumber[q];}
		 else if(target.compareTo(word[q].trim())<0){
			//System.out.println("2");
            return binarySearch(word, lineNumber, p, q-1, target);}
		 else{ // target > A[q]
			//System.out.println("3");
            return binarySearch(word, lineNumber, q+1, r, target);
         }
      }
    }  

   public static void main(String[] args) throws IOException {

      // check number of command line arguments
      if(args.length <= 1){
         System.err.println("Usage: Search file");
         System.exit(1);
      }
      
      // count the number of lines in file
      Scanner in = new Scanner(new File(args[0]));
      in.useDelimiter("\\Z"); // matches the end of file character
      String s = in.next();  // read in whole file as a single String
      in.close();
      String[] lines = s.split("\n");  // split s into individual lines
      int lineCount = lines.length;	 // extract length of the resulting array
	  int[] lineNumber = new int[lineCount];	// create the lineNumber array
	  int temp, i;
	  for(i=0; i<lineCount; i++){	// loads the line numbers into the array
		 lineNumber[i] = i+1;
	  }
	  
      mergeSort(lines, lineNumber, 0, lineCount-1);
	  for(i=1; i<args.length; i++){	
		 s = args[i];
		 temp = binarySearch(lines, lineNumber, 0, lineCount, s);
		 if(temp != -1){
			System.out.println(args[i] + " found on line " + temp); }
		 else{
			 System.out.println(args[i] + " not found");
			 }
	  }
	  
	  //for(i=0; i<lineCount; i++){	// loads the line numbers into the array
		//System.out.println(lineNumber[i]);
		//System.out.println(lines[i]);
	  //}
   }
}