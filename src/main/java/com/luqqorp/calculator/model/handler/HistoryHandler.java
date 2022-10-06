package com.luqqorp.calculator.model.handler;

import lombok.Getter;
import lombok.Setter;
import com.luqqorp.calculator.model.data.History;
import com.luqqorp.calculator.model.data.HistoryUnit;
import com.luqqorp.calculator.model.data.Operator;

import java.math.BigDecimal;
import java.util.LinkedList;

import static com.luqqorp.calculator.model.data.Operator.PERCENT;

/**
 * This class handles the sequences of operations in history
 */
public class HistoryHandler {
   /**
    * Returned History instance
    */
   @Getter
   private History history = new History();
   private LinkedList<HistoryUnit> historyUnits = history.getHistoryUnits();
   @Setter
   private Operator currentOperator = null;
   /**
    * Creates a new HistoryUnit with value and currentOperator
    * If currentOperator is binary it checks if the previous unit in history
    * has a binary currentOperator and if it is, adds new unit, otherwise sets currentOperator as binary
    * for previous unit
    * If currentOperator is non-binary and not percent sets non-binary currentOperator
    * for new unit and adds it to the history
    *
    * @param value    BigDecimal unit value
    */
   public void addHistory(BigDecimal value) {
      HistoryUnit historyUnit = new HistoryUnit(value);
      if (currentOperator.isBinary) {
         if (!historyUnits.isEmpty() && historyUnits.getLast().getBinary() == null) {
            historyUnits.getLast().setBinary(currentOperator);
         } else {
            historyUnit.setBinary(currentOperator);
            historyUnits.add(historyUnit);
         }
      } else {
         if (currentOperator != PERCENT) {
            historyUnit.getNonBinaries().add(currentOperator);
         }
         historyUnits.add(historyUnit);
      }
   }


   /**
    * Takes a previous HistoryUnit with value and operators
    * If input currentOperator is binary it sets currentOperator as binary
    * for previous unit
    * If currentOperator is non-binary and not percent adds to non-binary currentOperator
    * list for previous unit
    * If currentOperator is non-binary and percent adds to non-binary currentOperator
    * list for previous unit and overwrite its value with new value
    * for new unit and adds it to the history
    *
    * @param value    BigDecimal unit value
    */
   public void changeOperator(BigDecimal value) {
      if (!history.getHistoryUnits().isEmpty()) {
         HistoryUnit historyUnit = history.getHistoryUnits().getLast();
         if (currentOperator.isBinary) {
            historyUnit.setBinary(currentOperator);
         } else {
            if (currentOperator == PERCENT) {
               historyUnit.setValue(value);
            }
            historyUnit.getNonBinaries().add(currentOperator);
         }
      }
   }

   /**
    * Removes last HistoryUnit from historyList if
    *
    * @param nonBinaryHistoryIsNew indicates if last history unit has non-binary operator not completed
    */
   public void clearEnter(boolean nonBinaryHistoryIsNew) {
      if (!nonBinaryHistoryIsNew && !historyUnits.isEmpty()) {
         historyUnits.pollLast();
      }
   }

}