/**
 *  CSC-207-02 (Fall)
 *  Mini-Project 2: Fun with Fractions
 *  Maral Bat-Erdene
 *  2024-09-14
 */
 package edu.grinnell.csc207.util;

 import java.math.BigInteger;
 
 /**
  * A simple implementation of arbitrary-precision Fractions.
  *
  * @author Samuel A. Rebelsky
  * @author Maral Bat-Erdene, Khanh Do
  */
public class BigFraction {
  // +------------------+---------------------------------------------
  // | Design Decisions |
  // +------------------+

  /*
    * (1) Denominators are always positive. Therefore, negative fractions
    * are represented with a negative numerator. Similarly, if a fraction
    * has a negative numerator, it is negative.
    *
    * (2) Fractions are not necessarily stored in simplified form. To
    * obtain a fraction in simplified form, one must call the `simplify`
    * method.
    */

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /** The default numerator when creating fractions. */
  private static final BigInteger DEFAULT_NUMERATOR = BigInteger.valueOf(2);

  /** The default denominator when creating fractions. */
  private static final BigInteger DEFAULT_DENOMINATOR = BigInteger.valueOf(7);

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   * @return 
   */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    this.num = numerator;
    this.denom = denominator;
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   *
   * Warning! Not yet stable.
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   * @return 
   */
  public BigFraction(int numerator, int denominator) {
    this.num = BigInteger.valueOf(numerator);
    this.denom = BigInteger.valueOf(denominator);
  } // BigFraction(int, int)

  /**
   * Build a new fraction by parsing a string.
   *
   * Warning! Not yet implemented.
   *
   * @param str
   *   The fraction in string form
   * @return 
   */
  public BigFraction(String str) {
    int indx = str.indexOf('/');
    this.num = BigInteger.valueOf(Integer.valueOf(str.substring(0, indx)));
    this.denom = BigInteger.valueOf(Integer.valueOf(str.substring(indx+1, str.length())));

  } // BigFraction

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Express this fraction as a double.
   *
   * @return the fraction approxiamted as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * Add another faction to this fraction.
   *
   * @param addend
   *   The fraction to add.
   *
   * @return the result of the addition.
   */
  public BigFraction add(BigFraction addend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(addend.denom);
    // The numerator is more complicated
    resultNumerator =
      (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)

  /**
   * Subtract another faction from this fraction.
   *
   * @param subend
   *   The fraction to add.
   *
   * @return the result of the subtraction.
   */
  public BigFraction subtract(BigFraction subend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(subend.denom);
    // The numerator is more complicated
    resultNumerator =
      (this.num.multiply(subend.denom)).subtract(subend.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // subtract(BigFraction)

  /**
   * Multiply another fraction to this fraction.
   *
   * @param frac
   *   The fraction to multiply.
   *
   * @return the result of the multiplication.
   */
  public BigFraction multiply(BigFraction frac) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and num's denominator
    resultDenominator = this.denom.multiply(frac.denom);
    // The numerator is more complicated
    resultNumerator = this.num.multiply(frac.num);

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // multiply(BigFraction)

  /**
   * Divide this fraction by another fraction.
   *
   * @param frac
   *   The fraction to divide.
   *
   * @return the result of the division.
   */
  public BigFraction divide(BigFraction frac) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and frac's numerator
    resultDenominator = this.denom.multiply(frac.num);
    // The numerator of the result is the product of this object's
    // numerator and frac's denominator
    resultNumerator = this.num.multiply(frac.denom);

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // divide(BigFraction)

  /**
   * Get the denominator of this fraction.
   *
   * @return the denominator
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   *
   * @return the numerator
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  /**
   * Convert this fraction to a string for ease of printing.
   *
   * @return a string that represents the fraction.
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if it's zero

    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return this.num + "/" + this.denom;
  } // toString()

  /**
   * Return fractional part.
   * @num numerator - whole number
   * @denum denumerator - whole number
   * @return a BigFraction that represents the fraction.
   */
  public static BigFraction fractional(int num, int denum) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    resultNumerator = BigInteger.valueOf(num % denum);
    resultDenominator = BigInteger.valueOf(denum);
    return new BigFraction(resultNumerator, resultDenominator);
  } // fractional(int, int)

  /**
   * Return whole part of the fraction.
   * @num numerator - whole number
   * @denum denumerator - whole number
   * @return a BigInteger that represents the whole part.
   */
  public static BigInteger whole(int num, int denum) {
    BigInteger result;

    result = BigInteger.valueOf((int)(num / denum));

    return result;
  } // fractional(int, int)
} // class BigFraction