/*	Nicholas Pappas
	nhpappas@ucsc.edu
	ID: 1558554
	Assignment: pa3
*/
//-----------------------------------------------------------------------------
// DictionaryTest.java
// Test Client for the Dictionary class
//-----------------------------------------------------------------------------

public class DictionaryTest{

   public static void main(String[] args){
	  String print;
      Dictionary A = new Dictionary();

      A.insert("1","does"); //Start with making sure the Dictionary is loadable
      A.insert("2","this"); //Check intial edge cases first
      A.insert("3","work"); //Had to fix the findKey() to get it to work properly
      A.insert("4","yes");
      A.insert("5","it");
      A.insert("6","does");
      A.insert("7","g");
      System.out.println(A);
	  System.out.println("size= " + A.size()); //Check size()

	  print = A.lookup("3"); //Check lookup() returns properly for value != null
      System.out.println("key=3 "+(print==null?"not found":("value="+print)));
	  System.out.println("isEmpty() is called: " + A.isEmpty()); //check boolean case false
	  
	  System.out.println("makeEmpty() is called"); //Test methods in empty state
	  A.makeEmpty();
	  System.out.println("isEmpty() is called: " + A.isEmpty()); //check bollean case true
	  System.out.println("size= " + A.size());
	  print = A.lookup("3");
      System.out.println("key=3 "+(print==null?"not found":("value="+print)));
	  
	  A.insert("breakfast", "first"); //After making empty add items, test again
	  A.insert("dinner", "2nd");
	  A.insert("lunch", "third");
	  System.out.println(A);
	  System.out.println("size= " + A.size());
	  //System.out.println("Try to insert lunch twice");
	  //A.insert("lunch", "third"); //Test DuplicateKeyException
	  
	  System.out.println("delete lunch and dinner");
	  A.delete("dinner"); //Test delete
	  A.delete("lunch");
	  System.out.println(A);
	  System.out.println("size= " + A.size());
	  //System.out.println("Try to delete dinner again");
	  //A.delete("dinner"); //Test for KeyNotFoundException
	  
	  
	  
   }
}