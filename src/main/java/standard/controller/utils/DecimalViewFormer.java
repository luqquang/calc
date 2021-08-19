package standard.controller.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static java.math.BigDecimal.*;
import static java.math.MathContext.DECIMAL64;
import static java.math.RoundingMode.HALF_UP;

/**
 * Builds a String value from unit,
 * considering its decimal value
 * Also sets a new unit from input text
 */
@Slf4j
public class DecimalViewFormer {
   /**
    * Maximum input digits for single unit
    */
   private static final int DIGITS_MAX_VALUE = 16;
   /**
    * Minimum length before exponent for number that has zero int part
    */
   private static final int LIMIT_EXP = 18;
   /**
    * Minimum degree of fraction exponent
    */
   private static final int FRACTION_E_START = 4;
   /**
    * DecimalFormat responds for primary formatting and rounding views
    */
   private static final DecimalFormat df = new DecimalFormat();
   /**
    * Shows if current unit has a fraction part
    */
   private boolean hasDot = false;
   @Getter
   String currentDecimalText;


   /**
    * Build a new unit, set its value to current unit
    * and its view based on current unit
    *
    * @param code new digit value
    * @return currentDecimal with new value
    */
   public BigDecimal parseDecimal(BigDecimal decimal, int code, boolean currentValNew) {
      if (code == 10) {
         hasDot = true;
         decimal = decimal.setScale(decimal.scale() + 1, HALF_UP);

      } else if (decimal.compareTo(ZERO) == 0 || currentValNew) {                              //adds digit to new unit
         decimal = valueOf(code);
         hasDot = false;

      } else if (decimal.scale() != 0) {
         decimal = decimal.add(ONE.divide(TEN.pow(decimal.precision()), DECIMAL64).multiply(valueOf(code)));

      } else if (decimal.compareTo(new BigDecimal("999999999999999")) <= 0) {
         decimal = new BigDecimal(decimal.toString() + code);
         hasDot = false;
      }
      return decimal;
   }


   /**
    * Creates a unit from current unit text
    *
    * @return current unit with new value
    */
   private BigDecimal createDecimal() {
      return new BigDecimal(currentDecimalText.replaceAll(",", ""));  //removes thousand separators for new value

   }

   /**
    * Adds dot in currentDecimalText if it hasn't one
    */
   public BigDecimal addDot(BigDecimal decimal) {
      if (!hasDot) {
         decimal = decimal.setScale(1, HALF_UP);                                                 //adds dot to old unit
      }
      hasDot = true;
      return decimal;
   }

   /**
    * Removes last digit or dot from value text of unit
    * and creates new value for current unit
    *
    * @return current unit with new value
    */
   public BigDecimal tryRemoveLast() {
      try {
         removeLast();
      } catch (Exception exception) {
         log.error("Failed to remove last digit...");
      }
      return createDecimal();
   }

   private void removeLast() {
      if (currentDecimalText.length() == 1) {
         currentDecimalText = "0";                                             //sets zero if unit has only 1 digit
      } else {
         currentDecimalText = currentDecimalText.substring(0, currentDecimalText.length() - 1);  //removes last symbol from unit
      }
   }


   /**
    * Formats big decimal into string
    * using pattern 16 fraction digits
    * with thousand separator ',' comma
    *
    * @param decimal adjusted decimal value
    * @return String format
    */
   public String formatInputNumber(BigDecimal decimal) {
      df.setDecimalSeparatorAlwaysShown(hasDot);
      return df.format(decimal).toLowerCase();
   }

   /**
    * Checks if current unit text has dot
    */
   private void dotCheck() {
      hasDot = currentDecimalText.contains(".");
   }

   /**
    * Forms a result text for BigDecimal
    * picks format patterns depending on value
    * and its plain length
    *
    * @param unitValue adjusted decimal value
    * @return String format
    */
   public String formOperateDecimalText(BigDecimal unitValue) {
      df.applyPattern("0.#################");
      String decimalValueText;
      if (exponentCheck(unitValue)) {
         decimalValueText = exponentHandle(unitValue);                                               //picks format for exponent

      } else if (containsZeroInt(unitValue)) {
         decimalValueText = zeroIntValueFormat(unitValue);
      } else {
         decimalValueText = valueFormat(unitValue);
      }
      return decimalValueText;
   }

   /**
    * Sets result format text to current text
    *
    * @param decimal adjusted result unit
    */
   public void formResultDecimalText(BigDecimal decimal) {
      currentDecimalText = formOperateDecimalText(decimal);
   }

   /**
    * Sets maximum fraction digits to 16 and formats decimal value
    *
    * @param decimal adjusted decimal
    * @return String format
    */
   private String zeroIntValueFormat(BigDecimal decimal) {
      df.setMaximumFractionDigits(DIGITS_MAX_VALUE);                          //16 fraction digits for number with zero integer part
      return df.format(decimal);
   }

   /**
    * Sets maximum fraction digit, depending on its plain length and formats decimal value
    *
    * @param decimal adjusted decimal
    * @return String format
    */
   private String valueFormat(BigDecimal decimal) {
      int val = decimal.abs().toBigInteger().toString().length();
      int limitFractionDigits = DIGITS_MAX_VALUE - val;
      df.applyPattern("#,###.################");
      df.setMaximumFractionDigits(limitFractionDigits);
      return df.format(decimal);
   }

