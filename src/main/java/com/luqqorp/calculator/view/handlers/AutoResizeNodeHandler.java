package com.luqqorp.calculator.view.handlers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javafx.scene.text.Font.font;
import static javafx.scene.text.FontPosture.ITALIC;

/**
 * This class responds for auto-resize of nodes and its content
 * in adjusted Stage
 */
@RequiredArgsConstructor
public class AutoResizeNodeHandler {
   /**
    * Adjusted instance of Scene
    */
   private Scene scene;
   /**
    * Adjusted instance of VBox
    */
   private VBox rootVBox;
   /**
    * Adjusted instance of AnchorPane root
    */
   private AnchorPane root;
   /**
    * Initial instance of DoubleProperty
    * for main-label font-size property
    */
   private static final DoubleProperty FONT_SIZE_PROPERTY = new SimpleDoubleProperty(0);
   /**
    * Initial main-label font resize
    */
   private static final int FONT_SIZE = 45;
   /**
    * Minimal length for main-label font resize
    */
   private static final int START_FONT_RESIZE_LENGTH = 12;
   /**
    * Minimal width for main-label font resize
    */
   private static final int END_FONT_RESIZE_WIDTH = 494;
   /**
    * Minimal height for main-label font resize
    */
   private static final int END_FONT_RESIZE_HEIGHT = 600;
   /**
    * Declared name of main-label font style
    */
   private static final String MAIN_FONT = "Segoe UI Semibold";
   /**
    * Declared name of non-binary buttons font style
    */
   private static final String TIMES_NEW_ROMAN = "Times New Roman";
   /**
    * Suffix of main button anchors selector
    */
   private static final String ANCHOR_SELECTOR_SUFFIX = "2";
   /**
    * AnchorPane root selector
    */
   private static final String ROOT_VBOX_SELECTOR = "#root-anc";
   /**
    * Toolbar HBox selector
    */
   private static final String TOOLBAR_HBOX_SELECTOR = "#hbox-toolbar";
   /**
    * Memory HBox selector
    */
   private static final String MEMORY_HBOX_SELECTOR = "#hbox-memory";
   /**
    * Option HBox selector
    */
   private static final String OPT_HBOX_SELECTOR = "#hbox-opt";
   /**
    * Display HBox selector
    */
   private static final int OPERATION_BTN_5_FONT_RATIO = 18;
   private static final int OPERATION_BTN_5_FONT_RATIO1 = 21;
   private static final int OPERATION_BTN_5_FONT_RATIO2 = 35;
   private static final int OPERATION_BTN_5_FONT_RATIO3 = 20;
   private static final double OPERATION_BTN_2_FONT_RATIO = 31.3;
   private static final int NUMBER_BTN_FONT_RATIO = 22;
   private static final double MAIN_FONT_HEIGHT_RATIO = 11.33;
   private static final double MAIN_FONT_TEXT_RATIO = 14.5;

   private static final String DISPLAY_HBOX_SELECTOR = "#hbox-display";
   private static final String[] MAIN_HBOX_SELECTORS = {"#hbox1", "#hbox2", "#hbox3", "#hbox4", "#hbox5", "#hbox6"};
   private static final String[] OPERATION_BUTTONS_SELECTORS1 = {"#btn-equals", "#btn-multiply", "#btn-divide"};
   private static final String[] OPERATION_BUTTONS_SELECTORS2 = {"#btn-undo", "#btn-c", "#btn-ce", "#btn-percent", "#btn-sqrt"};
   private static final String[] OPERATION_BUTTONS_SELECTORS3 = {"#btn-1x", "#btn-square"};
   private static final String[] OPERATION_BUTTONS_SELECTORS4 = {"#btn-dot", "#btn-minus"};
   private static final String[] OPERATION_BUTTONS_SELECTORS5 = {"#btn-negate", "#btn-plus"};
   private static final String[] NUMBER_BUTTONS_SELECTORS = {"#btn-1", "#btn-2", "#btn-3", "#btn-4", "#btn-5", "#btn-6", "#btn-7", "#btn-8", "#btn-9", "#btn-0",};
   private static final String[] MEMORY_BUTTONS_SELECTORS = {"#mc", "#mr", "#m-plus", "#m-minus", "#ms", "#m"};

   /**
    * Constructs an instance of class
    * and initializes stage
    *
    * @param stage adjusted Stage
    */
   public AutoResizeNodeHandler(Stage stage) {
      scene = stage.getScene();
      root = (AnchorPane) scene.getRoot();
      rootVBox = (VBox) root.lookup(ROOT_VBOX_SELECTOR);
   }

   /**
    * Method calls auto-resize for
    * h-boxes, fonts, additional options,
    * history-labels.
    */
   public void setAutoResize() {
      hboxAutoResize();
      fontResize();
      additionalOptionResize();
      labelResizeAndScrollProperty();
   }

   public void hideResizeBar() {
      HBox toolbarHBox = (HBox) rootVBox.lookup(TOOLBAR_HBOX_SELECTOR);
      toolbarHBox.setVisible(false);
   }

