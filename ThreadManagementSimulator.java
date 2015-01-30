/*
	Sean Burns
	
	Project directory:
		Customer.java
		CustomerGenerator.java
		CustomerQueue.java
		CounterManager.java
		Counter.java
		Teller.java
		Output.java
		ThreadManagementSimulator.java
*/

import java.util.*;
import java.io.*;

/********************************************************************************************
* ThreadManagementSimulator
*		Simple thread manager designed to handle threads that arrive in a queue in a FIFO
*		approach. Should avoid deadlock in all circumstances. Designed to implement simulated
*		multi-threaded processing. Purely an academic exercise.
********************************************************************************************/
public class ThreadManagementSimulator {

	/********************************************************************************************
	*	main()
	*		The driver for the thread simulator. Instantiates the output method, generates a
	*		CustomerQueue (essentially, threads to be processed), CustomerGenerator, to create
	*		the threads, a CounterManager ('counters' are the thread processors), then begins
	*		sending customers into the queue and processing customers at different counters.
	*********************************************************************************************/
	public static void main (String [] args) {
	
		Output out = new Output();
		CustomerQueue queue = new CustomerQueue(out);
		
		Thread generator = new CustomerGenerator (queue, out);
		CounterManager counters = new CounterManager(queue, out);
		
		generator.start();
		counters.start();
		
		try {
			generator.join();
			counters.join();
		}
		catch (InterruptedException e) {
			return;
		}
	}
}