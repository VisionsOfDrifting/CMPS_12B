/*	Nicholas Pappas
	nhpappas@ucsc.edu
	ID: 1558554
	Assignment: pa4
*/
// ---------------------------------------------------------------------------
// QueueTest.java
// A test client of various functions for Queue.java
// ---------------------------------------------------------------------------
class QueueTest{
	public static void main(String[] args) {
		Queue A = new Queue();
		Job testJob1 = new Job(1,2);
		Job testJob2 = new Job(3,1);
		Job testJob3 = new Job(4,1);

		//testing empty case
		System.out.println("Queue isEmpty()?");
		if(A.isEmpty() == true){ System.out.println("true");} //true
		else { System.out.println("false"); }
		System.out.println( "Queue: " + A.length() ); //0
		System.out.println(A); //toString()
		//Made a statement for 0 elements instead of blank so it is clear 

		// enqueue test
		A.enqueue(testJob1);
		System.out.println("Queue isEmpty()?");
		if(A.isEmpty() == true){ System.out.println("true");}
		else { System.out.println("false"); } //false
		System.out.println( "Queue: " + A.length() ); //1

		A.enqueue(testJob2);
		A.enqueue(testJob3);
		System.out.println(A); //toString()
		System.out.println( "Queue: " + A.length() ); //3

		System.out.println( "Peeking " + A.peek() ); // Test peek() prints testJob3
		
		// dequeue test
		System.out.println( "Dequeueing " + A.dequeue() );
		System.out.println( "Queue: " + A.length() ); //2
		System.out.println( "Peeking " + A.peek() ); // Prints testJob2

		System.out.println( "Dequeueing " + A.dequeue() );
		System.out.println( "Queue: " + A.length() );
		System.out.println( "Dequeueing " + A.dequeue() );
		System.out.println( "Queue: " + A.length() );
		
		//dequeueAll test
		A.enqueue(testJob2);
		A.enqueue(testJob3);
		System.out.println(A);
		System.out.println( "Queue: " + A.length() );
		A.dequeueAll();
		System.out.println( "Queue: " + A.length() );		

		System.out.println(A);



	}
}

