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
  private BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  private BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
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
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   * @return
   */
  public BigFraction(int numerator, int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("Denominator cannot be zero.");
    } // if
    this.num = BigInteger.valueOf(numerator);
    this.denom = BigInteger.valueOf(denominator);
  } // BigFraction(int, int)

  /**
   * Build a new fraction from a whole number with 1 as a denominator.
   *
   * @param wholeNumber
   *   The whole numerator of the fraction.
   * @return
   */
  public BigFraction(int wholeNumber) {
    this.num = BigInteger.valueOf(wholeNumber);
    this.denom = BigInteger.ONE;
  } // BigFraction(int, int)

  /**
   * Build a new fraction by parsing a string.
   *
   * @param str
   *   The fraction in string form
   * @return
   */
  public BigFraction(String str) {
    // Check if the input string contains a '/'
    int indx = str.indexOf('/');
    if (indx == -1) {
      // If there's no '/', treat the string as a whole number
      this.num = BigInteger.valueOf(Integer.valueOf(str));
      this.denom = BigInteger.ONE; // Set denominator to 1
    } else {
      // If there is a '/', parse the numerator and denominator
      this.num = BigInteger.valueOf(Integer.valueOf(str.substring(0, indx)));
      this.denom = BigInteger.valueOf(Integer.valueOf(str.substring(indx + 1)));

      // Check for zero denominator
      if (this.denom.equals(BigInteger.ZERO)) {
        throw new IllegalArgumentException("Denominator cannot be zero.");
      } // if
    } // if
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
    resultNumerator = (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));

    // Return the computed value
    return (new BigFraction(resultNumerator, resultDenominator)).simplify();
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
    return (new BigFraction(resultNumerator, resultDenominator)).simplify();
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
    return (new BigFraction(resultNumerator, resultDenominator)).simplify();
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
    return (new BigFraction(resultNumerator, resultDenominator)).simplify();
  } // divide(BigFraction)

  /**
   * Get the denominator of this fraction.
   *
   * @return the denominator
   */
  public BigInteger denominator() {
    return this.simplify().denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   *
   * @return the numerator
   */
  public BigInteger numerator() {
    return this.simplify().num;
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

    // Simplify the fraction first
    BigFraction simplified = this.simplify();

    // Check if there is no fractional part (i.e., it is a whole number)
    if (fractionalPart(simplified.num.intValue(), simplified.denom.intValue())
            .numerator().equals(BigInteger.ZERO)) {
      return simplified.num.toString(0); // Return just the whole part
    } else {
      // Return the simplified fraction
      return simplified.num + "/" + simplified.denom;
    } // if
  } // toString()

/**
 * Simplifies the fraction to its lowest terms.
 *
 * This method calculates the greatest common divisor (GCD) of the numerator
 * and denominator, then divides both by this value to produce a simplified
 * fraction. It ensures that the denominator remains positive, negating the
 * numerator if necessary.
 *
 * @return a new BigFraction that represents the simplified version of this fraction.
 * @throws IllegalArgumentException if the current fraction is invalid
 */
  public BigFraction simplify() {
    BigInteger gcd = (this.num).gcd(this.denom); // Get the GCD of numerator and denominator

    // Divide both numerator and denominator by their GCD
    BigInteger simplifiedNum = this.num.divide(gcd);
    BigInteger simplifiedDenom = this.denom.divide(gcd);

    // Ensure the denominator is positive
    if (simplifiedDenom.intValue() < 0) {
      simplifiedNum = simplifiedNum.negate();
      simplifiedDenom = simplifiedDenom.negate();
    } // if
    return new BigFraction(simplifiedNum, simplifiedDenom);
  } // simplify

/**
 * Calculates the fractional part of a given fraction.
 *
 * @param num the numerator (whole number)
 * @param denom the denominator (whole number, must not be zero)
 * @return a BigFraction representing the fractional part of the fraction
 */
  public static BigFraction fractionalPart(int num, int denom) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    resultNumerator = BigInteger.valueOf(num % denom);
    resultDenominator = BigInteger.valueOf(denom);
    return new BigFraction(resultNumerator, resultDenominator);
  } // fractional(int, int)

  /**
   * Calculates the whole part of a fraction.
   *
   * @param num the numerator (whole number)
   * @param denom the denominator (whole number)
   * @return a BigInteger representing the whole part of the fraction
   */
  public static BigInteger wholePart(int num, int denom) {
    BigInteger result;

    result = BigInteger.valueOf((int) (num / denom));
    return result;
  } // fractional(int, int)

  /**
   * Checks if the given string represents an integer.
   *
   * @param str the string to check
   * @return true if the string can be parsed as an integer; false otherwise
   */
  public static boolean isInteger(String str) {
    try {
      Integer.valueOf(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    } // try
  } // isInteger

  /**
   * Checks if the given string represents a fraction in the format "numerator/denominator".
   *
   * This method verifies that the string contains exactly one '/' character,
   * splits the string into two parts, and checks if both parts can be parsed as integers.
   *
   * @param str the string to check
   * @return true if the string represents a valid fraction; false otherwise
   */
  public static boolean isFraction(String str) {
    if (str.contains("/")) {
      String[] parts = str.split("/");
      if (parts.length == 2) {
        try {
          Integer.valueOf(parts[0]);
          Integer.valueOf(parts[1]);
          return true;
        } catch (NumberFormatException e) {
          return false;
        } // try
      } // if
    } // if
    return false;
  } // isFraction
} // class BigFraction
