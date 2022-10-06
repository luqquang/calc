package com.luqqorp.calculator.controller.utils;

import com.luqqorp.calculator.model.data.History;
import com.luqqorp.calculator.model.data.HistoryUnit;
import com.luqqorp.calculator.model.data.Operator;

import java.util.EnumMap;
import java.util.Map;

/**
 * This class used for building the history view
 * in adjusted sequences
 */
public class HistoryViewFormer {

   /**
    * Forms a BigDecimal's value view for history units
    */
   private DecimalViewFormer viewFormer = new DecimalViewFormer();

   /**
    * Concatenates all history units views in history list in one string
    *
    * @param history adjusted History
    * @return String representation of History
    */
   public String getHistoryText(History history) {
      StringBuilder sb = new StringBuilder();
      for (HistoryUnit historyUnit : history.getHistoryUnits()) {
         sb.append(formatHistoryUnit(historyUnit, sb));
      }
      return sb.toString();
   }

   /**
    * Sign-map of operators
    */
   private final Map<Operator, String> signMap = new EnumMap<>(Operator.class);

   /**
    * Constructs HistoryViewFormer and fills sign-map
    */
   public HistoryViewFormer() {
       signMap.put(Operator.ADD, " + ");
       signMap.put(Operator.SUBTRACT, " - ");
       signMap.put(Operator.MULTIPLY, " × ");
       signMap.put(Operator.DIVIDE, " ÷ ");
       signMap.put(Operator.SQUARE, "sqr");
       signMap.put(Operator.ONE_BY_X, "1/");
       signMap.put(Operator.PERCENT, "%");
       signMap.put(Operator.SQRT, "√");
       signMap.put(Operator.NEGATE, "negate");
   }


   /**
    * Forms a single history unit view
    *
    * @param historyUnit adjusted historyUnit from list
    * @return String representation of historyUnit
    */
   private String formatHistoryUnit(HistoryUnit historyUnit, StringBuilder sb) {
      String decimalText = viewFormer.formOperateDecimalText(historyUnit.getValue()).replaceAll(",", "");
      String historyUnitText = decimalText;
      for (Operator nB : historyUnit.getNonBinaries()) {
          if (nB != Operator.PERCENT) {
              sb.append(signMap.get(nB)).append("(").append(historyUnitText).append(")");
          } else {
              sb.append(decimalText);
          }
         historyUnitText = sb.toString();
         sb.setLength(0);
      }
      if (historyUnit.getBinary() != null) {
         historyUnitText += signMap.get(historyUnit.getBinary());
      }
      return historyUnitText;
   }
}
