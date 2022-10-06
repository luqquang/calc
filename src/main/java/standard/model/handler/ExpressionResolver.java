package standard.model.handler;

import standard.model.data.Operator;
import standard.model.exceptions.CannotDivideByZeroException;
import standard.model.exceptions.InvalidInputException;
import standard.model.exceptions.OverflowException;
import standard.model.exceptions.ResultIsUndefinedException;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_UP;
import static standard.model.data.Operator.*;

/**
 * This class presents a whole calculation process.
 * It allocates all expressions into two BigDecimal values
 * depending on its logic and assumes the result value
 * Supported operations: add, subtract, multiply, divide,
 * square, sqrt, fraction, percent and negate.
 */
public class ExpressionResolver {
    /**
     * The maximum power value that supports by calculator
     */
    /**
     * The scale value that supports by calculator
     */
    private static final int SCALE = 20050;
    private static final BigDecimal MAX_SUPPORTED_VALUE = new BigDecimal("9.9999999999999995e9999");
    private static final BigDecimal MIN_SUPPORTED_VALUE = new BigDecimal("1e-9999");
    private static final BigDecimal ZERO_ROUND_LIMIT = new BigDecimal("1e-20000");

    /**
     * The BigDecimal value of the first operand in expression
     */
    private BigDecimal firstVal;
    /**
     * The BigDecimal value of the second operand in expression
     */
    private BigDecimal secondVal;
    /**
     * The BigDecimal value of returned result of expression
     */
    private BigDecimal result = ZERO;

    /**
     * Set value for two of operands
     * first value of new expression sets for both operands
     *
     * @param decimal Followed by the new decimal, the first operand will take the value of the second,
     *                and the second will take the new one
     */
    public void setValues(BigDecimal decimal) {
        if (firstVal == null) {
            firstVal = decimal;
        } else {
            firstVal = secondVal;
        }
        secondVal = decimal;
    }

    /**
     * Set value for two of operands, resolves the given operation
     */
    public BigDecimal calculate(BigDecimal firstVal, BigDecimal secondVal, Operator operator) throws InvalidInputException, CannotDivideByZeroException, ResultIsUndefinedException, OverflowException {
        this.firstVal = firstVal;
        this.secondVal = secondVal;
        return resolve(operator, true);
    }

    /**
     * Handles expression depending on operation
     * and consider if operation is new for non-binary operations
     * for next usages of result in same expression
     *
     * @param operator Name of operation
     * @param isNew    boolean determines if a result value need to in set in second operand
     * @return result Unit with result value after operation
     * @throws CannotDivideByZeroException to indicate that a method is trying to divide by zero
     * @throws ResultIsUndefinedException  to indicate that a method is trying divide zero by zero
     * @throws InvalidInputException       to indicate that a method is trying to get square root from negative number
     * @throws OverflowException           to indicate that a method is reached overflow
     */
    public BigDecimal resolve(Operator operator, boolean isNew) throws CannotDivideByZeroException, ResultIsUndefinedException, InvalidInputException, OverflowException {
        if (operator == ADD) {
            add();
        } else if (operator == SUBTRACT) {
            subtract();

        } else if (operator == MULTIPLY) {
            multiply();

        } else if (operator == DIVIDE) {
            divide();

        } else if (operator == SQUARE) {
            square(isNew);

        } else if (operator == SQRT) {
            sqrt(isNew);

        } else if (operator == ONE_BY_X) {
            oneByX(isNew);

        } else if (operator == PERCENT) {
            percent(isNew);

        } else if (operator == NEGATE) {
            negate(isNew);
        }
        zeroRounding();
        overflowCheck(operator);
        return result;
    }

    /**
     * This method resolves add operation
     */
    private void add() {
        result = firstVal.add(secondVal);
    }

    /**
     * This method resolves substract operation
     */
    private void subtract() {
        result = firstVal.subtract(secondVal);
    }

    /**
     * This method resolves multiply operation
     */
    private void multiply() {
        result = firstVal.multiply(secondVal);
    }

    /**
     * This method resolves divide operation
     *
     * @throws ResultIsUndefinedException  to indicate that a method is trying divide zero by zero
     * @throws CannotDivideByZeroException to indicate that a method is trying to divide by zero
     */
    private void divide() throws ResultIsUndefinedException, CannotDivideByZeroException {
        if (equalsZero(firstVal) && equalsZero(secondVal)) {
            throw new ResultIsUndefinedException(exceptionMessage(DIVIDE));
        } else if (equalsZero(secondVal)) {
            throw new CannotDivideByZeroException(exceptionMessage(DIVIDE));
        } else {
            result = firstVal.divide(secondVal, SCALE, HALF_UP);
        }
    }

