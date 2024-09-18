/**
 *  CSC-207-02 (Fall)
 *  Mini-Project 2: Fun with Fractions
 *  Maral Bat-Erdene
 *  2024-09-14
 */
package edu.grinnell.csc207.util;

/**
 * Simple counters.
 *
 */
public class BFRegisterSet {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The ASCII value for the lowercase letter 'a'. */
  static final int LOW_MIN = 'a';

  /** The number of letters in the English alphabet,
   *  used to handle wrapping around the end of the alphabet. */
  static final int ALPHABET_SIZE = 26;

  BigFraction[] regArray;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  public BFRegisterSet(){
    this.regArray = new BigFraction[ALPHABET_SIZE];

    for (int i = 0; i < ALPHABET_SIZE; i++) {
      this.regArray[i] = null;
    } // for
  } // BFRegisterSet

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Converts a lowercase letter to its corresponding integer value.
   *
   * @param letter The lowercase letter to be converted.
   * @return The integer value corresponding to the given letter.
   */
  private static int letter2int(char letter) {
    // Compute the integer value by subtracting the ASCII value of 'a', so 'a' maps to 0
    int intVal = letter - LOW_MIN;
    return (intVal);
  } // letter2int

  //stores the given value in the specified register
  public void store(char register, BigFraction val) {
    regArray[letter2int(register)] = val;
  } // store

  //retrieves the value from the given register
  public BigFraction get(char register) {
    return regArray[letter2int(register)];
  } // get
} // class Counter