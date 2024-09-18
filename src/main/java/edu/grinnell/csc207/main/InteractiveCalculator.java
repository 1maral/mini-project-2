/**
 *  CSC-207-02 (Fall)
 *  Mini-Project 2: Fun with Fractions
 *  Maral Bat-Erdene
 *  2024-09-14
 */

package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Scanner;

import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;

public class InteractiveCalculator {
  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Performs an arithmetic operation using the calculator.
   */
  private static void 
  performOperation(BFCalculator calculator, BigFraction value, String operator) {
    if (operator == null) {
      System.err.println("Error: Operator missing.\n");
      return;
    } // if

    switch (operator) {
      case "+":
        calculator.add(value);
        break;
      case "-":
        calculator.subtract(value);
        break;
      case "*":
        calculator.multiply(value);
        break;
      case "/":
        calculator.divide(value);
        break;
      default:
        System.err.println("Error: Unknown operator.\n");
        return;
    } // switch
  } // performOperation

  /**
   * Evaluates an expression and returns the result as a BigFraction.
   */
  private static BigFraction
  evaluateExpression(String[] values, BFCalculator calculator, BFRegisterSet registers) {
    BigFraction currentValue = null;
    String operator = null;

    for (String value : values) {
      // Check if the value is a register char
      if (value.length() == 1 && (value.charAt(0) >= 'a' && value.charAt(0) <= 'z')) {
        BigFraction regValue = registers.get(value.charAt(0));

        if (regValue == null) {
          System.err.println("Error: Unknown register.\n");
          return null;
        } // if

        if (currentValue == null) {
          currentValue = regValue;
        } else {
          performOperation(calculator, regValue, operator);
        } // else
      } // if

      // Check if the value is an operator
      else if (value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/")) {
        operator = value;
      } // else if

      // Check if the value is a fraction, integer, or negative fraction
      else if (BigFraction.isInteger(value) || BigFraction.isFraction(value)) {
        BigFraction numValue = null;
        if (BigFraction.isInteger(value)){
          numValue = new BigFraction(Integer.valueOf(value));
        } else {
          numValue = new BigFraction(value);
        } // if

        if (currentValue == null) {
          currentValue = numValue;
          calculator.clear();
          calculator.add(numValue);
        } else {
          performOperation(calculator, numValue, operator);
        } // if

      // Not an operator, a fraction, an integer, negative fraction, register char
      } else {
        System.err.println("Error: Unexpected input for an expression.\n");
        return null;
      } // if
    } // for
    return calculator.get();
  }

  /**
   * Handles the STORE command, storing the last computed value in a register.
   */
  private static void handleStoreCommand(String[] values, BFCalculator calculator, BFRegisterSet registers) {
    if (values.length == 2) {
      char register;
      if (values[1].length() == 1) {
        register = values[1].charAt(0);
      } else {
        System.err.println("Error: Register char is not a lowercase letter.\n");
        return;
      } // else

      if (register >= 'a' && register <= 'z') {
        BigFraction lastValue = calculator.get();
        registers.store(register, lastValue);
        System.out.println("STORED" + lastValue + " in register " + register);
      } else {
        System.err.println("Error: Register char is not a lowercase letter.\n");
        return;
      } // else
    } else {
      System.err.println("Error: Incorrect number of parameters.\n");
      return;
    } //else
  } // handleStoreCommand

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  public static void main(String[] args) {
    // Initialize the calculator and the register set
    BFCalculator calculator = new BFCalculator();
    BFRegisterSet registers = new BFRegisterSet();

    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);

    pen.print("Enter an expression: ");
    pen.flush();
    
    while(true){
      String input = eyes.nextLine();
      String[] values = input.split(" ");

      for(String val : values){
        pen.printf("\'%s\'\n", val);
      }

      // Check if the user entered QUIT
      if (input.equals("QUIT")) {
        break;
      }
      // Handle STORE command
      if (values[0].equals("STORE")) {
        handleStoreCommand(values, calculator, registers);
      } else {
        // Otherwise, treat it as an arithmetic expression
        BigFraction result = evaluateExpression(values, calculator, registers);
        System.out.println(result);
        //System.err.println("Error: Invalid expression.\n");
      } // else
    } // while
    pen.close();
    eyes.close();
  } // main
} // class InteractiveCalculator
