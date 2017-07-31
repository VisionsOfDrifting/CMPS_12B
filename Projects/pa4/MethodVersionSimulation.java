//-----------------------------------------------------------------------------
// Simulation.java
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Simulation{

//-----------------------------------------------------------------------------
//
// The following function may be of use in assembling the initial backup and/or
// storage queues.  You may use it as is, alter it as you see fit, or delete it
// altogether.
//
//-----------------------------------------------------------------------------

  	public static int numberOfJobs(Scanner sc) { 
		String s = sc.nextLine();
		int x = Integer.parseInt(s);
		return x;
	}

	public static Job getJob(Scanner in) { /* Given in SimulationStub*/
		String[] s = in.nextLine().split(" ");
		int a = Integer.parseInt(s[0]);
		int d = Integer.parseInt(s[1]);
		return new Job(a, d);
	}
   
//-----------------------------------------------------------------------------
// More Methods
//-----------------------------------------------------------------------------
	public static int findNextEvent(int timeNow, int indexPtr, int n, Queue[] processor) {
		int min = 999999, finishTime = -1;
		Job job = (Job)processor[0].peek();
		if (!processor[0].isEmpty() && job.getFinish()==-1) {
			min = job.getArrival();
			indexPtr = 0;
		}
		for(int i = 1; i <= n; i++) {
			job = (Job)processor[i].peek();
			if (processor[i].length() != 0 && job.getFinish()!=-1) {
				finishTime = job.getFinish();
			}
			if (finishTime == -1) {
				job.computeFinishTime(timeNow);
			} else if (finishTime < min) {
				min = finishTime;
				indexPtr = i;
			}
		}
		if(min == 999999){
			return -1;
		}
		return min;
	}
	
	public static void doesArrivalEvents(int n, Queue[] processor){
		int tempLength, length, tempIndex = 1;
		length = processor[tempIndex].length();
		for (int i = 1; i <= n; i++) { //checks length of processors to find shortest queue
			tempLength = processor[i].length();
			if (tempLength < length) {
				length = tempLength;
				tempIndex = i;
			}
		}
		Job job = (Job)processor[0].dequeue(); //queues job from storage to shortest queue
		processor[tempIndex].enqueue(job);
	}
	
	public static void doesFinishEvents(int index, int maxWait, int totalWait, Queue[] processor){
		Job job = (Job)processor[index].dequeue();
		processor[0].enqueue(job);
		int tempWait = job.getWaitTime();
		if (tempWait > maxWait){
			maxWait = tempWait;
			totalWait += tempWait;
		}
	}
//-----------------------------------------------------------------------------
//
// The following stub for function main contains a possible algorithm for this
// project.  Follow it if you like.  Note that there are no instructions below
// which mention writing to either of the output files.  You must intersperse
// those commands as necessary.
//
//-----------------------------------------------------------------------------

   public static void main(String[] args) throws IOException{

//    1.  check command line arguments
	if(args.length != 1){
		System.err.println("Usage: Simulation input-file\n");
		System.exit(1);
	}
//    2.  open files for reading and writing
	Scanner in = new Scanner(new File(args[0]));
	PrintWriter rpt = new PrintWriter(new FileWriter(args[0] + ".rpt"));
	PrintWriter trc = new PrintWriter(new FileWriter(args[0] + ".trc"));
    
//    3.  read in m jobs from input file
	int numJobs = numberOfJobs(in);  //extract m = number of Jobs
//    5.      declare and initialize an array of n processor Queues and any 
//            necessary storage Queues
	Queue backup = new Queue();
	Queue storage = new Queue();
	Queue[] processor = new Queue[numJobs+1]; //may be numJobs + 1
	processor[0] = storage; //This is unnecesarry, but for consistencies sake
	while(in.hasNextLine()) { //get m jobs store them in a backup queue for when restarting simulation
		backup.enqueue(getJob(in));
	}
	for(int i = 1; i<numJobs; i++){ 
		processor[i] = new Queue(); //Intialize Queues equal to m and load them into a Queue[]
	}
	trc.println("Trace file: " + (args[0] + ".trc")); //Heading on output files
	trc.println(numJobs + " Jobs:");
	trc.println(backup.toString());
	trc.println();
	rpt.println("Report file: " + (args[0] + ".rpt"));
	rpt.println(numJobs + " Jobs:");
	rpt.println(backup.toString());
	rpt.println();
	rpt.println("***********************************************************");
//    4.  run simulation with n Processors for n=1 to n=m-1  { //initialize all n at once
	for(int n = 1; n < numJobs; n++){
		Job job = null;
		int timeNow = 0, totalWait = 0, maxWait = 0, index = 1;
		double avgWait = 0.0;
		for(int i = 0; i < backup.length(); i++) {  //At the begining of each itteration of n n
				job = (Job)backup.dequeue();
				job.resetFinishTime();				//Reset each jobs finish time
				processor[0].enqueue(job);			//Load jobs in backup queue into storage queue
				backup.enqueue(job);
		}
		System.out.println(processor[0]);
		int timeNext = findNextEvent(timeNow, index, n, processor);
		
		trc.println("*****************************");
		if(n == 1){
			trc.println(n + " processor:");
		}else{
			trc.println(n + " processors:");
			trc.println("*****************************");
		}
		trc.println("time=" + timeNow);
		trc.println("0: " + processor[0].toString());
		for(int i = 1; i <= n; i++) {
			trc.println(i + ": " + processor[i]);
		}
//    6.      while unprocessed jobs remain  {
		while(timeNext != -1){
			timeNow = timeNext;
			//do event
			if(index == 0){
				doesArrivalEvents(n, processor);
			}else{
				doesFinishEvents(index, maxWait, totalWait, processor);
			}
			timeNext = findNextEvent(timeNow, index, n, processor);
			//after a lot of staring at psuedo code I realized the order must be
			//finish events || arrival event, compute finish time, find next event repeat.
			trc.println();
			trc.println("time=" + timeNow);
			trc.println("0: " + processor[0].toString());
			for(int i = 1; i <= n; i++){
				trc.println(i + ": " + processor[i]);	
			}
		}
//    7.          determine the time of the next arrival or finish event and 
//                update time
//    8.          finishTimelete all processes finishing now
//
//    9.          if there are any jobs arriving now, assign them to a processor 
//                Queue of minimum length and with lowest index in the queue array.
//
//    10.     } end loop
		
//    11.     finishTimeute the total wait, maximum wait, and average wait for 
//            all Jobs, then reset finish times
		avgWait = ((double)totalWait/numJobs);
		avgWait = (double)Math.round(avgWait*100)/100;
		trc.println();
		if (n == 1){
			rpt.println(n + " processor: totalWait=" + totalWait + ", maxWait=" + maxWait + ", averageWait=" + avgWait);
		}else{
			rpt.println(n + " processors: totalWait=" + totalWait + ", maxWait=" + maxWait + ", averageWait=" + avgWait);
		}
		timeNow = 0;
		processor[0].dequeueAll();
//    12. } end loop
	}	
//    13. close input and output files
	in.close();
	rpt.close();
	trc.close();
    
  }
}

