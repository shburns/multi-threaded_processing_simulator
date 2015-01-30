/*
	Sean Burns
*/

import java.util.*;
import java.io.*;

/********************************************************************************************
* class Teller
*		An instance of a 'Teller' which processes 'customer' instances (threads).
*********************************************************************************************/
public class Teller extends Thread {

	//Private instance methods.
	private Customer currentCustomer;
	private Counter counter;
	private Output out;
	private Random num = new Random();

	/********************************************************************************************
	* Teller()
	*		Constructor method instantiates a Teller with a reference to its counter and the
	*		output interface object.
	*********************************************************************************************/
	public Teller (Counter c, Output o) {
		counter = c;
		out = o;
	}
	
	/********************************************************************************************
	* run()
	*		The thread process that will process each customer that is presented to the teller's
	*		counter in the amount of time necessary to service that customer, adn then retrieve
	*		the next customer assigned to the teller's counter.
	*********************************************************************************************/
	public void run () {
		for (;;) {
			try {
					currentCustomer = counter.removeCustomer();
			}
			catch(Exception e) {
				return;
			}
			try {
				if (currentCustomer != null) {
					out.addMessage("[Teller " + counter.getNumber() + "]: Processing customer " + currentCustomer.getNumber() + " for " + (currentCustomer.getServiceTime()/10) + " seconds.");
					Thread.sleep(currentCustomer.getServiceTime());
					out.addMessage("[Teller " + counter.getNumber() + "]: Customer " + currentCustomer.getNumber() + " processed for " + (currentCustomer.getServiceTime()/10) + " seconds.");
					if (currentCustomer.getNumber() > 990) {
						out.saveDiagnostics();
					}
					currentCustomer = null;
				}
				else {
					Thread.sleep(num.nextInt(999));
				}
			}
			catch(Exception e) {
				return;
			}
		}
	}
}