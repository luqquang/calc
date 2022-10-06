package com.luqqorp.calculator.view.handlers;

import com.luqqorp.calculator.view.listeners.AdditionalBlocksListener;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * This class responds for Window behavior
 * Dragging, closing, minimizing, maximizing,
 * additional options and focus requests
 */
public class WindowOptionHandler {
   /**
    * Adjusted Stage
    */
   private Stage stage;
   /**
    * Adjusted Scene
    */
   private Scene scene;
   /**
    * Adjusted AnchorPane root
    */
   private AnchorPane root;
   /**
    * AdditionalOptionHandler for unfocused
    * option-tabs closing
    */
   private AdditionalOptionHandler additionalOption;
   /**
    * Set content for windows buttons
    */
   private ButtonHandler buttonHandler;
   /**
    * Horizontal point of scene before dragging
    */
   private double xOffset;
   /**
    * Vertical point of scene before dragging
    */
   private double yOffset;
   /**
    * Width of scene before maximizing
    */
   private double lastStageWidth;
   /**
    * Height of scene before maximizing
    */
   private double lastStageHeight;
   /**
    * Horizontal point of scene before maximizing
    */
   private double lastStageX;
   /**
    * Vertical point of scene before maximizing
    */
   private double lastStageY;
   /**
    * Double value of workspace width
    */
   private final double workspaceWidth;
   /**
    * Double value of workspace height
    */
   private final double workspaceHeight;
   /**
    * Screen border size
    */
   private static final int BORDER_SIZE = 5;
   /**
    * Maximize button selector
    */
   private static final String MAXIMIZE_BTN_SELECTOR = "maximize_btn";

   /**
    * Constructs WindowOptionHandler.
    * Adjusts stage, scene and root,
    * sets workspace properties,
    * initializes button handler
    *
    * @param stage Adjusted Stage
    */
   public WindowOptionHandler(Stage stage) {
      this.stage = stage;
      scene = stage.getScene();
      root = (AnchorPane) scene.lookup("#root");
      int taskBarHeight = 35;
      workspaceHeight = Screen.getPrimary().getBounds().getHeight() - taskBarHeight;
      workspaceWidth = Screen.getPrimary().getBounds().getWidth() + BORDER_SIZE;
      buttonHandler = new ButtonHandler(root);
   }


   /**
    * Method calls private methods of: additional option handling,
    * actions for windows buttons, dragging and focus properties
    */
   public void addWindowOptions() {
      additionalOptionsHandle();
      setWindowsButtonActions();
      setMoveAndDragStage();
      addWindowing();
      setFocusProperties();
   }

   /**
    * Method responds for moving a window when
    * mouse drag title-bar. If it minimized and dragged to top border,
    * it will be maximized
    */
   private void setMoveAndDragStage() {
      Node titleBar = root.lookup("#title_bar");

      titleBar.setOnMousePressed(event -> {

         if (event.getSceneX() > BORDER_SIZE && event.getSceneY() > BORDER_SIZE) {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
         }

      });

      titleBar.setOnMouseDragged(event -> {
         minimizeDragHandle();
         stage.setX(event.getScreenX() - xOffset);
         stage.setY(event.getScreenY() - yOffset);
      });

      titleBar.setOnMouseReleased(event -> {

         if (event.getScreenY() < BORDER_SIZE) {
            maximized();
         }

      });
   }

   /**
    * Method responds for dragging a window when it
    * maximized, it will auto-minimized
    */
   private void minimizeDragHandle() {

      if (stage.isMaximized()) {
         minimized();
         setNewLayout();
      }

   }

   /**
    * Sets points when mouse is pressed in title-bar
    */
   private void setNewLayout() {
      xOffset = (xOffset / workspaceWidth) * lastStageWidth;
      yOffset = (yOffset / workspaceHeight) * lastStageHeight;
   }

   /**
    * Saves minimized bounds, maximized the window and overwrite maximize button with minimize
    */
   private void maximized() {
      scene.setCursor(Cursor.DEFAULT);
      setLastPosition();
      String minimizeBtn = "minimize_btn";
      Button maximize = (Button) root.lookup("#" + MAXIMIZE_BTN_SELECTOR);
      stage.setMaximized(true);
      stage.setX(-1.0);
      stage.setY(-1.0);
      stage.setWidth(workspaceWidth);
      stage.setHeight(workspaceHeight);
      buttonHandler.setButtonImg(minimizeBtn, maximize, 10, 10);
   }

   /**
    * Minimized and sets last position value to its bounds,
    * overwrite minimize button with maximize
    */
   private void minimized() {
      stage.setMaximized(false);
      Button maximize = (Button) root.lookup("#" + MAXIMIZE_BTN_SELECTOR);
      stage.setWidth(lastStageWidth);
      stage.setHeight(lastStageHeight);
      buttonHandler.setButtonImg(MAXIMIZE_BTN_SELECTOR, maximize, 10, 10);
   }

   /**
    * Set last bounds to window
    */
   private void setLastPositionToWindow() {
      stage.setX(lastStageX);
      stage.setY(lastStageY);
   }

   /**
    * Maximized or minimized window when title-bar is double-clicked
    */
   private void addWindowing() {
      Button maximize = (Button) root.lookup("#" + MAXIMIZE_BTN_SELECTOR);
      maximize.setOnAction(event -> windowingHandle());
      Pane titleBar = (Pane) root.lookup("#title_bar");
      titleBar.setOnMouseClicked(event -> {

         if (event.getClickCount() == 2) {
            windowingHandle();
         }

      });
   }

   /**
    * Closes additional tabs when its changes maximized value
    */
   private void windowingHandle() {
      if (additionalOption.isHistoryOpen() || additionalOption.isMemoryOpen() || additionalOption.isOptionOpen()) {
         additionalOption.slideHistoryTab();
         additionalOption.slideOptionTab();
         additionalOption.slideMemoryTab();
      }
      if (stage.isMaximized()) {
         setLastPositionToWindow();
         minimized();
      } else {
         maximized();
      }

   }

   /**
    * Saves last bounds of window
    */
   private void setLastPosition() {
      lastStageHeight = stage.getHeight();
      lastStageWidth = stage.getWidth();
      lastStageX = stage.getX();
      lastStageY = stage.getY();
   }


   /**
    * Sets iconify and close buttons
    */
   private void setWindowsButtonActions() {
      Button iconify = (Button) root.lookup("#iconify_btn");
      iconify.setOnAction(event -> stage.setIconified(!stage.isIconified()));
      Button close = (Button) root.lookup("#close_btn");
      close.setOnAction(event -> stage.close());
   }

   /**
    * Sets AdditionalOptionListener to mouse events
    */
   private void additionalOptionsHandle() {
      additionalOption = new AdditionalOptionHandler(stage);
      AdditionalBlocksListener additionalBlocksListener = new AdditionalBlocksListener(stage);
      scene.setOnMouseClicked(additionalBlocksListener);
   }

   /**
    * Changes window border color when it focused
    */
   private void setFocusProperties() {
      stage.focusedProperty().addListener((observable, oldValue, newValue) -> {

         if (newValue) {
            root.setStyle("-fx-border-color: #E8186C");

         } else {
            root.setStyle("-fx-border-color: #A2A2A2");
         }

      });
   }
}
