/*
	Sean Burns
*/

import java.util.*;

/********************************************************************************************
* class Customer
*		The 'Customer' object is an analogy for a thread to be processed by an operating system.
*		The customers are generated and processed in a FIFO fashion until there are no more
*		customers to process and the "Teller"s can idle.
*********************************************************************************************/
public class Customer {

	//Protected instance variables.
	int serviceTime;
	int number;
	Random num = new Random();
	
	/********************************************************************************************
	* Customer()
	*		Constructor method, generates a customer with a uniquely identifying number and a
	*		random service time required to process the customer.
	*@param n
	*		The uniquely identifying customer number.
	*********************************************************************************************/
	public Customer(int n) {
		number = n;
		serviceTime = num.nextInt(999) ;
	}
	
	/********************************************************************************************
	* getServiceTime()
	*		getter method for the serviceTime instance variable.
	*********************************************************************************************/
	public int getServiceTime() {
		return serviceTime;
	}
	
	/********************************************************************************************
	* getNumber()
	*		getter method for the number instance variable.
	*********************************************************************************************/
	public int getNumber() {
		return number;
	}
}