    /**
     * This method resolves square operation
     *
     * @param isNew boolean value to decide how to manage operand
     */
    private void square(boolean isNew) {
        result = secondVal.multiply(secondVal);
        operandControl(isNew);
    }

    /**
     * This method resolves fraction operation
     *
     * @param isNew boolean value to decide how to manage operand
     * @throws CannotDivideByZeroException to indicate that a method is trying to divide by zero
     */
    private void oneByX(boolean isNew) throws CannotDivideByZeroException {
        if (equalsZero(secondVal)) {
            throw new CannotDivideByZeroException(exceptionMessage(ONE_BY_X));
        } else {
            result = ONE.divide(secondVal, SCALE, HALF_UP);
            operandControl(isNew);
        }
    }

    /**
     * This method sqrt fraction operation
     *
     * @param isNew boolean value to decide how to manage operand
     * @throws InvalidInputException to indicate that a method is trying to get square root from negative number
     */
    private void sqrt(boolean isNew) throws InvalidInputException {
        if (secondVal.compareTo(ZERO) < 0) {
            throw new InvalidInputException(exceptionMessage(SQRT));
        } else {
            result = sqrt(secondVal);
            operandControl(isNew);
        }
    }


    /**
     * Returns the square root calculated for the given {@link BigDecimal} number. Source of square root algorithm for {@link BigDecimal} is
     * <a href="https://www.java-forums.org/advanced-java/44345-square-rooting-bigdecimal.html">square rooting a BigDecimal</a>
     *
     * @return the square root calculated for the given {@link BigDecimal} number
     * @throws InvalidInputException if the given {@link BigDecimal} number is negative
     */
    private BigDecimal sqrt(BigDecimal value) throws InvalidInputException {
        if (value.compareTo(ZERO) < 0) {
            throw new InvalidInputException();
        }

        if (value.compareTo(ZERO) == 0) {
            return ZERO;
        }

        // n = x*(10^(2*scale))
        BigInteger n = value.movePointRight(SCALE << 1).toBigInteger();

        // The first approximation is the upper half of n.
        int bits = (n.bitLength() + 1) >> 1;
        BigInteger ix = n.shiftRight(bits);
        BigInteger ixPrev;

        // Loop until the approximations converge
        // (two successive approximations are equal after rounding).
        do {
            ixPrev = ix;

            // x = (x + n/x)/2
            ix = ix.add(n.divide(ix)).shiftRight(1);

            Thread.yield();
        } while (ix.compareTo(ixPrev) != 0);

        return new BigDecimal(ix, SCALE);
    }

    /**
     * This method resolves negate operation
     *
     * @param isNew boolean value to decide how to manage operand
     */
    private void negate(boolean isNew) {
        result = secondVal.negate();
        operandControl(isNew);
    }

    /**
     * Method calculates percent of values in expression
     * If operation is new it calculates percentage of itself and sets
     * the result in first operand
     *
     * @param isNew indicates which value to use for operation and which value to set after
     */
    private void percent(boolean isNew) {
        if (isNew) {
            percentCalculation(secondVal, secondVal);
            firstVal = result;
        } else {
            percentCalculation(firstVal, secondVal);
            secondVal = firstVal;
        }
    }

    private void percentCalculation(BigDecimal val1, BigDecimal val2) {
        result = (val1.multiply(val2)).divide(new BigDecimal("100"), DECIMAL128);
    }

    /**
     * Method controls operands in expression
     * If operation is new it rewrites second operand with the result
     * If operation isn't new it rewrites second operand with first operand
     *
     * @param isNew indicates if operation is new.
     */
    private void operandControl(boolean isNew) {
        if (isNew) {
            secondVal = result;
        } else {
            secondVal = firstVal;
        }
    }

    /**
     * Counts an exponent degree
     *
     * @throws OverflowException to indicate that a method is reached greater than 9999
     */
    private void overflowCheck(Operator operator) throws OverflowException {
        if (result.compareTo(ZERO) != 0 && (result.abs().compareTo(MAX_SUPPORTED_VALUE) >= 0 || result.abs().compareTo(MIN_SUPPORTED_VALUE) < 0)) {
            throw new OverflowException(exceptionMessage(operator));
        }
    }

    /**
     * Counts zeros in plain value of BigDecimal and rounds result if number of values equals number of PERIOD_TAIL
     * Rounds a result value to zero
     */
    private void zeroRounding() {
        if (result.abs().compareTo(ZERO_ROUND_LIMIT) < 0) {
            result = ZERO;
        }
    }

    private String exceptionMessage(Operator operator) {
        return firstVal + " " + operator + " " + secondVal;
    }

    private boolean equalsZero(BigDecimal decimal) {
        return decimal.compareTo(ZERO) == 0;
    }
}
