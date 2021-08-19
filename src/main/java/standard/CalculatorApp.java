package standard;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import standard.view.CalculatorGraphic;

public class CalculatorApp extends Application {
   private CalculatorGraphic calculatorGraphic = new CalculatorGraphic();

   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage) throws Exception {
      primaryStage = calculatorGraphic.calcStage(primaryStage);
      primaryStage.initStyle(StageStyle.UNDECORATED);
      primaryStage.show();
   }
}

