/**
 *  CSC-207-02 (Fall)
 *  Mini-Project 2: Fun with Fractions
 *  Maral Bat-Erdene
 *  2024-09-14
 */

package edu.grinnell.csc207.main;

import java.io.PrintWriter;
//import edu.grinnell.csc207.util.CipherUtils;

/**
 * Main class for handling classical encryption and decryption tasks.
 */
public class QuickCalculator {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /** The number of command-line arguments expected for correct execution. */
  static final int NUM_ARG = 4;

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Calls the appropriate encryption or decryption method based on the given
   * parameters.
   *
   * @param pen    The PrintWriter to output the results.
   * @param action The action to perform, either "-encode" or "-decode".
   * @param cipher The type of cipher to use, either "-caesar" or "-vigenere".
   * @param str    The string to be encrypted or decrypted.
   * @param key    The key to be used for encryption or decryption.
   */
  private static void myCipherCall(PrintWriter pen, String action, String cipher, String str, String key) {
    // Handle Caesar cipher operations
    if (cipher.equals("-caesar")) {
      // Encode using Caesar cipher
      if (action.equals("-encode")) {
        pen.printf("%s\n", CipherUtils.caesarEncrypt(str, key.charAt(0)));
        // Decode using Caesar cipher
      } else if (action.equals("-decode")) {
        pen.printf("%s\n", CipherUtils.caesarDecrypt(str, key.charAt(0)));
      } // if

      // Handle Vigenère cipher operations
    } else if (cipher.equals("-vigenere")) {
      // Encode using Vigenère cipher
      if (action.equals("-encode")) {
        pen.printf("%s\n", CipherUtils.vigenereEncrypt(str, key));
        // Decode using Vigenère cipher
      } else if (action.equals("-decode")) {
        pen.printf("%s\n", CipherUtils.vigenereDecrypt(str, key));
      } // if

      // Print an error message for invalid cipher types or actions
    } else {
      System.err.println("Error: Invalid parameters.");
      return;
    } // else
  } // myCipherCall

  /**
   * Validates the command-line arguments and ensures they are suitable for
   * processing.
   *
   * @param pen    The PrintWriter to output the results.
   * @param action The action to perform, either "-encode" or "-decode".
   * @param cipher The type of cipher to use, either "-caesar" or "-vigenere".
   * @param str    The string to be encrypted or decrypted.
   * @param key    The key to be used for encryption or decryption.
   * @return True if all arguments are valid, otherwise false.
   */
  private static boolean myFourArguments(PrintWriter pen, String action, String cipher, String str, String key) {
    // Check that none of the input parameters are empty
    if (!action.equals("") && !cipher.equals("") && !str.equals("") && !key.equals("")) {
      // Validate that the input string contains only lowercase letters
      for (char c : str.toCharArray()) {
        if (!(c >= 'a' && c <= 'z')) {
          System.err.println("Error: String contains characters other than lowercase letters.\n");
          return false;
        } // if
      } // for

      // Validate that the Caesar cipher key is a single character
      if (cipher.equals("-caesar") && key.length() != 1) {
        System.err.println("Error: Caesar key should be a single character.\n");
        return false;
      } // if

      // Validate that the key string contains only lowercase letters
      for (char c : key.toCharArray()) {
        if (!(c >= 'a' && c <= 'z')) {
          System.err.println("Error: Key contains characters other than lowercase letters.\n");
          return false;
        } // if
      } // for

      // Print the validated parameters for confirmation
      // pen.printf("action: %s, cipher: %s, string: %s, key: %s\n", action, cipher,
      // str, key);
      return true;

      // Handle the case where one or more parameters are empty
    } else {
      System.err.println("Error: Invalid parameters, empty field.");
      return false;
    } // else
  } // myFourArguments

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * The main method processes command-line arguments
   * and calls appropraite encryption or decryption.
   *
   * @param args Command-line arguments.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    String action = "";
    String cipher = "";
    String str = "";
    String key = "";

    // Check if the correct number of arguments is provided
    if (args.length == NUM_ARG) {
      // Process each argument to assign values to action, cipher, str, and key
      for (int i = 0; i < args.length; i++) {
        if ((args[i].equals("-encode")) || (args[i].equals("-decode"))) {
          action = args[i];
        } else if ((args[i].equals("-vigenere")) || (args[i].equals("-caesar"))) {
          cipher = args[i];
        } else if (str.equals("")) {
          str = args[i];
        } else {
          key = args[i];
        } // else
      } // for

      // Validate the arguments and proceed if they are correct
      if (myFourArguments(pen, action, cipher, str, key)) {
        myCipherCall(pen, action, cipher, str, key);
      } // if

      // Handle incorrect number of parameters
    } else {
      System.err.println("Error: Incorrect number of parameters.");
      return;
    } // else

    pen.close();
  } // main
} // class Cipher

// cipher$ mvn compile
// in target classes$ java edu.grinnell.csc207.main.AllCaesar encode
// "helloworld"
