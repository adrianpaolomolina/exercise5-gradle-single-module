package com.apm;

import java.util.Scanner;
import java.util.ArrayList;
import org.apache.commons.lang3.RandomUtils;
import java.io.File;
import java.io.FileWriter;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import com.apm.OutputUtilities;

public class AdvancedController {

  AdvancedModel advancedModel = new AdvancedModel();
  OutputUtilities outputUtilities = new OutputUtilities();
  InputUtilities inputUtilities = new InputUtilities();
  private int rowIncrement;
  private int columnIncrement;

  public void runActivity ( String file ) {
    if(advancedModel.getKeyList().isEmpty()) {
      retrieveFile(file);
    } printList();
  }

  public void retrieveFile( String file ) {
    advancedModel.setFile(new File(file));
    int row = countRow(file);
    advancedModel.setFileScan(file);
    Scanner scan;
    try{
  			scan = advancedModel.getFileScan().useDelimiter("\\s+[\\|]\\s+");
  			for( rowIncrement = 0 ; rowIncrement < row; rowIncrement++){
          generateRow();
          int column = countColumn(file);
  				for( columnIncrement = 0; columnIncrement < column; columnIncrement++){
  					if(scan.hasNext()){
  						String str = scan.next();
  						String[] split=str.split(" , ");
  						advancedModel.addKeyColumnVal( rowIncrement, split[0]);
              advancedModel.addValueColumnVal( rowIncrement, split[1]);
  					}
  				}
  			}
  	} catch (Exception e){
      outputUtilities.systemMessageFileNotFoundExit();
  	  }
  }

  public int countRow(String file){
      int count = 0;
        try {
            advancedModel.setFileScan(file);
            while(advancedModel.getFileScan().hasNextLine()){
                advancedModel.getFileScan().nextLine();
                count++;
            }
        } catch(Exception e){

          }
      return count;
	}

  public int countColumn(String file){
      int count = 0;
      try {
        advancedModel.setFileScan(file);
        String row = advancedModel.getFileScan().nextLine();
				String[] splitRow = row.trim().split("(\\s+)(\\,)(\\s+)|(\\s+)(\\|)(\\s+)|(\\|)");
				for(String s: splitRow){
				count++;
				}
    	} catch(Exception e){

       	}
      return count/2;
	}

  public void generateRow() {
    advancedModel.addKeyString(new ArrayList<String>());
    advancedModel.addValueString(new ArrayList<String>());
  }

  public String generateNewFile() throws IOException {
    outputUtilities.systemMessageNewFile();
    String fileName = inputUtilities.getNewFileName();
    FileUtils.touch(new File(fileName));
    advancedModel.setFile(new File(fileName));
    generateDefaultContent();
    randomizeArrayList();
    return fileName;
  }

  public void generateDefaultContent(){
    for( rowIncrement = 0 ; rowIncrement < 3; rowIncrement++){
      generateRow();
      for( columnIncrement = 0; columnIncrement < 3; columnIncrement++){
        advancedModel.addKeyColumnVal( rowIncrement, "default");
        advancedModel.addValueColumnVal( rowIncrement, "default");
      }
    }
  }

  public void randomizeArrayList(){
    StringBuilder tempKey;
    StringBuilder tempValue;
    Integer charLength = new Integer(RandomUtils.nextInt(1,4));
    for ( rowIncrement = 0; rowIncrement < advancedModel.getKeyListSize(); rowIncrement++ ){
      for( columnIncrement = 0; columnIncrement < advancedModel.getColumnSize(rowIncrement); columnIncrement++){
        tempKey = new StringBuilder(new Character((char)randomAsciiValue()).toString());
        tempValue = new StringBuilder(new Character((char)randomAsciiValue()).toString());
        for( int i = 0 ; i < charLength ; i++){
          tempKey.append(new Character((char)randomAsciiValue()).toString());
          tempValue.append(new Character((char)randomAsciiValue()).toString());
        }
        advancedModel.getKeyList().get(rowIncrement).set(columnIncrement, tempKey.toString());
        advancedModel.getValueList().get(rowIncrement).set(columnIncrement, tempValue.toString());
      }
    }
    updateFile();
  }

  public void updateFile(){
    	File fileToBeModified = advancedModel.getFile();
    	StringBuilder newString = new StringBuilder("");
    	FileWriter writer = null;
    	try{
	    	writer = new FileWriter(fileToBeModified);
	    	writer.write(assignNewString(newString));
    	} catch(Exception e){
    		e.printStackTrace();
    	} finally {
    		try{
    			writer.flush();
    			writer.close();
    		} catch(Exception e){
    		}
    	}
    }

