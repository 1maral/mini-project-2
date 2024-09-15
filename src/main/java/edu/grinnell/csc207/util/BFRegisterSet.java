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
 * Maral Bat-Erdene, Khanh Do
 */
public class BFRegisterSet {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  int starter;
  int count;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  public Counter(){
    this.count = 0;
    this.starter = 0;
  }

  public Counter(int _count) {
    this.starter = _count;
    this.count = _count;
  }
  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+
  public void increment(){
    this.count ++;
  }

  public int get(){
    return this.count;
  }

  public String toString() {
    return String.valueOf(this.count);
  }

  public void reset() {
    this.count = this.starter;
  }

} // class Counter