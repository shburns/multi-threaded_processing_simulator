/*
	Sean Burns
*/

import java.util.*;
import java.io.*;

/********************************************************************************************
* class CustomerQueue
*		The FIFO queue for the 'customers' to be processed by the 'tellers'.
*********************************************************************************************/
public class CustomerQueue {

	//Private instance and static vars
	private static final int MAX_QUEUE_LENGTH = 20;
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private boolean full;
	private boolean empty;
	private Output out;
	
	/********************************************************************************************
	* CustomerQueue()
	*		Constructor object, takes a reference to an Output interface.
	*********************************************************************************************/
	public CustomerQueue (Output o) {
		out = o;
	}
	
	/********************************************************************************************
	* addCustomer()
	*		Adds a customer to the queue if and when there is a space for a new customer, as
	*		restricted by the static MAX_QUEUE_LENGTH property of the CustomerQueue class.
	*********************************************************************************************/
	public synchronized void addCustomer(Customer c)  throws InterruptedException {
		while(customers.size() == MAX_QUEUE_LENGTH ) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				throw e;
			}
		}
		out.addMessage("[CustomerQueue]: Customer number " + c.getNumber() + " added at position " + customers.size() + ".");
		customers.add(c);
		empty = false;
		if (customers.size() == MAX_QUEUE_LENGTH)
			full = true;
		notify();
	}
	
	/********************************************************************************************
	* getNextCustomer()
	*		Returns the next customer from the queue, as long as there is at least one customer
	*		in the queue.
	*********************************************************************************************/
	public synchronized Customer getNextCustomer()  throws InterruptedException{
		while (customers.size() == 0) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				throw e;
			}
		}
		out.addMessage("[CustomerQueue]: Customer number " + customers.get(0).getNumber() + " left queue to be serviced.");
		Customer c = customers.remove(0);
		if (customers.size() == 0)
			empty = true;
		if (customers.size() < MAX_QUEUE_LENGTH)
			full = false;
		notify();
		return c;
	}
}