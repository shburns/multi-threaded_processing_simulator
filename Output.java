/*
	Sean Burns
*/

import java.util.*;
import java.io.*;

/********************************************************************************************
* class Output
*		Interface class for the program that will handle piping output to the proper device
*		or output location.
*********************************************************************************************/
public class Output {

private String diagnosticPrint;

	/********************************************************************************************
	* Output()
	*		Constructor method. Creates an instance of the Output method and prints out an intial
	*		diagnostic message.
	*********************************************************************************************/
	public Output() {
		System.out.println("<<Diagnostic Messages>>\n" + 
			"<<Multithread Processing Simulation>>\n" +
			"<<Sean Burns>>\n");
	}

	/********************************************************************************************
	* addMessage()
	*		Adds a message to the Output pipe.
	*********************************************************************************************/
	public void addMessage(String s) {
		diagnosticPrint = diagnosticPrint + "\n" + s;
		System.out.println(s);
	}
	
	/********************************************************************************************
	* saveDiagnostics()
	*		When executed, will save the diagnostic messages into a file for future reference.
	*********************************************************************************************/
	public void saveDiagnostics() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("Test Results.txt"));
			writer.write(diagnosticPrint);
			writer.close();
		}
		catch(Exception e) {}
	}
}