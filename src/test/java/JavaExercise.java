/*
 *  Java Exercise for Advanced Java
 *  Adrian Paolo M. Molina
 *
 */

import java.util.*; 
import java.io.*; 

public class JavaExercise extends Operations {

	/*
	 * Start of main method
	 */

	public static void main( String[]args ) {

		/*
		 * loop for overall operation.
		 */

		do{
			try{

				if(key.isEmpty() || value.isEmpty()) {

					assignValues(args[0]); 				//gets argument from command line for file

				}										

				printArrayList();
				selectionOperation = true;				//after file is uploaded, proceed to selection
				overallOperation = false;				
			
			} catch(Exception e) {

				//randomFileContent(args[0]);
				systemMessageFileNotFoundExit(); 		//executes if file is not found.

			}
				/*
				 * loop for the selection of operation.
				 */

				do{

					switchOperation(args[0]);			//passes the file so it can be used by other methods.

				} while (selectionOperation==true);

		
		} while ( overallOperation == true );

	}


}