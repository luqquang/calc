package standard.utils;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.util.*;

public class NodesInformation {
   private Scene scene;
   public static final Map<String, String> buttonCommands = new HashMap<>();
   public static final String HIGHEST_NEGATIVE_NUMBER_EXPRESSION = "10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = sqr × 10 = 1/x ±";
   public static final String LOWEST_POSITIVE_NUMBER_EXPRESSION = "10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = sqr × 10 = 1/x";
   public static final String HIGHEST_NUMBER_EXPRESSION = "10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = sqr ÷ 1000000000000000 = m+ × 1000000000000000 × 10 = 1/x × 9 = 1/x × 10 - mr × 9 + mr = = = =";
   public static final String LOWEST_NUMBER_EXPRESSION = "10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = sqr ÷ 1000000000000000 = m+ × 1000000000000000 × 10 = 1/x × 9 = 1/x × 10 - mr × 9 + mr = = = = ±";
   public NodesInformation(Scene scene) {
      this.scene = scene;
      buttonCommands.put("±", "#btn-negate");
      buttonCommands.put("0","#btn-0");
      buttonCommands.put(".","#btn-dot");
      buttonCommands.put("=","#btn-equals");
      buttonCommands.put("1","#btn-1");
      buttonCommands.put("2","#btn-2");
      buttonCommands.put("3","#btn-3");
      buttonCommands.put("+","#btn-plus");
      buttonCommands.put("4","#btn-4");
      buttonCommands.put("5","#btn-5");
      buttonCommands.put("6","#btn-6");
      buttonCommands.put("-","#btn-minus");
      buttonCommands.put("7","#btn-7");
      buttonCommands.put("8","#btn-8");
      buttonCommands.put("9","#btn-9");
      buttonCommands.put("×","#btn-multiply");
      buttonCommands.put("ce","#btn-ce");
      buttonCommands.put("c","#btn-c");
      buttonCommands.put("←","#btn-undo");
      buttonCommands.put("÷","#btn-divide");
      buttonCommands.put("%","#btn-percent");
      buttonCommands.put("√","#btn-sqrt");
      buttonCommands.put("sqr","#btn-square");
      buttonCommands.put("1/x","#btn-1x");
      buttonCommands.put("mc","#mc");
      buttonCommands.put("mr","#mr");
      buttonCommands.put("m+","#m-plus");
      buttonCommands.put("m-","#m-minus");
   }

   public List<Button> getMainButtons() {
      Set bntCommands = buttonCommands.keySet();
      Iterator iterator = bntCommands.iterator();
      List<Button> mainButtons = new ArrayList<>();
      while (iterator.hasNext()) {
         Button button = (Button) scene.lookup(buttonCommands.get(iterator.next().toString()));
         if(!button.getId().startsWith("m")){
            mainButtons.add(button);
         }
      }
      return mainButtons;
   }

}
