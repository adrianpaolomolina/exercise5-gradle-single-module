package com.apm;

import java.util.ArrayList;

public class OutputUtilities {

  public void systemMessageFileNotFoundExit() {
    System.out.println("File not Found");
    System.exit(0);
  }

  public void systemMessageExit() {
    System.out.println("\nThank you for using the program! ");
    System.exit(0);
  }

  public void systemMessageSelectionNotValid(){
    System.out.println("Selection not Valid! Input Again!");
  }

  public void printArrayList ( ArrayList<ArrayList<String>> key,
                                  ArrayList<ArrayList<String>> value ) {
  	System.out.println();
  		for ( int i = 0 ; i < key.size(); i++ ) {
				for ( int j = 0 ; j < key.get(i).size(); j++ ) {
  				System.out.print(key.get(i).get(j) + " , " + value.get(i).get(j) + "  |");
  			}
  				System.out.println();
			}
    System.out.println();
  	}

  public void printCount ( int rowIncrement, int columnIncrement, int count ) {
      System.out.println("Character Found in: ["
                        + rowIncrement + "," + columnIncrement + "]\n"
                        + "Number of Occurences: " + count);
    }

  public void systemMessageNewFile(){
    System.out.println("Generate New File...");
    System.out.println("Generating Random Values...");
  }

}
