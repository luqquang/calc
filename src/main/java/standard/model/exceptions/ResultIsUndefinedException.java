package standard.model.exceptions;

/**
 * Thrown to indicate that a method is trying divide zero by zero.
 */
public class ResultIsUndefinedException extends Exception {

   private static final String MESSAGE = "Result is undefined";

   /**
    * Constructs an ResultIsUndefinedException.
    */
   public ResultIsUndefinedException() {

   }

   /**
    * Message and operation that leads to exception
    *
    * @param expression adjusted operation
    */
   public ResultIsUndefinedException(String expression) {
      super(MESSAGE + ": " + expression);
   }

}
