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

/**
 * A calculator that performs arithmetic operations on BigFraction values.
 */
public class InteractiveCalculator {
  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Performs an arithmetic operation using the calculator.
   *
   * @param calculator the BFCalculator instance to perform the operation on
   * @param value the BigFraction value to operate with
   * @param operator the operator indicating the operation to perform
   * @return true if the operation was successful, false otherwise
   */
  public static boolean
        performOperation(BFCalculator calculator, BigFraction value, String operator) {
    // Check if the operator is given
    if (operator == null) {
      System.err.println("Error: Operator missing.");
      return false;
    } // if

    if (operator.equals("+")) {
      calculator.add(value);
      return true;
    } else if (operator.equals("-")) {
      calculator.subtract(value);
      return true;
    } else if (operator.equals("*")) {
      calculator.multiply(value);
      return true;
    } else if (operator.equals("/")) {
      calculator.divide(value);
      return true;
    } else {
      System.err.println("Error: Unknown operator.");
      return false;
    } // if
  } // performOperation

  /**
   * Evaluates an expression and returns the result as a BigFraction.
   *
   * @param values an array of strings representing the expression
   * @param calculator the BFCalculator instance to perform calculations
   * @param registers the BFRegisterSet for storing and retrieving values
   * @return the result of the evaluated expression, or null if invalid
   */
  public static BigFraction
        evaluateExpression(String[] values, BFCalculator calculator, BFRegisterSet registers) {
    BigFraction currentValue = null;
    String operator = null;
    int numOps = 0;
    int numVals = 0;

    // Loop through each value in the expression
    for (String value : values) {
      // Check if the first value is an operator
      if (values[0].equals("+") || values[0].equals("-")
              || values[0].equals("*") || values[0].equals("/")) {
        System.err.println("Error: Invalid expression.");
        return null;
      } else if (value.length() == 1 && (value.charAt(0) >= 'a' && value.charAt(0) <= 'z')) {
        // Check if the value is a register char
        BigFraction regValue = registers.get(value.charAt(0));

        // Check if the register stored anything
        if (regValue == null) {
          System.err.println("Error: Unknown register.");
          return null;
        } // if

        numVals++;
        if (currentValue == null) {
          currentValue = regValue;
          calculator.clear();
          calculator.add(regValue);
        } else {
          if (!performOperation(calculator, regValue, operator)) {
            return null;
          } // if
        } // else
      } else if (value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/")) {
        // Check if the value is an operator
        numOps++;
        operator = value;
      } else if (BigFraction.isInteger(value) || BigFraction.isFraction(value)) {
        // Check if the value is a fraction, integer, or negative fraction
        BigFraction numValue = null;
        if (BigFraction.isInteger(value)) {
          numValue = new BigFraction(Integer.valueOf(value));
        } else {
          numValue = new BigFraction(value);
        } // if

        numVals++;
        if (currentValue == null) {
          currentValue = numValue;
          calculator.clear();
          calculator.add(currentValue);
        } else {
          if (!performOperation(calculator, numValue, operator)) {
            return null;
          } // if
        } // if

        // Not an operator, a fraction, an integer, negative fraction, register char
      } else {
        System.err.println("Error: Invalid expression.");
        return null;
      } // if
    } // for

    if (numOps != (numVals - 1)) {
      System.err.println("Error: Invalid expression.");
      return null;
    } else {
      return calculator.get();
    } // if
  } // evaluateExpression

  /**
   * Handles the STORE command, storing the last computed value in a register.
   *
   * @param values an array of strings representing the command and register
   * @param calculator the BFCalculator instance to get the last value from
   * @param registers the BFRegisterSet to store the value
   */
  public static void
        handleStoreCommand(String[] values, BFCalculator calculator, BFRegisterSet registers) {
    if (values.length == 2) {
      char register;
      if (values[1].length() == 1) {
        register = values[1].charAt(0);
      } else {
        System.err.println("Error: Register char is not a lowercase letter.");
        return;
      } // else

      if (register >= 'a' && register <= 'z') {
        BigFraction lastValue = calculator.get();
        registers.store(register, lastValue);
        System.out.print("STORED \n");
      } else {
        System.err.println("Error: Register char is not a lowercase letter.");
        return;
      } // else
    } else {
      System.err.println("Error: Incorrect number of parameters.");
      return;
    } // else
  } // handleStoreCommand

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Main method to run the InteractiveCalculator.
   *
   * This method initializes the calculator and handles user input,
   * evaluating expressions or storing values in registers.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    // Initialize the calculator and the register set
    BFCalculator calculator = new BFCalculator();
    BFRegisterSet registers = new BFRegisterSet();

    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);

    pen.print("Enter an expression: ");
    pen.flush();

    while (true) {
      String input = eyes.nextLine();
      String[] values = input.split(" ");

      // Check if the user entered QUIT
      if (input.equals("QUIT")) {
        break;
      } // if

      // Handle STORE command
      if (values[0].equals("STORE")) {
        handleStoreCommand(values, calculator, registers);
      } else {
        // Otherwise, treat it as an arithmetic expression
        BigFraction result = evaluateExpression(values, calculator, registers);
        if (result != null) {
          pen.println(result.toString());
        } // if
      } // else
    } // while
    pen.close();
    eyes.close();
  } // main
} // class InteractiveCalculator
