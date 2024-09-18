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
public class BFCalculator {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  BigFraction lastVal;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+
  public BFCalculator(){
    this.lastVal = new BigFraction(0, 1);
  }

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  //gets the last computed value (returns 0 if there is no such value)
  public BigFraction get(){
    return this.lastVal;
  } // get

  //adds val to the last computed value
  public void add(BigFraction val) {
    this.lastVal.add(val);
  } // add
  
  //subtracts val from the last computed value
  public void subtract(BigFraction val) {
    this.lastVal.subtract(val);
  } // subtract

  //multiplies the last computed value by val
  public void multiply(BigFraction val) {
    this.lastVal.multiply(val);
  } // multiply

  //divides the last computed value by val
  public void divide(BigFraction val) {
    this.lastVal.divide(val);
  } // divide

  //resets the last computed value to 0
  public void clear() {
    this.lastVal = new BigFraction(0, 1);
  } // clear
} // class BFCalculator