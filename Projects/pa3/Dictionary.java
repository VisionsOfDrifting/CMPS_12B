/*	Nicholas Pappas
	nhpappas@ucsc.edu
	ID: 1558554
	Assignment: pa3
*/
//-----------------------------------------------------------------------------
// Dictionary.java
// Linked List implementation of the Dictionary ADT
//-----------------------------------------------------------------------------

public class Dictionary implements DictionaryInterface {

   // private inner Node class
   private class Node {
      String key, value;
      Node next;

      Node(String x, String y){
         key = x;
		 value = y;
         next = null;
      }
   }

   // Fields for the Dictionary class
   private Node head;     // reference to first Node in List
   private Node tail;     // reference to last Node in List
   private int numItems;  // number of items in this Dictionary

   // Dictionary()
   // constructor for the Dictionary class
   public Dictionary(){
      head = null;
	  tail = null;
      numItems = 0;
   }
   
   // findKey()
   // returns a reference to the Node with this key in this Dictionary
   private Node findKey(String key){
      Node N = head;
      while(N != null){
		if(key.compareTo(N.key) == 0){
			return N;}
		else{
			N = N.next;}
	  }
	  return null;
   }

   // Implementation of ADT operations //////////////////////////////////////

   // isEmpty()
   // pre: none
   // post: returns true if this Dictionary is empty, false otherwise
   public boolean isEmpty(){
      return(numItems == 0);
   }

   // size()
   // pre: none
   // post: returns the number of entries in this Dictionary
   public int size() {
      return numItems;
   }

   // lookup()
   // pre: none
   // returns value associated key, or null reference if no such key exists
   public String lookup(String key) {
      Node N = findKey(key);
	  if(N == null){
		return null;
	  }
      return N.value;
   }

   // insert()
   // inserts new (key,value) pair into this Dictionary
   // pre: lookup(key)==null
   public void insert(String key, String value) throws DuplicateKeyException{

      if(lookup(key)!=null){
         throw new DuplicateKeyException(
            "insert(): cannot insert duplicate keys " + key);
      }
	  
      if(head == null){
		 Node N = new Node(key,value);
		 head = N;
		 tail = N;
		}
	  else if(head == tail){
		 Node N = head;
		 N.next = new Node(key,value);
		 tail = N.next;
	  }else{
		 Node N = tail;
		 N.next = new Node(key,value);
		 tail = N.next;
      }
      numItems++;
   }

   // delete()
   // deletes pair with the given key
   // pre: lookup(key)!=null
   public void delete(String key) throws KeyNotFoundException{
      if(lookup(key)==null){
         throw new KeyNotFoundException(
            "delete(): cannot delete non-existent key " + key);
      }
      else if(findKey(key)==head){
		 if(head == tail){
		  head = null;
		  tail = null;}
		 else{
         Node N = head;
         head = N.next;
         N = null;}
	  }
	  else if(findKey(key)==tail){
		 Node S = tail;
		 Node P = head;
		 while(P.next != tail){
			 P = P.next;}
		 P.next = null;
		 tail = P;
	  }else{
         Node S = findKey(key);
         Node P = head;
		 while(P.next != S){
			 P = P.next;}
         P.next = S.next;
         S = null;
      }
      numItems--;
   }

   // makeEmpty()
   // pre: none
   public void makeEmpty(){
      head = null;
	  tail = null;
      numItems = 0;
   }

   // toString
   // pre: none
   // post:  prints current state to stdout
   // Overrides Object's toString() method
   public String toString(){
      String s = "";
      for(Node N=head; N!=null; N=N.next){
         s += N.key + " " + N.value + "\n";
      }
      return s;
   }
}