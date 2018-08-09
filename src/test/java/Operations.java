import java.util.*;
import java.io.*;

public class Operations {
	

	public static ArrayList<ArrayList<String>> key = new ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<String>> value = new ArrayList<ArrayList<String>>();
	public static int row = 3;
	public static int column = 3;
	public static int rowIncrement = 0;
	public static int columnIncrement = 0;
	public static Boolean overallOperation = new Boolean(true);
	public static Boolean selectionOperation = new Boolean(false);
	public static Random randomAscii = new Random(); 
	public static String File;
	public static Scanner scan;

	/*
	 * method to assign value from file to arraylist
 	 */ 

	public static void assignValues(String file){

		row = countRow(file);
		column = countColumn(file);

		try{
				scan = new Scanner(new File(file)).useDelimiter("\\s+[\\|]\\s+");
				

				for( Integer i = new Integer(0); i < row; i++){
					
					key.add(new ArrayList<String>());
					value.add(new ArrayList<String>());
			
					for( Integer j = new Integer(0); j < column; j++){
									   
						if(scan.hasNext()){
		
							String str = scan.next();	
			
							String[] split=str.split(" , ");
			
							key.get(i).add(split[0]);
							value.get(i).add(split[1]);	
		
						}
					}
		
				}

		} catch(Exception e){
			systemMessageFileNotFoundExit();

			/*System.out.println("File not Found");
			e.printStackTrace();
			System.exit(0);
*/
		}
	} // end of assign value method

	/*
	 * Method to count column in file
	 */

	public static Integer countColumn(String file){
               
        Integer count = new Integer(0);
                    
        try {
            
        	scan = new Scanner(new File(file));
            String row = scan.nextLine();
			String[] splitRow = row.trim().split("(\\s+)(\\,)(\\s+)|(\\s+)(\\|)(\\s+)|(\\|)");

			for(String s: splitRow){

				count++;
               
			}
		
       	} catch(Exception e){

                systemMessageFileNotFoundExit();
       	  }
            
            return count/2;
	} // end of count Column method

	/*
	 * method to count row in file.
	 */

	public static Integer countRow(String file){
               
        Integer count = new Integer(0);
                   
        try {
            scan = new Scanner(new File(file));
                
            while(scan.hasNextLine()){

                scan.nextLine();
                count++;

            }
                
		
        } catch(Exception e){

            systemMessageFileNotFoundExit();

          }
            
            return count;
	} // end of Count row method


	/*
	 * Start of printArrayList Method. Prints values of arrays.
	 */

	public static void printArrayList(){

			System.out.println();
			
			for(int i = 0 ; i < key.size(); i++ ){

				for(int j = 0 ; j < key.get(i).size(); j++){
					
					System.out.print(key.get(i).get(j) + " , " + value.get(i).get(j) + "  |");
					
				}

				if(!key.get(i).isEmpty() || !value.get(i).isEmpty()) {
					System.out.println();
				}
			}

		} // end of print method


	/*
	 * Start of Switch Operation
	 */


	public static void switchOperation(String file){

		switch(selectOperation()){

			    		case 1:								//Search 

			    			systemMessageSearchResult();
			    			printArrayList();
			    			break;

			    		case 2:								//Edit specific key value

			    			try{

			    				editOptions();
			    				printArrayList();

			    			} catch(Exception e){

			    				systemMessageErrorInput();

			    			}

			    			updateFile(file);

			    			break;

			    		case 3:								//Add row

			    			addRow(file);
			    			printArrayList();
			    			break;

			    		case 4:								//Print method

			    			printArrayList();
			    			break;

			    		case 5:								// Sort by Row

			    			sortArray(file);
			    			break;

			    		case 6:								// randomize Reset

			    			randomizeArrayList();
			    			updateFile(file);
			    			overallOperation = true;
			    			selectionOperation = false;
			    			
			    			break;

			    		case 7:								//Exit method

			    			systemMessageExit();
			    			break;

			    		default:							//Default message if not within case. 

			    			systemMessageSwitchDefault();
			    			printArrayList();
			    	}

	} //end of switch operation

	/* 
	 * Provides selection for the switchOperation
	 */ 

	public static Integer selectOperation(){

			scan = new Scanner(System.in);
			Integer select = new Integer(0);
			boolean correctSelection = false;
				
				do {

					try{

			       		System.out.print("\n\n==Choose Operation==\n"
			                + "1. Search \n"
			                + "2. Edit \n"
			                + "3. Add Row \n"
			                + "4. Print \n"
			                + "5. Sort \n"
			                + "6. Reset \n"
			                + "7. Exit \n"
			                + "\nSelect:" );
			       		select = scan.nextInt();
			       		correctSelection = true;

					} catch(Exception e){

						System.out.println("Selection not Valid! Input Again!");
						scan.next();
						
						printArrayList();
					}


				} while (!correctSelection) ;
	 
			return select;
		} // End of select operation method


