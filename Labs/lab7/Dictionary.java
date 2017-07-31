/*	Nicholas Pappas
	nhpappas@ucsc.edu
	ID: 1558554
	Assignment: lab7
*/
//-----------------------------------------------------------------------------
// Dictionary.java
// Linked List implementation of the Dictionary BST ADT
//-----------------------------------------------------------------------------

//import java.io.*;
//import java.util.Scanner;

public class Dictionary implements DictionaryInterface {

//PrintWriter out = null;
//out = new PrintWriter(new FileWriter(args[1]));

   // private inner Node class
   private class Node {
      String key, value;
      Node left, right;

      Node(String x, String y){
         key = x;
		 value = y;
         left = right = null;
      }
   }

   // Fields for the Dictionary class
   private Node root;     // reference to first Node in tree
   private int numPairs;  // number of items in this Dictionary

   // Dictionary()
   // constructor for the Dictionary class
   public Dictionary(){
      root = null;
      numPairs = 0;
   }
   
   // findKey()
   // returns a reference to the Node with this key in this Dictionary
   private Node findKey(Node R, String key){
      if(R==null || key.compareTo(R.key)==0){
		  return R;
	  }
	  if(key.compareTo(R.key)<0){
		  return findKey(R.left, key);
	  }
	  else{ //(key.compareTo(R.key)<0)
		  return findKey(R.right, key);
	  }
   }
   
	// findParent()
	// returns the parent of N in the subtree rooted at R, or returns null 
	// if N is equal to R. (pre: R != null)
   private Node findParent(Node N, Node R){
	Node P=null;
	if( N!=R ){
		P = R;
		while( P.left!=N && P.right!=N ){
			if(N.key.compareTo(P.key)<0){
				P = P.left;}
			else{
				P = P.right;
				}
			}
		}
	return P;
   }
   
   // findLeftmost()
	// returns the leftmost Node in the subtree rooted at R, or null if R is null.
	private Node findLeftmost(Node R){
	Node L = R;
	if( L!=null ){ 
		for( ; L.left!=null; L=L.left){}
		}
		return L;
	}
	
	// printInOrder()
	// prints the (key, value) pairs belonging to the subtree rooted at R in order
	// of increasing keys to file pointed to by out.
	private String printInOrder(Node R, String s){
	if( R!=null ){
		s = printInOrder(R.left, s);
		s += R.key + " " + R.value + "\n";
		s = printInOrder(R.right, s);
		} else{}
	return s;
	}

	// deleteAll()
	// deletes all Nodes in the subtree rooted at N.
	void deleteAll(Node N){
	if( N!=null ){
		deleteAll(N.left);
		deleteAll(N.right);
		N = null;
		}
	}



   // Implementation of ADT operations //////////////////////////////////////

   // isEmpty()
   // pre: none
   // post: returns true if this Dictionary is empty, false otherwise
   public boolean isEmpty(){
      return(numPairs == 0);
   }

   // size()
   // pre: none
   // post: returns the number of entries in this Dictionary
   public int size() {
      return numPairs;
   }

   // lookup()
   // pre: none
   // returns value associated key, or null reference if no such key exists
   public String lookup(String key) {
      Node N = findKey(root, key);
	  if(N == null){
		return null;
	  }
      return N.value;
   }

   // insert()
   // inserts new (key,value) pair into this Dictionary
   // pre: lookup(key)==null
   public void insert(String key, String value) throws DuplicateKeyException{
	Node N, A, B;
      if(findKey(root, key)!=null){
         throw new DuplicateKeyException(
            "insert(): cannot insert duplicate keys " + key);
      }
		N = new Node(key, value);
		B = null;
		A = root;
		while( A!=null ){
			B = A;
			if( key.compareTo(A.key)<0 ){ A = A.left;}
			else {A = A.right;}
		}
		if( B==null ){ root = N;}
		else if( key.compareTo(B.key)<0 ){ B.left = N;}
		else{ B.right = N;}
		numPairs++;
   }

   // delete()
   // deletes pair with the given key
   // pre: lookup(key)!=null
   public void delete(String key) throws KeyNotFoundException{
      Node N, P, S;
   N = findKey(root, key);
   if(findKey(root, key)==null){
         throw new KeyNotFoundException(
            "delete(): cannot delete non-existent key " + key);
      }
   if( N.left==null && N.right==null ){  // case 1 (no children)
      if( N==root ){
         root = null;
      }else{
         P = findParent(N, root);
         if( P.right==N ){ P.right = null;}
         else{ P.left = null;}
      }
   }else if( N.right==null ){  // case 2 (left but no right child)
      if( N==root ){
         root = N.left;
		 N= null;
      }else{
         P = findParent(N, root);
         if( P.right==N ){ P.right = N.left;}
         else{ P.left = N.left;}
		 N= null;
      }
   }else if( N.left==null ){  // case 2 (right but no left child)
      if( N==root ){
         root = N.right;
         N=null;
      }else{
         P = findParent(N, root);
         if( P.right==N ) P.right = N.right;
         else P.left = N.right;
         N=null;
      }
   }else{                     // case3: (two children: N.left!=null && N.right!=null)
      S = findLeftmost(N.right);
      N.key = S.key;
      N.value = S.value;
      P = findParent(S, N);
      if( P.right==S ){ P.right = S.right;}
      else{ P.left = S.right;}
      S= null;
   }
   numPairs--;
   }

   // makeEmpty()
   // pre: none
   public void makeEmpty(){
    deleteAll(root);
	root = null;
	numPairs = 0;
   }

   // toString
   // pre: none
   // post:  prints current state to stdout
   // Overrides Object's toString() method
   public String toString(){
      String s = printInOrder(root, "");
      return s;
   }
}