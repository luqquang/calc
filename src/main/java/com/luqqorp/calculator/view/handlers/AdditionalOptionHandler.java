package com.luqqorp.calculator.view.handlers;

import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;

/**
 * This class responds for display logic of popup tabs,
 * such as history-tab, memory-tab and option-tab.
 * Handles animation of slide-in and slide-out
 */
public class AdditionalOptionHandler {
   /**
    * Adjusted Scene
    */
   private Scene scene;
   /**
    * Popup history AnchorPane
    */
   @Getter
   private AnchorPane historyTab;
   /**
    * Popup option AnchorPane
    */
   @Getter
   private AnchorPane optionTab;
   /**
    * Popup memory AnchorPane
    */
   @Getter
   private AnchorPane memoryTab;
   /**
    * History slide-in/slide-out button
    */
   private Button clockBtn;
   /**
    * Memory slide-in/slide-out button
    */
   private Button memoryBtn;
   /**
    * Option slide-in button
    */
   private Button optBtn;
   /**
    * Option slide-out button
    */
   private Button optBtn2;
   /**
    * Translate transition for Y value of vertical slides
    */
   private static final int TRASNLATE_TRANSITION = -5;
   /**
    * Animation time in milliseconds
    */
   private static final int ANIMATION_TIME_MILLIS = 300;
   /**
    * History AnchorPane selector
    */
   private static final String HISTORY_TAB_SELECTOR = "#history-tab";
   /**
    * Option AnchorPane selector
    */
   private static final String OPTION_TAB_SELECTOR = "#option-tab";
   /**
    * Memory AnchorPane selector
    */
   private static final String MEMORY_TAB_SELECTOR = "#memory-tab";
   /**
    * Shows if history-tab is open
    */
   @Getter
   private boolean historyOpen = false;
   /**
    * Shows if option-tab is open
    */
   @Getter
   private boolean optionOpen = false;
   /**
    * Shows if memory-tab is open
    */
   @Getter
   private boolean memoryOpen = false;

   /**
    * Constructs an AdditionalOptionHandler instance
    * and initializes scene and nodes of additional options
    * @param stage adjusted Stage
    */
   public AdditionalOptionHandler(Stage stage) {
      scene = stage.getScene();
      historyTab = (AnchorPane) scene.lookup(HISTORY_TAB_SELECTOR);
      optionTab = (AnchorPane) scene.lookup(OPTION_TAB_SELECTOR);
      memoryTab = (AnchorPane) scene.lookup(MEMORY_TAB_SELECTOR);
      clockBtn = (Button) scene.lookup("#clock_btn");
      optBtn = (Button) scene.lookup("#opt_btn");
      optBtn2 = (Button) scene.lookup("#opt_btn2");
      memoryBtn = (Button) scene.lookup("#m");
      optionBlock();
      historyBlock();
      memoryBlock();
   }


   /**
    * Slide-out option-tab
    */
   public void slideOptionTab() {
      TranslateTransition closeNav = createTransition(optionTab);
      closeNav.setToX(-optionTab.getWidth());
      closeNav.play();
      optionOpen = false;
   }

   /**
    * Slide-out history-tab
    */
   public void slideHistoryTab() {
      TranslateTransition closeNav = createTransition(historyTab);
      closeNav.setToY(scene.getHeight());
      closeNav.play();
      historyOpen = false;
   }

   /**
    * Slide-out memory-tab
    */
   public void slideMemoryTab() {
      TranslateTransition closeNav = createTransition(memoryTab);
      closeNav.setToY(scene.getHeight());
      closeNav.play();
      memoryOpen = false;
   }

   /**
    * Handles sliding of option-tab,
    * e.g. sets action for option button
    * if memory or history tabs are open, it closes both,
    * if both of them are closed, it slides-in option-tab
    */
   private void optionBlock() {
      optBtn.setOnAction(event -> {
         if (memoryOpen) {
            slideMemoryTab();
         } else if (historyOpen) {
            slideHistoryTab();
         } else {
            openOptionTab();
         }
      });
      optBtn2.setOnAction(event -> slideOptionTab());
   }

   /**
    * Handles sliding of option-tab,
    * e.g. sets action for option button
    * if memory or option tabs are open, it closes both,
    * if both of them are closed, it slides-in history-tab
    */
   private void historyBlock() {
      clockBtn.setOnAction(event -> {
         if (memoryOpen) {
            slideMemoryTab();
         } else if (optionOpen) {
            slideOptionTab();
         } else if (!historyOpen) {
            openHistoryTab();
         } else {
            slideHistoryTab();
         }
      });
   }

   /**
    * Handles sliding of option-tab,
    * e.g. sets action for option button
    * if option or history tabs are open, it closes both,
    * if both of them are closed, it slides-in memory-tab
    */
   private void memoryBlock() {
      memoryBtn.setOnAction(event -> {
         if (historyOpen) {
            slideHistoryTab();
         } else if (optionOpen) {
            slideOptionTab();
         } else if (!memoryOpen) {
            openMemoryTab();
         } else {
            slideMemoryTab();
         }
      });
   }

   /**
    * Creates an animation of sliding for adjusted tab
    * @param tab adjusted tab
    * @return TranslateTransition an instance of slide-animation
    */
   private TranslateTransition createTransition(AnchorPane tab) {
      return new TranslateTransition(new Duration(ANIMATION_TIME_MILLIS), tab);
   }

   /**
    * Slide-in option-tab
    */
   private void openOptionTab() {
      TranslateTransition openNav = createTransition(optionTab);
      openNav.setToX(0);
      openNav.play();
      optionOpen = true;
   }

   /**
    * Slide-in history-tab
    */
   private void openHistoryTab() {
      TranslateTransition openNav = createTransition(historyTab);
      openNav.setToY(TRASNLATE_TRANSITION);
      openNav.play();
      historyOpen = true;
   }

   /**
    * Slide-in memory-tab
    */
   private void openMemoryTab() {
      TranslateTransition openNav = createTransition(memoryTab);
      openNav.setToY(TRASNLATE_TRANSITION);
      openNav.play();
      memoryOpen = true;
   }

}