	/*
	 * Counts how many times a character has been found in the file/array.
	 */

	public static Integer searchResult(){

			scan = new Scanner(System.in);
			Integer lastIndex = new Integer(0);
			Integer count = new Integer(0);
			String temporaryKeyValue;

			System.out.print("Input characters to Search: ");
	        String findStr = scan.nextLine();


	        for ( rowIncrement = 0 ; rowIncrement < key.size() ; rowIncrement++ ) {
	            
	        	for ( columnIncrement = 0 ; columnIncrement < key.get(rowIncrement).size() ; columnIncrement++ ) {
	            	
	            	lastIndex = 0;
	            	
	            	if(!key.get(rowIncrement).isEmpty() || !value.get(columnIncrement).isEmpty()){

	            		temporaryKeyValue = key.get(rowIncrement).get(columnIncrement).concat(value.get(rowIncrement).get(columnIncrement));

		            	while (lastIndex != -1) {

							lastIndex = temporaryKeyValue.indexOf(findStr, lastIndex);

		        	     	if (lastIndex != -1) {

		            	     	count++;
		                	 	lastIndex += 1;
		              		}
		            	}

		            }

	            
	          	}
	          
	        } return count; 

    } // end of search method

   
    /*
     * Start of edit options - key & value or Key or Value
     */

    public static void editOptions(){

    	scan = new Scanner(System.in);
    	Boolean editOptionsOperation = new Boolean(true);	
    	System.out.println("1. Edit Key OR Value?\n"
    						+ "2. Edit Key AND Value");
    	systemMessageSelect();
    	Integer editOption = Integer.parseInt(scan.next());

    	do{

    		try{

    			switch(editOption){
    			
	    			case 1: 
	    			
	    				editKeyOrValue();
	    				break;
	    			
	    			case 2:
	    			
	    				editBothKeyValue();
	    				break;
	    			
	    			default: 

	    				systemMessageSwitchDefault();
    			}

    			editOptionsOperation = false;

    		} catch(Exception e){

    			systemMessageErrorInput();

    		}


    	} while ( editOptionsOperation == true );
 		
    } // end of Edit options.


    /*
     * randomize list for reset
     */

    public static void randomizeArrayList(){

    	try{
		
			Integer charAmountPerKeyValue = new Integer(randomAscii.nextInt(3)+1);
		 	StringBuilder tempKey;
	   		StringBuilder tempValue;
	   		key.trimToSize();
	   		value.trimToSize();

    	    for ( rowIncrement = 0; rowIncrement < key.size(); rowIncrement++ ){
    	
    	    	for( columnIncrement = 0; columnIncrement < key.get(rowIncrement).size(); columnIncrement++){
    	
    	    		if(!key.isEmpty() || !value.isEmpty()) {
    		   				//constant values
    		   			tempKey = new StringBuilder(new Character((char)(randomAscii.nextInt(94)+33)).toString());
    		   			tempValue = new StringBuilder(new Character((char)(randomAscii.nextInt(94)+33)).toString()); 
    	
    		    			for(Integer i = new Integer(0); i < charAmountPerKeyValue ; i++){
    	
    			    			tempKey.append(new Character((char)(randomAscii.nextInt(94)+33)).toString());
    			    			tempValue.append(new Character((char)(randomAscii.nextInt(94)+33)).toString());
    			    			
    		    			}
    	
    		    		key.get(rowIncrement).set(columnIncrement, tempKey.toString());
    		    		value.get(rowIncrement).set(columnIncrement, tempValue.toString());
   						 	    			
    	    		} else 
    	    			continue;
    	    	}
    	    	if(key.isEmpty() || value.isEmpty()){
    	    		continue;
    	    	}
    	
    	    }

    	} catch(Exception e){
    		systemMessageErrorInput();
    	}
    
    } //end of randomize ArrayList

    /*
     * Start of edit key or value method
     */

    public static void editKeyOrValue(){

    	Boolean editKeyOrValueOperation = new Boolean(true);
    	scan = new Scanner(System.in);
    	System.out.println("1. Edit Key\n"
   							+ "2. Edit Value");
    	systemMessageSelect();
    	Integer option = Integer.parseInt(scan.next());

    	do{

    		try{

				switch( option ){

					case 1:

						editKey();
						break;

					case 2:

						editValue();
						break;

					default:
								
						systemMessageSwitchDefault();
						break;
				}    			

				editKeyOrValueOperation = false;

    		} catch(Exception e){

    			systemMessageErrorInput();
    		}


    	} while ( editKeyOrValueOperation == true);

    } //end of edit key or value method

