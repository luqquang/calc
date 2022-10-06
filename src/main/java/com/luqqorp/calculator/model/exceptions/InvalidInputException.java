package com.luqqorp.calculator.model.exceptions;

/**
 * Thrown to indicate that a method is trying to get square root from negative number
 */
public class InvalidInputException extends Exception {

   private static final String MESSAGE = "Invalid input";

   /**
    * Constructs an InvalidInputException.
    */
   public InvalidInputException(){

   }
   /**
    * Message and operation that leads to exception
    * @param expression adjusted operation
    */
   public InvalidInputException(String expression) {
      super(MESSAGE + ": " + expression);
   }
}
