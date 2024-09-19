/**
 *  CSC-207-02 (Fall)
 *  Mini-Project 2: Fun with Fractions
 *  Maral Bat-Erdene
 *  2024-09-14
 */

package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * A calculator that processes arithmetic expressions from command-line arguments.
 */
public class QuickCalculator {
  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Main method to run the QuickCalculator.
   *
   * This method initializes the calculator and registers, then processes
   * each expression passed as a command-line argument. It evaluates
   * expressions or stores values in registers based on the command.
   *
   * @param args command-line arguments representing expressions to evaluate
   */
  public static void main(String[] args) {
    // Initialize the calculator and the register set
    BFCalculator calculator = new BFCalculator();
    BFRegisterSet registers = new BFRegisterSet();

    PrintWriter pen = new PrintWriter(System.out, true);

    pen.print("Enter an expression in your command-line: \n");
    pen.flush();

    // Loop through each input expression provided as command-line argument
    for (String input : args) {
      String[] values = input.split(" ");
      // Store the entire expression for output
      String expression = input;

      // Check if the user entered STORE command
      if (values[0].equals("STORE")) {
        pen.printf("%s -> ", expression);
        InteractiveCalculator.handleStoreCommand(values, calculator, registers);
      } else {
        // Otherwise, treat it as an arithmetic expression
        pen.printf("%s ", expression);
        BigFraction result =
                InteractiveCalculator.evaluateExpression(values, calculator, registers);
        if (result != null) {
          pen.printf("-> %s\n", result.toString());
        } // if
      } // if
    } // for
    pen.close();
  } // main
} // class QuickCalculator
