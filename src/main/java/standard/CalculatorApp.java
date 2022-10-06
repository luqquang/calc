package standard;


import javafx.application.Application;
import javafx.stage.Stage;
import standard.view.CalculatorGraphic;

public class CalculatorApp extends Application {
   private final CalculatorGraphic calculatorGraphic = new CalculatorGraphic();

   public static void main(String[] args) {
      launch(args);

   }

   @Override
   public void start(Stage primaryStage) throws Exception {
      primaryStage = calculatorGraphic.calcStage(primaryStage);
      primaryStage.show();
   }
}