   /**
    * Sets half-up rounding mode and formats,
    * depending on plain values of decimal
    *
    * @param decimal adjusted decimal
    * @return String format
    */
   private String exponentHandle(BigDecimal decimal) {
      String text;
      df.setRoundingMode(HALF_UP);
      if (firstExpCheck(decimal.abs().toPlainString())) {
         df.applyPattern("0.#################");                                 //17 fraction digits
         text = df.format(decimal);
      } else {
         df.applyPattern("0.###############E0");                                //15 fraction digits, exponent from e-4 or e+4, from e+16 or e-16
         text = exponentFormat(decimal);
      }
      return text;
   }

   /**
    * Checks if exponent is needed
    * If decimal has zero int, it will count zeros
    * before first non-zero digit and if it counts equals returns true
    * or if non-zero digit is lower than 5 it also returns true
    *
    * @param plainValue adjusted String plain value of decimal
    * @return boolean value shows if exponent is needed
    */
   private boolean firstExpCheck(String plainValue) {
      boolean expCheck = false;
      boolean underFiveExp = false;
      if (plainValue.startsWith("0.")) {
         int a = 0;
         for (int i = 2; i < LIMIT_EXP; i++) {                                // 1 for '0', 1 for '.'
            if (plainValue.charAt(i) == '0') {
               a++;
               int maxZerosBeforeExp = 15;
               expCheck = a == maxZerosBeforeExp;
            } else {
               underFiveExp = isUnderFiveExp(plainValue.charAt(i));
               break;
            }
         }
      }
      return expCheck && (underFiveExp || plainValue.length() <= LIMIT_EXP);
   }

   /**
    * Checks if char represents of int is under 5
    *
    * @param digit adjusted char
    * @return boolean if digit is lower than 5
    */
   private boolean isUnderFiveExp(char digit) {
      return digit == '1' || digit == '2' || digit == '3' || digit == '4';
   }

   /**
    * Shows if value has zero integer value
    *
    * @param value adjusted decimal
    * @return boolean if it has zero integer
    */
   private boolean containsZeroInt(BigDecimal value) {
      return value.abs().toPlainString().startsWith("0.");
   }

   /**
    * Returns true if decimal value is greater then 10,000,000,000,000,000
    * or if decimal value text length is greater the 18 and contains .000
    *
    * @param value adjusted value
    * @return boolean shows if exponent is needed
    */
   private boolean exponentCheck(BigDecimal value) {
      boolean limitValueToExponent = value.abs().compareTo(new BigDecimal("10000000000000000")) >= 0;
      String decimalText = zeroEndsTrim(value.abs().toPlainString());
      boolean periodExponent = decimalText.contains(".") && decimalText.length() > LIMIT_EXP && decimalText.contains(".000");
      return limitValueToExponent || periodExponent;
   }

   /**
    * Format decimal value to string with exponent
    *
    * @param decimal BigDecimal adjusted decimal
    * @return String exponent format
    */
   private String exponentFormat(BigDecimal decimal) {
      String textValue = df.format(decimal);
      int e = textValue.indexOf('E');

      String beforeEValue = zeroEndsTrim(textValue.substring(0, e));
      if (!beforeEValue.contains(".")) {
         beforeEValue += ".";
      }

      String postEValue = textValue.substring(e).toLowerCase();
      if (!postEValue.contains("e-")) {
         postEValue = postEValue.replace("e", "e+");
      }

      String expValue = beforeEValue + postEValue.toLowerCase();
      if ((Integer.valueOf(expValue.substring(expValue.indexOf('e') + 2)) < FRACTION_E_START)) {    //1 for 'e' and 1 for sign
         df.applyPattern("0.###############");
         expValue = df.format(decimal);
      }
      return expValue;
   }

   /**
    * Trim extra zeros in exponent value text
    *
    * @param currentDecimal adjusted string
    * @return string trimmed text
    */
   private String zeroEndsTrim(String currentDecimal) {
      StringBuilder sb = new StringBuilder(currentDecimal);
      if (!currentDecimal.contains("e") && currentDecimal.contains(".")) {
         int maxZeroEnd = 30;
         for (int i = 0; i < maxZeroEnd; i++) {
            if (sb.toString().endsWith("0") && sb.toString().length() > 1) {
               sb.deleteCharAt(sb.toString().length() - 1);
            }
         }
      }
      return sb.toString();
   }

   /**
    * Checks if input number length is under 17
    * for zero integer values and under 16
    * for other numbers
    *
    * @return true if value is in limit
    */
   private boolean limitDigitsCheck() {
      String digits = currentDecimalText.replaceAll(",", "");

      boolean inLimit;
      if (digits.contains("0.")) {
         inLimit = digits.length() < DIGITS_MAX_VALUE + 2;        //17 digits for zero integer part e.g 0.0000000000000001
      } else {
         digits = digits.replace(".", "");
         inLimit = digits.length() < DIGITS_MAX_VALUE;            //16 digits for most numbers e.g 2.000000000000001, 123.4567890123456
      }

      return inLimit;
   }

}