    /*
     * Start of edit key method 
     */

    public static void editKey(){
    	
    		scan = new Scanner(System.in);

    		System.out.print("Input Row of Key: ");
			Integer editRow = Integer.parseInt(scan.next());
			System.out.print("Input Column of Key: ");
			Integer editColumn = Integer.parseInt(scan.next());	

			System.out.print("New Key: ");
	    	String newKey = scan.next();

	    	key.get(editRow).set(editColumn, newKey);

    } // end of edit key method

    /*
     * start of edit value method
     */ 

    public static void editValue(){

    		scan = new Scanner(System.in);

	    	System.out.print("Input Row of Value: ");
			Integer editRow = Integer.parseInt(scan.next());
			System.out.print("Input Column of Value: ");
			Integer editColumn = Integer.parseInt(scan.next());	

			System.out.print("New Value: ");
	    	String newValue = scan.next();

	    	value.get(editRow).set(editColumn, newValue);

    } //end of edit value method

    /*
     * Start of add row method
     */

    public static void addRow(String file){

    	try{
    	    	scan = new Scanner(System.in);
    	    	Integer addCell = 0;
    			Integer tempPrevRow = key.size();
    			StringBuilder newString = new StringBuilder("");
    	    	System.out.println("Number of Values To Add: ");
    	    	addCell = Integer.parseInt(scan.next());

    	    	key.add(new ArrayList<String>());
    	    	value.add(new ArrayList<String>());
    	    	

    	    	for ( rowIncrement = tempPrevRow ; rowIncrement < key.size() ; rowIncrement++ ){

    	    		for( columnIncrement = 0 ; columnIncrement < addCell ; columnIncrement++ ){

    	    			System.out.print("Input Key: ");
    	    			String newKey = scan.next();
    	    			System.out.print("Input Value: ");
    	    			String newValue = scan.next();
    	   			if(newKey!=null || newKey!="")	
    	   				key.get(rowIncrement).add(newKey);
    	   			else 
    	   				key.get(rowIncrement).add(" ");
    	   			if(newValue!=null || newValue!="")
    	   				value.get(rowIncrement).add(newValue);
    	   			else
    	   				value.get(rowIncrement).add(" ");
    	    		}

    	    	}

    	    	


    	    } catch(Exception e){

    	    	systemMessageErrorInput();

    	    }

    	    updateFile(file);

    } //end of add row method

    /*
     * Start of update file. Update Text file. 
     */

    public static void updateFile(String file){

    	File fileToBeModified = new File(file);
    	StringBuilder newString = new StringBuilder("");
    	FileWriter writer = null;


    	try{

	    	writer = new FileWriter(fileToBeModified);
	    	writer.write(assignNewString(newString));

    	} catch(Exception e){

    		e.printStackTrace();
    		systemMessageErrorInput();

    	} finally {

    		try{
    			writer.flush();
    			writer.close();

    		} catch(Exception e){


  				
    		}

    	}

    } //end of update file.

    /*
     * start of Sort Array
     */

    public static void sortArray(String file){

    	ArrayList<String> temporaryArrayList = new ArrayList<String>();

    	for ( rowIncrement = 0 ; rowIncrement < key.size() ; rowIncrement++ ){

    		temporaryArrayList.clear();

    		for ( columnIncrement = 0 ; columnIncrement < key.get(rowIncrement).size() ; columnIncrement++ ) {

    			temporaryArrayList.add(key.get(rowIncrement).get(columnIncrement)+" "
    									+value.get(rowIncrement).get(columnIncrement));

    		}
    		Collections.sort(temporaryArrayList);
    		reSplit( rowIncrement, temporaryArrayList);
    		System.out.println(temporaryArrayList);

    	}

    	updateFile(file);
 
    } //end of sort array

    /*
     * splits temporary array for sorting. 
     */

    public static void reSplit(Integer row, ArrayList<String> temporaryArrayList){

    	for( columnIncrement = 0 ; columnIncrement < key.get(row).size(); columnIncrement++ ){

    		String[]splitted = temporaryArrayList.get(columnIncrement).split(" ");

    		key.get(row).set(columnIncrement, splitted[0]);    		
    		value.get(row).set(columnIncrement, splitted[1]);

    	}

    } //end of split method

