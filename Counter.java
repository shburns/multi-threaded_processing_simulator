/*
	Sean Burns
*/

import java.util.*;
import java.io.*;

/********************************************************************************************
* class Counter
*		Represents a thread-handler. The idea being 'customers' are processed at checkout
*		'counters'... I realize a 'counter' would probably have a different meaning in a software
*		context. This may not have been the best analogy for my naming conventions on this.
*********************************************************************************************/
public class Counter {

	//private instance variables.
	private boolean isOpen;
	private Customer currentCustomer;
	private int number;
	
	/*********************************************************************************************
	*	Counter()
	*		Constructor method, instantiates the counter with a number used to identify it uniquely
	*		in the list of counters.
	*@param n
	*		The number of the counter.
	*********************************************************************************************/
	public Counter(int n) {
		number = n;
		isOpen = true;
	}
	
	/********************************************************************************************
	*	addCustomer()
	*		Adds a 'customer' (thread) to the counter to be processed for the total amount of time
	*		necessary to service the customer.
	*@param c
	*		The customer to be processed.
	*********************************************************************************************/
	public synchronized void addCustomer (Customer c) {
		while(!isOpen) {
			try {
				wait();
			}
			catch(Exception e) {}
		}
		isOpen = false;
		currentCustomer = c;
		notify();
	}
	
	/********************************************************************************************
	*	isOpen()
	*		Indicates whether or not the counter is open to process another customer.
	*********************************************************************************************/
	public boolean isOpen() {
		return isOpen;
	}
	
	/********************************************************************************************
	*	getNumber()
	*		Gives the uniquely identifying number of the counter.
	*********************************************************************************************/
	public int getNumber() {
		return number;
	}
	
	/********************************************************************************************
	*	removeCustomer()
	*		Removes the customer from the counter after it has been successfully processed.
	*********************************************************************************************/
	public synchronized Customer removeCustomer() {
		while (isOpen) {
			try {
				wait();
			}
			catch(Exception e) {}
		}
		isOpen = true;
		notify();
		return currentCustomer;
	}
	
	/********************************************************************************************
	* isAvailable()
	*		Indicates whether or not the counter is available to process a customer.
	*********************************************************************************************/
	public boolean isAvailable() {
		return isOpen;
	}
}