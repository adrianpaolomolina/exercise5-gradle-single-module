package com.apm;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.NumberFormatException;

public class InputUtilities {

  Scanner scan = new Scanner(System.in);

  public void systemMessageExit() {
    System.out.println("\nThank you for using the program! ");
    System.exit(0);
  }

  public void systemMessageFileNotFoundExit() {
    System.out.println("File not Found");
    System.exit(0);
  }

  public void printArrayList ( ArrayList<ArrayList<String>> key,
                                ArrayList<ArrayList<String>> value ) {
			System.out.println();
			for ( int i = 0 ; i < key.size(); i++ ) {
				for ( int j = 0 ; j < key.get(i).size(); j++ ) {
					System.out.print(key.get(i).get(j) + " , " + value.get(i).get(j) + "  |");
				}
				if ( !key.get(i).isEmpty() || !value.get(i).isEmpty() ) {
					System.out.println();
				}
			}
		}

  public void printSelectOperation() {
    System.out.print("\n\n==Choose Operation==\n"
          + "1. Search \n"
          + "2. Edit \n"
          + "3. Add Row \n"
          + "4. Print \n"
          + "5. Sort \n"
          + "6. Reset \n"
          + "7. Exit \n"
          + "\nSelect:" );
  }

  public void systemMessageSelectionNotValid ( ArrayList<ArrayList<String>> key,
                                              ArrayList<ArrayList<String>> value ) {
    System.out.println("Selection not Valid! Input Again!");
    printArrayList( key, value );
  }

  public String inputKey() {
    String newKey;
    System.out.print("Input Key: ");
    newKey = scan.next();
    return newKey;
  }

  public String inputValue() {
    String newValue;
    System.out.print("Input Value: ");
    newValue = scan.next();
    return newValue;
  }

  public int selectOptionEdit(){
    System.out.println("1. Edit Key?\n"
              + "2. Edit Value?\n"
              + "3. Edit Key AND Value\n"
              + "Select: ");
    return Integer.parseInt(scan.next());
  }

  public int selectOptionKeyOrValue(){
    System.out.println("1. Edit Key\n"
              + "2. Edit Value\n"
              + "Select: ");
    return Integer.parseInt(scan.next());
  }

  public void editKey(ArrayList<ArrayList<String>> key){
    boolean isCorrectInput = false;
    do {
      try{
        System.out.print("Input Row of Key: ");
        int editRow = Integer.parseInt(scan.next());
        System.out.print("Input Column of Key: ");
        int editColumn = Integer.parseInt(scan.next());
        if(editRow > key.size() || editColumn > key.get(0).size()){
          System.out.println("Input Exceeds Size of Collection: ");
          continue;
        }
        System.out.print("New Key: ");
        String newKey = scan.next();
        isCorrectInput = true;
        key.get(editRow).set(editColumn, newKey);
      } catch ( NumberFormatException e){
        System.out.println("Input Not Valid! Try Again");
      }
    } while ( isCorrectInput == false );
  }

  public void editValue(ArrayList<ArrayList<String>> value){
    boolean isCorrectInput = false;
      do {
        try{
          System.out.print("Input Row of Value: ");
          int editRow = Integer.parseInt(scan.next());
          System.out.print("Input Column of Value: ");
          int editColumn = Integer.parseInt(scan.next());
          if ( editRow > value.size() || editColumn > value.get(0).size() ) {
            System.out.println("Input Exceeds Size of Collection: ");
            continue;
          }
          System.out.print("New Value: ");
          String newValue = scan.next();
          value.get(editRow).set(editColumn, newValue);
          isCorrectInput = true;
        } catch ( NumberFormatException e) {
          System.out.println("Input Not Valid! Try Again");
        }
      } while ( isCorrectInput == false );
  }

  public void editBothKeyValue ( ArrayList<ArrayList<String>> key, ArrayList<ArrayList<String>> value ) {
    boolean isCorrectInput = false;
    do {
      try{
        System.out.print("Input Row of Key Value: ");
        int editRow = Integer.parseInt(scan.next());
        System.out.print("Input Column of Key Value: ");
        int editColumn = Integer.parseInt(scan.next());
        if ( editRow > key.size() || editColumn > key.get(0).size() ) {
          System.out.println("Input Exceeds Size of Collection: ");
          continue;
        }
        System.out.print("New Key: ");
        String newKey = scan.next();
        System.out.print("New Value: ");
        String newValue = scan.next();
        key.get(editRow).set(editColumn, newKey);
        value.get(editRow).set(editColumn, newValue);
        isCorrectInput = true;
      } catch ( NumberFormatException e ) {
        System.out.println("Input Not Valid! Try Again!");
      }
    } while ( isCorrectInput == false );
  }

  public String inputCharToSearch() {
    System.out.print("Input characters to Search: ");
    return scan.next();
  }

  public int inputNumberOfValuesToAdd() {
    System.out.println("Number of Values To Add: ");
    return scan.nextInt();
  }

  public String getNewFileName(){
    String filename="";
    System.out.println("File Does Not Exist. Generate New File?");
    System.out.print("Input Name of File You Want To Create: ");
    filename = scan.next();
    return filename;
  }

  public int selectOperation(){
    int select = 0;
    boolean isCorrectSelection = false;
      do {
        try{
          printSelectOperation();
          select = scan.nextInt();
          isCorrectSelection = true;
        } catch(Exception e){
          System.out.println("Selection Not Valid. Please Input Again.");
          scan.next();
          }
      } while (!isCorrectSelection) ;
    return select;
  }
}
