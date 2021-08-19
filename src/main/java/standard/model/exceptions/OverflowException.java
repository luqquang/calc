package standard.model.exceptions;

/**
 * Thrown to indicate that a method is reached overflow
 */
public class OverflowException extends Exception {

   private static final String MESSAGE = "Overflow";

   /**
    * Constructs an OverflowException.
    */
   public OverflowException(){
   }

   /**
    * Message and operation that leads to exception
    * @param expression adjusted operation
    */
   public OverflowException(String expression){
      super(MESSAGE + ": " + expression);
   }
}
