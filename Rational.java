import java.math.BigInteger;
import java.util.Scanner;

public class Rational {
  private BigInteger numerator;
  private BigInteger denominator;

  public Rational() {
    this(BigInteger.ZERO, BigInteger.ONE);
  }

  public Rational(BigInteger numerator, BigInteger denominator) {
    if (denominator.equals(BigInteger.ZERO)) {
      throw new ArithmeticException("Denominator cannot be zero.");
    }

    BigInteger gcd = numerator.gcd(denominator);
    BigInteger sign = denominator.signum() < 0 ? BigInteger.valueOf(-1) : BigInteger.ONE;
    this.numerator = numerator.multiply(sign).divide(gcd);
    this.denominator = denominator.abs().divide(gcd);
  }

  public BigInteger getNumerator() {
    return numerator;
  }

  public BigInteger getDenominator() {
    return denominator;
  }

  public Rational add(Rational other) {
    BigInteger newNumerator = this.numerator.multiply(other.denominator)
                             .add(this.denominator.multiply(other.numerator));
    BigInteger newDenominator = this.denominator.multiply(other.denominator);
    return new Rational(newNumerator, newDenominator);
  }

  public Rational subtract(Rational other) {
    BigInteger newNumerator = this.numerator.multiply(other.denominator)
                             .subtract(this.denominator.multiply(other.numerator));
    BigInteger newDenominator = this.denominator.multiply(other.denominator);
    return new Rational(newNumerator, newDenominator);
  }

  public Rational multiply(Rational other) {
    BigInteger newNumerator = this.numerator.multiply(other.numerator);
    BigInteger newDenominator = this.denominator.multiply(other.denominator);
    return new Rational(newNumerator, newDenominator);
  }

  public Rational divide(Rational other) {
    if (other.numerator.equals(BigInteger.ZERO)) {
      throw new ArithmeticException("Cannot divide by zero.");
    }
    BigInteger newNumerator = this.numerator.multiply(other.denominator);
    BigInteger newDenominator = this.denominator.multiply(other.numerator);
    return new Rational(newNumerator, newDenominator);
  }

  @Override
  public String toString() {
    if (denominator.equals(BigInteger.ONE)) {
      return numerator.toString();
    }
    return numerator + "/" + denominator;
  }

  public double doubleValue() {
    return new java.math.BigDecimal(numerator)
        .divide(new java.math.BigDecimal(denominator), java.math.MathContext.DECIMAL128)
        .doubleValue();
  }

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
        System.out.println("Enter the first rational number (in the form numerator/denominator): ");
        String[] firstRational = scanner.nextLine().split("/");
        BigInteger num1 = new BigInteger(firstRational[0].trim());
        BigInteger denom1 = new BigInteger(firstRational[1].trim());
        Rational rational1 = new Rational(num1, denom1);
        System.out.println("Enter the second rational number (in the form numerator/denominator): ");
        String[] secondRational = scanner.nextLine().split("/");
        BigInteger num2 = new BigInteger(secondRational[0].trim());
        BigInteger denom2 = new BigInteger(secondRational[1].trim());
        Rational rational2 = new Rational(num2, denom2);
        System.out.println("First Rational Number: " + rational1);
        System.out.println("Second Rational Number: " + rational2);
        Rational sum = rational1.add(rational2);
        System.out.println(rational1 + " + " + rational2 + " = " + sum);
        Rational difference = rational1.subtract(rational2);
        System.out.println(rational1 + " – " + rational2 + " = " + difference);
        Rational product = rational1.multiply(rational2);
        System.out.println(rational1 + " * " + rational2 + " = " + product);
        try {
            Rational quotient = rational1.divide(rational2);
            System.out.println(rational1 + " / " + rational2 + " = " + quotient);
        } catch (Exception e) {
            System.out.println("Division Error: " + e.getMessage());
        }
        System.out.println(rational2 + " is " + rational2.doubleValue());
      }
    }
}
