/**
 *  CSC-207-02 (Fall)
 *  Mini-Project 2: Fun with Fractions
 *  Maral Bat-Erdene
 *  2024-09-14
 */
package edu.grinnell.csc207.util;

/**
 * A class representing a set of registers for storing BigFraction values.
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

  /**
   * An array of BigFraction objects representing the registers.
   * Each index corresponds to a register (0 for 'a', 1 for 'b', etc.).
   */
  private BigFraction[] regArray;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructs a new BFRegisterSet.
   *
   * This constructor initializes an array of registers to store
   * BigFraction values. The size of the array is determined by the
   * constant ALPHABET_SIZE, representing the number of registers available
   *
   * Each register is initially set to null to indicate that it is empty.
   */
  public BFRegisterSet() {
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

  /**
   * Stores the given value in the specified register.
   *
   * This method takes a character representing the register (e.g., 'a', 'b', etc.)
   * and stores the provided BigFraction value in the corresponding index of the register array.
   *
   * @param register the character representing the register (must be a lowercase letter)
   * @param val the BigFraction value to be stored in the register
   * @throws IllegalArgumentException if the register character is invalid (not a lowercase letter)
   */
  public void store(char register, BigFraction val) {
    regArray[letter2int(register)] = val;
  } // store

  /**
   * Retrieves the value from the given register.
   *
   * This method takes a character representing the register (e.g., 'a', 'b', etc.)
   * and returns the BigFraction value stored in the corresponding index of the register array.
   *
   * @param register the character representing the register (must be a lowercase letter)
   * @return the BigFraction value stored in the specified register, or null if not set
   * @throws IllegalArgumentException if the register character is invalid (not a lowercase letter)
   */
  public BigFraction get(char register) {
    return regArray[letter2int(register)];
  } // get
} // class Counter