   /**
    * Method calls auto-resize for
    * HBoxes: main, menu, memory
    */
   private void hboxAutoResize() {
      menuHBoxesResize();
      mainHBoxesResize();
      memoryHBoxResize();
   }

   /**
    * Binds history and memory tabs height with root height
    */
   private void additionalOptionResize() {
      Pane historyBlock = (AnchorPane) root.lookup("#history-tab");
      AnchorPane memoryBlock = (AnchorPane) root.lookup("#memory-tab");
      historyBlock.prefHeightProperty().bind(root.heightProperty());
      memoryBlock.prefHeightProperty().bind(root.heightProperty());
      root.heightProperty().addListener((observable, oldValue, newValue) -> {
         double blockHeightRatio = 2.55;
         historyBlock.setLayoutY(root.getHeight() / blockHeightRatio);
         memoryBlock.setLayoutY(root.getHeight() / blockHeightRatio);
      });
   }

   /**
    * Binds memory-buttons HBox height and width
    * with root height and width
    */
   private void memoryHBoxResize() {
      HBox hBox = (HBox) rootVBox.lookup(MEMORY_HBOX_SELECTOR);
      hBox.prefWidthProperty().bind(rootVBox.widthProperty());
      int memoryHBoxResizeRatio = 20;
      hBox.prefHeightProperty().bind(rootVBox.heightProperty().divide(memoryHBoxResizeRatio));
      for (String mAnchorSelector : MEMORY_BUTTONS_SELECTORS) {
         AnchorPane mAnchor = (AnchorPane) hBox.lookup(mAnchorSelector + ANCHOR_SELECTOR_SUFFIX);
         if (mAnchor != null) {
            int numberOfMemoryButtons = 6;
            mAnchor.prefWidthProperty().bind(hBox.prefWidthProperty().divide(numberOfMemoryButtons));
            mAnchor.prefHeightProperty().bind(hBox.prefHeightProperty());
         }
      }
   }

   /**
    * Binds main-buttons HBoxes height and width
    * with root height and width
    */
   private void mainHBoxesResize() {
      List<String> buttonsList = addStringArrays(NUMBER_BUTTONS_SELECTORS, OPERATION_BUTTONS_SELECTORS1, OPERATION_BUTTONS_SELECTORS2, OPERATION_BUTTONS_SELECTORS3, OPERATION_BUTTONS_SELECTORS4, OPERATION_BUTTONS_SELECTORS5);
      for (String hboxSelector : MAIN_HBOX_SELECTORS) {
         HBox hBox = (HBox) rootVBox.lookup(hboxSelector);
         hBox.prefWidthProperty().bind(rootVBox.widthProperty());
         int mainHBoxResizeRatio = 10;
         hBox.prefHeightProperty().bind(rootVBox.heightProperty().divide(mainHBoxResizeRatio));
         for (String bAnchorSelector : buttonsList) {
            AnchorPane bAnchor = (AnchorPane) hBox.lookup(bAnchorSelector + ANCHOR_SELECTOR_SUFFIX);
            if (bAnchor != null) {
               int numberOfMainButtons = 4;
               bAnchor.prefWidthProperty().bind(hBox.prefWidthProperty().divide(numberOfMainButtons));
               bAnchor.prefHeightProperty().bind(hBox.prefHeightProperty());
            }
         }
      }
   }

   private List<String> addStringArrays(String[]... arrays) {
      List<String> list = new ArrayList<>();
      for (String[] arr : arrays) {
         list.addAll(Arrays.asList(arr));
      }
      return list;
   }

   /**
    * Binds menu, toolbar and display HBoxes height and width
    * with root height and width
    */
   private void menuHBoxesResize() {
      HBox toolbarHBox = (HBox) rootVBox.lookup(TOOLBAR_HBOX_SELECTOR);
      toolbarHBox.prefWidthProperty().bind(rootVBox.widthProperty());
      HBox optHbox = (HBox) rootVBox.lookup(OPT_HBOX_SELECTOR);
      optHbox.prefWidthProperty().bind(rootVBox.widthProperty());
      HBox displayHBox = (HBox) rootVBox.lookup(DISPLAY_HBOX_SELECTOR);
      displayHBox.prefWidthProperty().bind(rootVBox.widthProperty());
      displayHBox.prefHeightProperty().bind(rootVBox.prefHeightProperty());
   }

   /**
    * Calls font auto-resize for main-label
    */
   private void fontResize() {
      textFontResize();
      widthFontResize();
      heightFontResize();
   }

