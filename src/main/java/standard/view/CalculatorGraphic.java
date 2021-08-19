package standard.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import standard.view.handlers.*;
import standard.view.listeners.ResizeListener;

import java.io.IOException;

/**
 * This class manages all of the graphical user interface properties
 */
public class CalculatorGraphic {
   /**
    * Stage instance
    */
   private Stage stage = null;
   /**
    * Scene instance
    */
   private Scene scene = null;
   /**
    * Root anchor-pane
    */
   private AnchorPane root = null;
   /**
    * Assets path in view resources
    */
   public static final String ASSETS_PATH = "assets/";
   /**
    * Minimal and initial stage width
    */
   public static final double MIN_WIDTH = 322;
   /**
    * Minimal and initial stage height
    */
   public static final double MIN_HEIGHT = 501;

   /**
    * Adds style, resize, window options and load button properties into primary stage
    * @param primaryStage adjusted Stage
    * @return Stage primaryStage
    * @throws IOException if an error occurs during loading
    */
   public Stage calcStage(Stage primaryStage) throws IOException {
      setStageProperties(primaryStage);
      addResize();
      addNodeAutoResize();
      addWindowOptions();
      addKeyListener();
      loadButtonProperties();
      return stage;
   }

   /**
    * Sets style and size properties to primary stage
    * @param primaryStage adjusted Stage
    * @throws IOException if an error occurs during loading
    */
   private void setStageProperties(Stage primaryStage) throws IOException {
      root = FXMLLoader.load(getClass().getResource("/standard_calc.fxml"));
      root.setMinHeight(MIN_HEIGHT);
      root.setMinWidth(MIN_WIDTH);
      scene = new Scene(root, MIN_WIDTH, MIN_HEIGHT, Color.TRANSPARENT);
      stage = primaryStage;
      stage.getIcons().add(new Image(ASSETS_PATH + "calculator-icon.png"));
      stage.setTitle("Calculator");
      stage.setMinHeight(MIN_HEIGHT);
      stage.setMinWidth(MIN_WIDTH);
      stage.setScene(scene);
   }

   private void loadButtonProperties() {
      ButtonHandler buttonHandler = new ButtonHandler(root);
      buttonHandler.loadButtonImages();
   }

   private void addWindowOptions() {
      WindowOptionHandler windowOptionHandler = new WindowOptionHandler(stage);
      windowOptionHandler.addWindowOptions();
      root.requestFocus();
   }

   private void addKeyListener() {
      HotkeyHandler hotkeyHandler = new HotkeyHandler(scene);
      hotkeyHandler.addKeyListener();
   }

   private void addNodeAutoResize() {
      AutoResizeNodeHandler resizeNodeHandler = new AutoResizeNodeHandler(stage);
      resizeNodeHandler.setAutoResize();
   }

   private void addResize() {
      ResizeListener listener = new ResizeListener(stage);
      scene.setOnMouseMoved(listener);
      scene.setOnMousePressed(listener);
      scene.setOnMouseDragged(listener);
   }

}