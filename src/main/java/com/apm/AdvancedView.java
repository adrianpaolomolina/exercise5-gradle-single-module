package com.apm;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;

public class AdvancedView {

  AdvancedController advancedController = new AdvancedController();
  OutputUtilities outputUtilities = new OutputUtilities();
  InputUtilities inputUtilities = new InputUtilities();
  boolean overallOperation = true;
  boolean selectOperation = false;

  public void startActivity(String[] file) {
    try{
      advancedController.runActivity(file[0]);
    } catch ( ArrayIndexOutOfBoundsException e){
      try {
        advancedController.runActivity(advancedController.generateNewFile());
      } catch ( IOException ex ){
        System.out.println("Failed to create file");
      }
    }
  }

  public void switchOperation(){
    switch(inputUtilities.selectOperation()){
			    		case 1:								//Search
                advancedController.searchResult();
			    			break;
			    		case 2:								//Edit specific key value
                advancedController.editOptions();
                advancedController.updateFile();
			    			break;
			    		case 3:								//Add row
                advancedController.addRow();
                break;
			    		case 4:								//Print method
			    			advancedController.printList();
			    			break;
			    		case 5:								// Sort by Row
                advancedController.sortArray();
			    			break;
			    		case 6:								// randomize Reset
                advancedController.randomizeArrayList();
                selectOperation = false;
			    			break;
			    		case 7:								//Exit method
			           outputUtilities.systemMessageExit();
			    			break;
			    		default:							//Default message if not within case.
			    			System.out.println("Selection not Valid! Input Again!");
                advancedController.printList();
			    	}
  }


}
