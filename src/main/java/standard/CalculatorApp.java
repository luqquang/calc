package standard;


import javafx.application.Application;
import javafx.stage.Stage;
import standard.view.CalculatorGraphic;

public class CalculatorApp extends Application {
   private CalculatorGraphic calculatorGraphic = new CalculatorGraphic();

   public static void main(String[] args) {

      //Operating system name
      System.out.println("Your OS name -> " + System.getProperty("os.name"));

      //Operating system version
      System.out.println("Your OS version -> " + System.getProperty("os.version"));

      //Operating system architecture
      System.out.println("Your OS Architecture -> " + System.getProperty("os.arch"));
      launch(args);

   }

   @Override
   public void start(Stage primaryStage) throws Exception {
      primaryStage = calculatorGraphic.calcStage(primaryStage);
      primaryStage.show();
   }
}