    /*
     * add values to new created row.
     */

    public static void addToNewRow(){

    	for ( rowIncrement = 0; rowIncrement < key.size(); rowIncrement++ ) {

    		for ( columnIncrement = 0 ; columnIncrement < key.get(0).size(); columnIncrement++ ){

    			if( key.get(rowIncrement).isEmpty() && value.get(rowIncrement).isEmpty() ) {
    				key.get(rowIncrement).set(columnIncrement, "  ");
    				value.get(rowIncrement).set(columnIncrement, "  ");
    			}    			 

    		}

    	}

    } //end of add values to new row method.

    /*
     * method for editing both key and value
     */

    public static void editBothKeyValue(){

    	scan = new Scanner(System.in);

    	System.out.print("Input Row of Key Value: ");
		Integer editRow = Integer.parseInt(scan.next());
		System.out.print("Input Column of Key Value: ");
		Integer editColumn = Integer.parseInt(scan.next());

		System.out.print("New Key: ");
	    String newKey = scan.next();
	    System.out.print("New Value: ");
	    String newValue = scan.next();

	    key.get(editRow).set(editColumn, newKey);
	    value.get(editRow).set(editColumn, newValue);

    } //end of method for editing both key and value.

    /*
     * Assign New String used for rewriting content of file.
     */

    public static String assignNewString(StringBuilder newString){
    	
    	newString.setLength(0);
    	for(rowIncrement = 0; rowIncrement < key.size(); rowIncrement++){
	    					
	    	for(columnIncrement = 0; columnIncrement 
	    		< key.get(rowIncrement).size(); columnIncrement++){

	    		if(key.get(rowIncrement).isEmpty() || value.get(rowIncrement).isEmpty())
	    			continue;
	    		else 
	    			newString.append(key.get(rowIncrement).get(columnIncrement) 
	    							+ " , " + value.get(rowIncrement).get(columnIncrement) + " | ");

	    	}
	    	
	    	if(key.get(rowIncrement).isEmpty() && value.get(rowIncrement).isEmpty())
	    		continue;
	    	else
	    		newString.append("\n");
	    }
	    
	    return newString.toString();
    
    } //end of assign new string


    public static void randomFileContent(String file){

    	Integer rowSize = new Integer(randomAscii.nextInt(3)+1);
    	Integer colSize = new Integer(randomAscii.nextInt(3)+1);

    	try {
		
			Integer charAmountPerKeyValue = new Integer(randomAscii.nextInt(3)+1);
		 	StringBuilder tempKey;
	   		StringBuilder tempValue;


    	    for ( rowIncrement = 0; rowIncrement < rowSize; rowIncrement++ ){
    	
    	    	for( columnIncrement = 0; columnIncrement < colSize; columnIncrement++){
    	
    	    		if(!key.isEmpty() || !value.isEmpty()) {
    		   				
    		   			tempKey = new StringBuilder(new Character((char)(randomAscii.nextInt(94)+33)).toString());
    		   			tempValue = new StringBuilder(new Character((char)(randomAscii.nextInt(94)+33)).toString()); 
    	
    		    			for(Integer i = new Integer(0); i < charAmountPerKeyValue ; i++){
    	
    			    			tempKey.append(new Character((char)(randomAscii.nextInt(94)+33)).toString());
    			    			tempValue.append(new Character((char)(randomAscii.nextInt(94)+33)).toString());
    			    			
    		    			}
    	
    		    		key.get(rowIncrement).set(columnIncrement, tempKey.toString());
    		    		value.get(rowIncrement).set(columnIncrement, tempValue.toString());
   						 	    			
    	    		} else 
    	    			continue;
    	    	}
    	    	if(key.isEmpty() || value.isEmpty()){
    	    		continue;
    	    	}
    	
    	    }
    	    updateFile(file);
    	} catch(Exception e){
    		systemMessageErrorInput();
    	}

    }

    public static void systemMessageErrorInput(){

    	System.out.println("Input Error. Try Again");

    } 

    public static void systemMessageSearchResult(){

    	System.out.println("Total Count of Searched Characters: " 
			    			+ searchResult());
			    			
    }

    public static void systemMessageSwitchDefault(){

    	System.out.println("Selection does not exist! Input Again:");
	  	
    }

    public static void systemMessageExit(){

    	System.out.println("\nThank you for using the program! ");
		System.exit(0);
    
    }

    public static void systemMessageFileNotFoundExit(){

    	System.out.println("File not Found");
		System.exit(0);

    }

    public static void systemMessageSelect(){

    	System.out.print("Select: ");

    }

}
