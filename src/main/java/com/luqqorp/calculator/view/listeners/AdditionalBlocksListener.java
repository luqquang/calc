package com.luqqorp.calculator.view.listeners;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.luqqorp.calculator.view.handlers.AdditionalOptionHandler;

/**
 * Class is Listener for AdditionalBlocks, handles sliding
 */
public class AdditionalBlocksListener implements EventHandler<MouseEvent> {
   /**
    * AdditionalOptionHandler controls animation of sliding
    * and its logic
    */
   private AdditionalOptionHandler optionHandler;

   /**
    * Constructs an instance of class
    * and initializes stage for option handler
    *
    * @param stage adjusted Stage
    */
   public AdditionalBlocksListener(Stage stage) {
      optionHandler = new AdditionalOptionHandler(stage);
   }

   /**
    * If one of additional option tabs
    * and mouse clicked outside that tab
    * is slides-out that tab
    *
    * @param event MouseEvent for click
    */
   @Override
   public void handle(MouseEvent event) {
      if (optionHandler.isOptionOpen() && event.getSceneX() > optionHandler.getOptionTab().getLayoutX()) {
         optionHandler.slideOptionTab();
      } else if (optionHandler.isHistoryOpen() && event.getSceneY() < optionHandler.getOptionTab().getLayoutY()) {
         optionHandler.slideHistoryTab();
      } else if (optionHandler.isMemoryOpen() && event.getSceneY() < optionHandler.getMemoryTab().getLayoutY()) {
         optionHandler.slideMemoryTab();
      }
   }
}
