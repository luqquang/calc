package standard.controller.utils;

import standard.model.data.History;
import standard.model.data.HistoryUnit;
import standard.model.data.Operator;

import java.util.EnumMap;
import java.util.Map;

import static standard.model.data.Operator.*;

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
      signMap.put(ADD, " + ");
      signMap.put(SUBTRACT, " - ");
      signMap.put(MULTIPLY, " × ");
      signMap.put(DIVIDE, " ÷ ");
      signMap.put(SQUARE, "sqr");
      signMap.put(ONE_BY_X, "1/");
      signMap.put(PERCENT, "%");
      signMap.put(SQRT, "√");
      signMap.put(NEGATE, "negate");
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
         if (nB != PERCENT) {
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
