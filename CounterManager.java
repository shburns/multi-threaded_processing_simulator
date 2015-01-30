/*
	Sean Burns
*/

import java.util.*;
import java.io.*;

/********************************************************************************************
*	class CounterManager
*		A controller class which creates 'Counter' objects and then serves as the interface
*		between the CustomerQueue object and the individual counters.
*********************************************************************************************/
public class CounterManager extends Thread{

	//Private static and instance variables.
	private static final int MAX_COUNTERS_TO_PROCESS = 10;
	private ArrayList<Counter> counters = new ArrayList<Counter>();
	private CustomerQueue next;
	private Customer nextCustomer;
	private Output out;
	private Random num = new Random();

	/********************************************************************************************
	* CounterManager()
	*		Constructor method which instantiates a CounterManager with a new CustomerQueue object
	*		to process as well as the Output object to print diagnostic information to.
	*********************************************************************************************/
	public CounterManager(CustomerQueue n, Output o) {
		next = n;
		for (int i = 0; i < MAX_COUNTERS_TO_PROCESS; i ++) {
			counters.add(new Counter(i));
			new Teller(counters.get(i), o).start();
		}
		out = o;
	}
	
	/********************************************************************************************
	* run()
	*		Required function to implement abstract Thread class. Performs the work of the thread
	*		In this case, it generates new customers if there isn't one available, then passes
	*		the customer to a counter to be processed. In the event of every counter being full,
	*		the CounterManager will sleep for 1/100th of a second and try to send it to a counter
	*		again.
	*********************************************************************************************/
	public void run() {
		for (;;) {
			try {
				if (nextCustomer == null) {
					nextCustomer = next.getNextCustomer();
					out.addMessage("[Counter Manager]: Customer number " + nextCustomer.getNumber() + " next to be processed.");
				}
			}
			catch(Exception e) {
				return;
			}
			try {
				if (nextCustomer != null) {
					for(int i = 0; i < counters.size(); i ++) {
						if (counters.get(i).isOpen()) {
							out.addMessage("[Counter Manager]: Adding customer number " + nextCustomer.getNumber() + " to counter number " + i + ".");
							counters.get(i).addCustomer(nextCustomer);
							nextCustomer = null;
							break;
						}
					}
				}	
			}
			catch(Exception e) {
				return;
			}
			try {
				Thread.sleep(num.nextInt(99));
			}
			catch(Exception e) {
				return;
			}
			
		}
	}
}