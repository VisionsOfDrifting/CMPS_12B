/*	Nicholas Pappas
	nhpappas@ucsc.edu
	ID: 1558554
	Assignment: pa4
*/
//-----------------------------------------------------------------------------
// Queue.java
// Linked List based implementation of Queue ADT
//-----------------------------------------------------------------------------

public class Queue implements QueueInterface{

    // private inner Node class
   private class /*Object*/ Node { //Object goes where?
	  Object qJob; //Check syntax
      Node next;

      Node(Object Job){
		 this.qJob = Job;
         next = null;
      }
   }

   // Fields for the Dictionary class
   private Node front;     // reference to first Node in List
   private Node back;     // reference to last Node in List
   private int numItems;  // number of items in this Dictionary

   // IntegerQueue()
   // default constructor for the IntegerQueue class
   public Queue(){
      numItems = 0;
      front = null;
      back = null;
   }

   // isEmpty()
   // pre: none
   // post: returns true if this IntgerQueue is empty, false otherwise
   public boolean isEmpty(){
      return( numItems==0 );
   }
   
   // length()
   // pre: none
   // post: returns the length of this Queue
   public int length(){
	  return numItems;
   }

   // enqueue()
   // adds x to back of this IntegerQueue
   // pre: none
   // post: !isEmpty()
   public void enqueue(Object newItem){
       if( front==null ){
		 Node N = new Node(newItem);
		 front = N;
		 back = N;
	  }else if( front==back ){
		 Node N = front;
		 N.next = new Node(newItem);
		 back = N.next;
	  }else{
		 Node N = back;
		 N.next = new Node(newItem);
		 back = N.next;
      }
      numItems++;
   }

   // dequeue()
   // deletes and returns item from front of this IntegerQueue
   // pre: !isEmpty()
   // post: this IntegerQueue will have one fewer element
   public Object dequeue() throws QueueEmptyException{
      if( numItems==0 ){
         throw new QueueEmptyException("cannot dequeue() empty queue");
      }
      
	  if( front==back ){
		  Node N = front;
		  front = null;
		  back = null;
		  numItems--;
		  return N.qJob;}
	  else{
         Node N = front;
         front = N.next;
		 numItems--;
		 return N.qJob;}
   }

   // peek()
   // pre: !isEmpty()
   // post: returns item at front of Queue
   public Object peek() throws QueueEmptyException{
      if( numItems==0 ){
         throw new QueueEmptyException("cannot peek() empty queue");
      }
      return front.qJob;
   }

   // dequeueAll()
   // sets this IntegerQueue to the empty state
   // pre: !isEmpty()
   // post: isEmpty()
   public void dequeueAll() throws QueueEmptyException{
      if( numItems==0 ){
         throw new QueueEmptyException("cannot dequeueAll() empty queue");
      }
      front = null;
	  back = null;
	  numItems = 0;
   }

   // toString()
   // overrides Object's toString() method
   public String toString() {
      String s = "";
      Node N = front;
	  if( numItems==0 ){s = "";} //I had it as "This Queue is empty." while debuging
	  else{
		 for(int i = 0; i<numItems; i++) {
			 s += N.qJob + " ";
			 N = N.next;}
	  }
    return s;
   }
}