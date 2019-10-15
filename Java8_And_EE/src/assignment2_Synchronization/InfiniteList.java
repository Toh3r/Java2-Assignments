package assignment2_Synchronization;
//Import ArrayLists
import java.util.*;

//Create infinite list class, implement runnable interface
public class InfiniteList implements Runnable {
	
	//Create synchronized list
	private static List<String> infiniList = Collections.synchronizedList(new ArrayList<>());
	
	//Create thread variables
	private Thread thread;
	private String threadNum;
	
	//Create constructor
	public InfiniteList (String num) {
		threadNum = num;
	}
	
	//Method to start threads
	public void start() {
		if(thread == null) {
			//Create thread
			thread = new Thread(this, threadNum);
			//Start thread execution
			thread.start();
		}
	}
	
	//Create run class
	public void run() {
		
		//Set condition for while loop
		boolean valid = true;
		
		//Create infinite loop
		while (valid) {
			
			//Synchronize ArrayList so only one thread at a time can access it
			synchronized(infiniList) {
				
				//If statement to add word "This" to empty array
				if(infiniList.isEmpty()){
					infiniList.add("This");
					System.out.println(infiniList.get(0));
				}
				//Create last variable to determine last entry in List
				String last = infiniList.get(infiniList.size() - 1);
				
				//IfElse statements to determine which word to add to array
				if( last == "infinite") {
					infiniList.add("This");
				}
				
				else if(last == "This") {
					infiniList.add("is");
				}
				
				else if(last == "is") {
					infiniList.add("infinite");
				}
			}
			//Print current element in ArrayList
			System.out.println(infiniList.get(infiniList.size() -1));
		}
	}

	//Create main method
	public static void main(String[] args) {
		
		//Create instance of InfinateList, call start() method to begin thread execution
		InfiniteList T1 = new InfiniteList("1");
		T1.start();
		
		InfiniteList T2 = new InfiniteList("2");
		T2.start();
		
		InfiniteList T3 = new InfiniteList("3");
		T3.start();	
	}
}