   /**
    * Binds main-label text-property with scene width,
    * height and text-length for main font-resize
    */
   private void textFontResize() {
      Label mainLabel = (Label) scene.lookup("#mainLabel");
      mainLabel.textProperty().addListener((observable, oldValue, newValue) -> {
         if (newValue.length() > START_FONT_RESIZE_LENGTH && scene.getWidth() < END_FONT_RESIZE_WIDTH) {
            FONT_SIZE_PROPERTY.bind(scene.widthProperty().divide(MAIN_FONT_TEXT_RATIO));
            mainLabel.setFont(Font.font(MAIN_FONT, FONT_SIZE_PROPERTY.doubleValue()));
         } else if (newValue.length() < START_FONT_RESIZE_LENGTH && scene.getHeight() < END_FONT_RESIZE_HEIGHT) {
            mainLabel.setFont(Font.font(MAIN_FONT, FONT_SIZE));
         }
      });
   }

   /**
    * Adds listener to scene width-property and changes font,
    * depending on height and text-length for main font-resize
    */
   private void widthFontResize() {
      Label mainLabel = (Label) scene.lookup("#mainLabel");
      scene.widthProperty().addListener((observable, oldValue, newValue) -> {
         if (scene.getWidth() < END_FONT_RESIZE_WIDTH && mainLabel.getText().length() > START_FONT_RESIZE_LENGTH) {
            FONT_SIZE_PROPERTY.bind(scene.widthProperty().divide(MAIN_FONT_TEXT_RATIO));
            mainLabel.setFont(Font.font(MAIN_FONT, FONT_SIZE_PROPERTY.doubleValue()));
         } else {
            mainLabel.setFont(Font.font(MAIN_FONT, FONT_SIZE));
         }
      });
   }

   /**
    * Adds listener to scene height-property and changes font,
    * depending height and text-length for font-resize of:
    * main-label, operation-buttons, digit-buttons
    */
   private void heightFontResize() {
      Label mainLabel = (Label) scene.lookup("#mainLabel");
      scene.heightProperty().addListener((observable, oldValue, newValue) -> {
         if (scene.getWidth() > END_FONT_RESIZE_WIDTH && newValue.doubleValue() > END_FONT_RESIZE_HEIGHT) {
            mainLabel.setFont(font(MAIN_FONT, (newValue.doubleValue() / MAIN_FONT_HEIGHT_RATIO)));
         }

         for (String btnSelector : NUMBER_BUTTONS_SELECTORS) {
            Button button = (Button) scene.lookup(btnSelector);
            button.setFont(font(MAIN_FONT, (int) (newValue.doubleValue() / NUMBER_BTN_FONT_RATIO)));
         }

         for (String btnSelector : OPERATION_BUTTONS_SELECTORS2) {
            Button button = (Button) scene.lookup(btnSelector);
            int size = (int) (newValue.doubleValue() /  OPERATION_BTN_2_FONT_RATIO);
            if (btnSelector.startsWith("#btn-c")) {
               button.setFont(font("Segoe UI", size));
            } else {
               button.setFont(font("Segoe MDL2 Assets", size));
            }
         }
         btnFontResize(newValue, OPERATION_BUTTONS_SELECTORS1, OPERATION_BTN_5_FONT_RATIO);
         btnFontResize(newValue, OPERATION_BUTTONS_SELECTORS3, OPERATION_BTN_5_FONT_RATIO1);
         btnFontResize(newValue, OPERATION_BUTTONS_SELECTORS4, OPERATION_BTN_5_FONT_RATIO2);
         btnFontResize(newValue, OPERATION_BUTTONS_SELECTORS5, OPERATION_BTN_5_FONT_RATIO3);
      });
   }

   private void btnFontResize(Number newValue, String[] operationButtonsSelectors5, int operationBtn5FontRatio) {
      for (String btnSelector : operationButtonsSelectors5) {
         Button button = (Button) scene.lookup(btnSelector);
         button.setFont(font(TIMES_NEW_ROMAN, ITALIC, (int) (newValue.doubleValue() / operationBtn5FontRatio)));
      }
   }


   /**
    * Adds listener to history-label text-property and scene width-property,
    * changes font-size, depending on height and text-length for main font-resize
    */
   private void labelResizeAndScrollProperty() {
      ScrollPane scrollPane = (ScrollPane) scene.lookup("#history-scroll");
      AnchorPane historyAnc = (AnchorPane) scrollPane.getContent();
      Label historyLabel = (Label) historyAnc.lookup("#history-label");
      historyLabel.textProperty().addListener((observable, oldValue, newValue) -> labelResizeAndScrollProperty(historyLabel, scrollPane));
      scene.widthProperty().addListener((observable, oldValue, newValue) -> labelResizeAndScrollProperty(historyLabel, scrollPane));
   }

   /**
    * Sets width for history label based on its text length
    */
   private void labelResizeAndScrollProperty(Label historyLabel, ScrollPane scrollPane) {
      double historyLabelWidthRatio = 6.5;
      double historyLabelPrefWidthInset = 20;
      double historyLabelWidthInset = 35;
      double value = historyLabel.getText().length() * historyLabelWidthRatio;
      if (value > scene.getWidth() - historyLabelPrefWidthInset) {
         historyLabel.setPrefWidth(value);
      } else {
         historyLabel.setPrefWidth(scene.getWidth() - historyLabelWidthInset);
      }
      scrollPane.setHvalue(1);
   }
}
