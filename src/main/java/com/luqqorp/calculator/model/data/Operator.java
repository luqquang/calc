package com.luqqorp.calculator.model.data;

/**
 * This enum lists operations
 */
public enum Operator {
   ADD(true),
   SUBTRACT(true),
   MULTIPLY(true),
   DIVIDE(true),
   SQUARE(false),
   ONE_BY_X(false),
   PERCENT(false),
   SQRT(false),
   NEGATE(false);

   /**
    * Boolean value shows if operation is arithmetic binary
    */
   public final boolean isBinary;

   Operator(boolean isBinary) {
      this.isBinary = isBinary;
   }
}
