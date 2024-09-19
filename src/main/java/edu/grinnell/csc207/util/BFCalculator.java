/**
 *  CSC-207-02 (Fall)
 *  Mini-Project 2: Fun with Fractions
 *  Maral Bat-Erdene
 *  2024-09-14
 */
package edu.grinnell.csc207.util;

/**
 * A simple calculator for performing arithmetic operations on BigFraction values.
 */
public class BFCalculator {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  /**
   * The last computed BigFraction value.
   */
  private BigFraction lastVal;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+
  /**
   * Initializes the BFCalculator with a last value of 0.
   */
  public BFCalculator() {
    this.lastVal = new BigFraction(0, 1);
  } // BFCalculator

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Gets the last computed value.
   *
   * @return the last computed BigFraction value, or 0 if there is no such value
   */
  public BigFraction get() {
    return this.lastVal;
  } // get

  /**
   * Adds the specified BigFraction value to the last computed value.
   *
   * @param val the BigFraction value to add
   */
  public void add(BigFraction val) {
    this.lastVal = this.lastVal.add(val);
  } // add

  /**
   * Subtracts the specified BigFraction value from the last computed value.
   *
   * @param val the BigFraction value to subtract
   */
  public void subtract(BigFraction val) {
    this.lastVal = this.lastVal.subtract(val);
  } // subtract

  /**
   * Multiplies the last computed value by the specified BigFraction value.
   *
   * @param val the BigFraction value to multiply by
   */
  public void multiply(BigFraction val) {
    this.lastVal = this.lastVal.multiply(val);
  } // multiply

  /**
   * Divides the last computed value by the specified BigFraction value.
   *
   * @param val the BigFraction value to divide by
   */
  public void divide(BigFraction val) {
    this.lastVal = this.lastVal.divide(val);
  } // divide

  /**
   * Resets the last computed value to 0.
   */
  public void clear() {
    this.lastVal = new BigFraction(0);
  } // clear
} // class BFCalculator