    public String assignNewString(StringBuilder newString){
      newString.setLength(0);
      for(rowIncrement = 0; rowIncrement < advancedModel.getKeyListSize(); rowIncrement++){
  	   for(columnIncrement = 0; columnIncrement
  	     < advancedModel.getColumnSize(rowIncrement); columnIncrement++){
  	   		  newString.append(advancedModel.getKeyColumnValue(rowIncrement, columnIncrement)
  	 				+ " , " + advancedModel.getValueColumnValue(rowIncrement, columnIncrement) + " | ");
  	   }
  	   newString.append("\n");
  	  }
  	  return newString.toString();
    }

    public int randomAsciiValue(){
      return RandomUtils.nextInt(33, 127);
    }

    public void printList(){
      outputUtilities.printArrayList(advancedModel.getKeyList(),advancedModel.getValueList());
    }

    public void searchResult(){
      int lastIndex = 0;
      int totalCount = 0;
      int count;
      String temporaryKeyValue;
      String findStr = inputUtilities.inputCharToSearch();
       for ( rowIncrement = 0 ; rowIncrement < advancedModel.getKeyListSize() ; rowIncrement++ ) {
        for ( columnIncrement = 0 ; columnIncrement < advancedModel.getColumnSize(rowIncrement) ; columnIncrement++ ) {
          count = 0;
          lastIndex = 0;
          temporaryKeyValue = advancedModel.getKeyColumnValue(rowIncrement, columnIncrement)
                                    .concat(advancedModel.getValueColumnValue(rowIncrement, columnIncrement));
          while (lastIndex != -1) {
            lastIndex = temporaryKeyValue.indexOf(findStr, lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex += 1;
                totalCount++;
            }
          }
          if(count!=0){
            outputUtilities.printCount(rowIncrement, columnIncrement, count);
          }
        }
       }
       System.out.println("Total Findings: " + totalCount);
    }

    public void editOptions(){
      boolean editOptionsOperation = true;
      do{
        try{
          int editOption = inputUtilities.selectOptionEdit();
          switch(editOption){
            case 1:
              inputUtilities.editKey(advancedModel.getKeyList());
              editOptionsOperation = false;
              break;
            case 2:
              inputUtilities.editValue(advancedModel.getValueList());
              editOptionsOperation = false;
              break;
            case 3:
              inputUtilities.editBothKeyValue(advancedModel.getKeyList(), advancedModel.getValueList());
              editOptionsOperation = false;
              break;
            default:
              outputUtilities.systemMessageSelectionNotValid();
              printList();
          }
        } catch (Exception e) {
          outputUtilities.systemMessageSelectionNotValid();
          printList();
        }
      } while ( editOptionsOperation == true );
    }

    public void addRow(){
      try{
        int tempPrevRow = advancedModel.getKeyListSize();
        int addCell = inputUtilities.inputNumberOfValuesToAdd();
        generateRow();
        for ( rowIncrement = tempPrevRow ; rowIncrement < advancedModel.getKeyListSize() ; rowIncrement++ ){
          for( columnIncrement = 0 ; columnIncrement < addCell ; columnIncrement++ ){
            String newKey = inputUtilities.inputKey();
            String newValue = inputUtilities.inputValue();
            advancedModel.addKeyColumnVal(rowIncrement, newKey );
            advancedModel.addValueColumnVal(rowIncrement, newValue );
          }
        }
      } catch(Exception e){
          System.out.println("Input Invalid! Type Again: ");
        }
      printList();
      updateFile();
    }

    public void sortArray(){
      ArrayList<String> temporaryArrayList = new ArrayList<String>();
      for ( rowIncrement = 0 ; rowIncrement < advancedModel.getKeyListSize() ; rowIncrement++ ){
        temporaryArrayList.clear();
        for ( columnIncrement = 0 ; columnIncrement < advancedModel.getColumnSize(rowIncrement) ; columnIncrement++ ) {
          temporaryArrayList.add(advancedModel.getKeyColumnValue(rowIncrement, columnIncrement) + " "
                              + advancedModel.getValueColumnValue(rowIncrement, columnIncrement));
        }
      Collections.sort(temporaryArrayList);
      reSplit(rowIncrement, temporaryArrayList);
      }
      printList();
      updateFile();
    }

    public void reSplit(Integer row, ArrayList<String> temporaryArrayList){
      for( columnIncrement = 0 ; columnIncrement < advancedModel.getKeyList().get(row).size(); columnIncrement++ ){
        String[]splitted = temporaryArrayList.get(columnIncrement).split(" ");
        advancedModel.getKeyList().get(row).set(columnIncrement, splitted[0]);
        advancedModel.getValueList().get(row).set(columnIncrement, splitted[1]);
      }
    }


}
