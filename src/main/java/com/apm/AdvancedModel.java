package com.apm;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class AdvancedModel {

  private ArrayList<ArrayList<String>> keyList = new ArrayList<ArrayList<String>>();
  private ArrayList<ArrayList<String>> valueList = new ArrayList<ArrayList<String>>();
  private File file;
  private Scanner fileScan;
  protected boolean overallOperation = true;
  protected boolean selectionOperation = false;

  public void addKeyString ( ArrayList<String> str ) {
    this.keyList.add(str);
  }

  public void addValueString ( ArrayList<String> str ) {
    this.valueList.add(str);
  }

  public void addKeyColumnVal ( int row, String str ) {
    this.keyList.get(row).add(str);
  }

  public void addValueColumnVal ( int row, String str ) {
    this.valueList.get(row).add(str);
  }

  public ArrayList<ArrayList<String>> getKeyList() {
    return this.keyList;
  }

  public ArrayList<ArrayList<String>> getValueList() {
    return this.valueList;
  }

  public Scanner getFileScan() {
    return this.fileScan;
  }

  public int getKeyListSize() {
    return getKeyList().size();
  }

  public int getValueListSize() {
    return getValueList().size();
  }

  public ArrayList<String> getKeyColumnValue ( int rowIncrement ) {
    return getKeyList().get(rowIncrement);
  }

  public ArrayList<String> getValueColumnValue ( int rowIncrement ) {
    return getValueList().get(rowIncrement);
  }

  public String getKeyColumnValue ( int rowIncrement, int columnIncrement ) {
    return getKeyList().get(rowIncrement).get(columnIncrement);
  }

  public String getValueColumnValue ( int rowIncrement, int columnIncrement ) {
    return getValueList().get(rowIncrement).get(columnIncrement);
  }

  public int getColumnSize ( int rowIncrement ) {
    return getKeyList().get(rowIncrement).size();
  }

  public void setFileScan ( String file ) {
    try{
      this.fileScan = new Scanner(new File(file));
    } catch ( Exception e ) {
      }
  }

  public void setFile ( File file ) {
    this.file = file;
  }

  public File getFile() {
    return this.file;
  }

}
