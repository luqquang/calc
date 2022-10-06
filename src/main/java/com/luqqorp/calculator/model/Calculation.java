package com.luqqorp.calculator.model;

import com.luqqorp.calculator.model.data.History;
import com.luqqorp.calculator.model.data.Operator;
import com.luqqorp.calculator.model.exceptions.CannotDivideByZeroException;
import com.luqqorp.calculator.model.exceptions.InvalidInputException;
import com.luqqorp.calculator.model.exceptions.OverflowException;
import com.luqqorp.calculator.model.exceptions.ResultIsUndefinedException;
import com.luqqorp.calculator.model.handler.ExpressionResolver;
import com.luqqorp.calculator.model.handler.HistoryHandler;
import com.luqqorp.calculator.model.handler.MemoryHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

/**
 * Class processes the interactions between computation an expressions,
 * its history and memory.
 */
@Slf4j
public class Calculation {
   /**
    * solver that responds for calculation process
    */
   private ExpressionResolver expressionResolver = new ExpressionResolver();
   /**
    * handler that responds for persisting history
    */
   private HistoryHandler historyHandler = new HistoryHandler();
   /**
    * handler that responds for persisting memory
    */
   private MemoryHandler memoryHandler = new MemoryHandler();

   /**
    * Previous unit in expression
    */
   private BigDecimal prevDecimal = ZERO;
   /**
    * Current unit in expression
    */
   @Getter
   private BigDecimal currentDecimal = ZERO;
   /**
    * Previous operator in expression
    */
   private Operator prevOperator = null;
   /**
    * Current operator in expression
    */
   private Operator currentOperator = null;
   /**
    * Shows if operation is new
    */
   private boolean operationIsNew = true;
   /**
    * Shows if non-binary operation is new
    */
   private boolean nonBinaryIsNew = true;
   /**
    * Shows if non-binary history is new
    */
   private boolean equalsIsNew = false;
   /**
    * Shows if current decimal is returned zero after equals operation
    */
   private boolean zeroPercent = false;
   /**
    * Boolean value that shows if unit is new and in completed form after
    * calculation operation is done. Default value of Unit is zero and no operations
    * have been performed
    */
   @Getter
   private boolean currentValNew = false;


   /**
    * Sets received unit as current decimal
    * Removes last history unit if non-binary operation is not completed
    *
    * @param unit received value
    */
   public void editCurrentDecimal(BigDecimal unit) {
      if (currentValNew) {
         historyHandler.clearEnter(nonBinaryIsNew);
      }
      nonBinaryIsNew = true;
      currentValNew = false;
      currentDecimal = unit;
   }

   /**
    * Handles the operation and its history
    *
    * @param operator name of operation
    * @return current decimal
    */
   public BigDecimal createOperation(Operator operator) throws OverflowException, CannotDivideByZeroException, ResultIsUndefinedException, InvalidInputException {
      setOperation(operator);
      expressionResolver.setValues(currentDecimal);
      if (currentOperator.isBinary && !operationIsNew && (!currentValNew || (!nonBinaryIsNew && equalsIsNew))) {
         binaryResolveHandle();
      } else if (!currentOperator.isBinary) {
         nonBinaryHandle();
      } else {
         historyHandle(operationIsNew);
         binaryOperationDone();
      }
      operationIsNew = false;
      return currentDecimal;
   }

   /**
    * Method calls to get result unit and defines equals operation in calculator
    *
    * @return Decimal with result value
    */
   public BigDecimal getResultDecimal() throws OverflowException, CannotDivideByZeroException, ResultIsUndefinedException, InvalidInputException {
      if (prevOperator != null) {
         expressionResolver.setValues(currentDecimal);
         if (equalsIsNew) {
            prevDecimal = currentDecimal;
         } else {
            expressionResolver.setValues(prevDecimal);
         }
         resolveOperation(prevOperator);
      }
      equalsOperationDone();
      return currentDecimal;
   }

   /**
    * Creates new decimal and set write it to current decimal
    * removes last history unit if non-binary operation isn't completed
    *
    * @return current decimal
    */
   public BigDecimal clearEnter() {
      currentDecimal = ZERO;
      historyHandler.clearEnter(nonBinaryIsNew);
      return currentDecimal;
   }

   /**
    * Method sets a memory value to current
    *
    * @return Decimal current value
    */
   public BigDecimal setCurrentDecimalFromMemory() {
      currentDecimal = memoryHandler.getMemoryDecimal();
      currentValNew = false;
      return currentDecimal;
   }

   /**
    * Method calls ADD operation with current for memory
    *
    * @return Decimal current default value;
    */
   public BigDecimal memoryAdd() {
      return memoryHandler.add(currentDecimal);
   }

   /**
    * Method calls SUBSTRACT operation with current for memory
    *
    * @return Decimal current default value;
    */
   public BigDecimal memorySubstract() {
      return memoryHandler.substract(currentDecimal);
   }

