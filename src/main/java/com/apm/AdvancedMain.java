package com.apm;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;

public class AdvancedMain {

  public static void runProgram(String[] args){
    AdvancedView advancedView = new AdvancedView();
    do {
      advancedView.startActivity(args);
      advancedView.selectOperation = true;
      do {
      advancedView.switchOperation();
    } while (advancedView.selectOperation == true);
  } while (advancedView.overallOperation == true);
  }

  public static void main( String[]args ) {
    runProgram(args);
  }
}
