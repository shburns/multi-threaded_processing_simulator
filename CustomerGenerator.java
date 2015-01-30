/*
	Sean Burns
*/

import java.io.*;
import java.util.*;

/********************************************************************************************
* class CustomerGenerator
*		Factory class for generating customers for the queue to be processed by the 'tellers'
*@extends Thread
*********************************************************************************************/
public class CustomerGenerator extends Thread {

	//Private instance vars
	private CustomerQueue queue;
	private Output out;
	private Random num = new Random();
	
	/********************************************************************************************
	* CustomerGenerator()
	*		Constructor method instantiating a CustomerGenerator with a reference to an instance
	*		of the CustomerQueue class, and a reference to the Output interface for the program.
	*********************************************************************************************/
	public CustomerGenerator(CustomerQueue q, Output o) {
		queue = q;
		out = o;
	}
	
	/********************************************************************************************
	* run()
	*		Required implementation of the 'run()' method of the Thread class. Generates 1000
	*		'Customer' objects and passes references of them to the queue for processing.
	*********************************************************************************************/
	public void run() {
		for (int i = 0; i < 1000; i ++) {
			try {
				Thread.sleep(num.nextInt(99));
			} 
			catch (InterruptedException e) { return; }
			
			try {
				out.addMessage("[Customer Generator]: Customer number " + i + " generated.");
				queue.addCustomer(new Customer(i));
				out.addMessage("[Customer Generator]: Customer number " + i + " accepted into queue.");
			}
			catch(InterruptedException e) {
				return;
			}
		}
	}
}