   /**
    * Clears memory
    *
    * @return Decimal memory default value;
    */
   public BigDecimal memoryClear() {
      memoryHandler = new MemoryHandler();
      currentValNew = false;
      return memoryHandler.getMemoryDecimal();
   }

   public History getHistory() {
      return historyHandler.getHistory();
   }

   /**
    * Refresh all fields of this class to default ones, except memory
    *
    * @return Decimal current default value;
    */
   public BigDecimal clearAll() {
      expressionResolver = new ExpressionResolver();
      historyHandler = new HistoryHandler();
      prevDecimal = ZERO;
      currentDecimal = ZERO;
      prevOperator = null;
      currentOperator = null;
      operationIsNew = true;
      nonBinaryIsNew = true;
      equalsIsNew = false;
      zeroPercent = false;
      return currentDecimal;
   }

   /**
    * Method sets to current decimal result value of operation
    *
    * @param operator name of operation
    * @throws CannotDivideByZeroException to indicate that a method is trying to divide by zero
    * @throws ResultIsUndefinedException  to indicate that a method is trying divide zero by zero
    * @throws InvalidInputException       to indicate that a method is trying to get square root from negative number
    * @throws OverflowException           to indicate that a method is reached overflow
    */
   private void resolveOperation(Operator operator) throws OverflowException, CannotDivideByZeroException, ResultIsUndefinedException, InvalidInputException {
      currentDecimal = expressionResolver.resolve(operator, operationIsNew);
   }

   /**
    * Handles percent logic of result and updates its history
    */
   private void percentResolve() throws OverflowException, CannotDivideByZeroException, ResultIsUndefinedException, InvalidInputException {
      if (prevOperator == null || zeroPercent) {
         currentDecimal = ZERO;
         expressionResolver.setValues(currentDecimal);
      } else {
         resolveOperation(currentOperator);
      }
      historyHandle(nonBinaryIsNew);
      nonBinaryNew(false);
   }

   /**
    * Sets current operator
    * @param operator adjusted operation
    */
   private void setOperation(Operator operator) {
      currentOperator = operator;
      historyHandler.setCurrentOperator(currentOperator);
   }

   /**
    * Controls if non-binary is new after operations
    * for handling a history behavior
    * @param nonBinaryIsNew boolean value
    */
   private void nonBinaryNew(boolean nonBinaryIsNew) {
      this.nonBinaryIsNew = nonBinaryIsNew;
      this.currentValNew = true;
   }

   /**
    * Ends binary operation. Sets current operator as a previous,
    * sets equals new to use
    */
   private void binaryOperationDone() {
      prevOperator = currentOperator;
      equalsIsNew = true;
      nonBinaryNew(true);
   }

   /**
    * Ends equals operation. Clears history,
    * sets binary and non-binary operations new to use
    * checks if result is zero for percent to return zero value
    */
   private void equalsOperationDone() {
      operationIsNew = true;
      equalsIsNew = false;
      nonBinaryNew(true);
      clearHistory();
      zeroPercent = currentDecimal.compareTo(ZERO) == 0;
   }

   /**
    * Handles binary operator and if equals operation is completed
    * resolves the result from current expression. Adds a new
    * unit to history
    */
   private void binaryResolveHandle() throws OverflowException, CannotDivideByZeroException, ResultIsUndefinedException, InvalidInputException {
      historyHandler.addHistory(currentDecimal);
      if (equalsIsNew) {
         resolveOperation(prevOperator);
      }
      expressionResolver.setValues(currentDecimal);
      binaryOperationDone();
   }

   /**
    * Handles non-binary currentOperator to resolve the result from current expression,
    * handles operation into history unit depending on operation name:
    * Percent checks if operation goes after zero equals result and sets
    * current decimal zero value, otherwise resolver percent by previous number.
    * handles history by non-binary operation completed status
    * than sets complete status for non-binary operation to false.
    * If negate uses while current decimal is not completed it's not effect the history
    * and non-binary completed status
    */
   private void nonBinaryHandle() throws OverflowException, CannotDivideByZeroException, ResultIsUndefinedException, InvalidInputException {
      if (currentOperator == Operator.PERCENT) {
         percentResolve();
      } else if (currentOperator == Operator.NEGATE && !currentValNew) {
         resolveOperation(currentOperator);
         nonBinaryIsNew = true;
      } else {
         historyHandle(nonBinaryIsNew);
         resolveOperation(currentOperator);
         nonBinaryNew(false);
      }
   }

   /**
    * Handles history, calls add method or change, whether history-unit is new
    *
    * @param newHistoryDecimal boolean value shows if history unit is new
    */
   private void historyHandle(boolean newHistoryDecimal) {
      if (newHistoryDecimal) {
         historyHandler.addHistory(currentDecimal);
      } else {
         historyHandler.changeOperator(currentDecimal);
      }
   }

   /**
    * Clears history
    */
   private void clearHistory() {
      historyHandler = new HistoryHandler();
   }


}
