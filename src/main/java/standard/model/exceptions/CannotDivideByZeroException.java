package standard.model.exceptions;

/**
 * Thrown to indicate that a method is trying to divide by zero
 */
public class CannotDivideByZeroException extends Exception {

   private static final String MESSAGE = "Cannot divide by zero";

   /**
    * Constructs an CannotDivideByZeroException.
    */
   public CannotDivideByZeroException(){

   }
   /**
    * Message and operation that leads to exception
    * @param expression adjusted operation
    */
   public CannotDivideByZeroException(String expression) {
      super(MESSAGE + ": " + expression);
   }

}
