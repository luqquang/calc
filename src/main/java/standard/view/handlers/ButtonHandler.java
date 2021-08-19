package standard.view.handlers;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import static standard.view.CalculatorGraphic.ASSETS_PATH;

/**
 * This class fill buttons with its content-views
 * and handles its disabled status
 */
public class ButtonHandler {
   /**
    * The images depth of popup-tabs buttons
    */
   private static final int POPUP_BTN_DEPTH = 16;
   /**
    * The images depth of window buttons
    */
   private static final int WINDOW_BTN_DEPTH = 10;
   /**
    * Adjusted AnchorPane root
    */
   private AnchorPane root;
  /**
    * Array of option-scroll button selectors
    */
   private static final String[] OPTIONS_SCROLL_ICONS = {"standard", "scientific", "programmer", "date", "currency", "volume", "length", "weight",
           "temperature", "energy", "area", "speed", "time", "power", "data", "pressure", "angle"};

   /**
    * Constructs ButtonHandler and adjusts AnchorPane root
    *
    * @param root adjusted AnchorPane
    */
   public ButtonHandler(AnchorPane root) {
      this.root = root;
   }

   /**
    * Loads image for buttons
    */
   public void loadButtonImages() {
      String clockBtnName = "clock_btn";
      String maximizeBtnName = "maximize_btn";
      String iconifyBtnName = "iconify_btn";
      String optBtnName = "opt_btn";
      String optBtnName2 = "opt_btn2";
      Button clock = (Button) root.lookup("#" + clockBtnName);
      Button maximize = (Button) root.lookup("#" + maximizeBtnName);
      Button iconify = (Button) root.lookup("#" + iconifyBtnName);
      Button opt = (Button) root.lookup("#" + optBtnName);
      Button opt2 = (Button) root.lookup("#" + optBtnName2);
      setButtonImg(clockBtnName, clock, POPUP_BTN_DEPTH, POPUP_BTN_DEPTH);
      setButtonImg(maximizeBtnName, maximize, WINDOW_BTN_DEPTH, WINDOW_BTN_DEPTH);
      setButtonImg(iconifyBtnName, iconify, WINDOW_BTN_DEPTH, 1);
      setButtonImg(optBtnName, opt, POPUP_BTN_DEPTH, WINDOW_BTN_DEPTH);
      setButtonImg(optBtnName, opt2, POPUP_BTN_DEPTH, WINDOW_BTN_DEPTH);
      setOptionIcons();
   }

   /**
    * Sets images for buttons
    *
    * @param btnName String button name
    * @param button  Button instance
    * @param width   double value of image width
    * @param height  double value of image height
    */
   void setButtonImg(String btnName, Button button, double width, double height) {
      Image image = new Image(ASSETS_PATH + btnName + ".png");
      ImageView imageView = new ImageView(image);
      imageView.setFitWidth(width);
      imageView.setFitHeight(height);
      button.setGraphic(imageView);
   }

   /**
    * Sets option-scroll icons
    */
   private void setOptionIcons() {
      ImageView imageView = (ImageView) root.lookup("#about");
      Image image = new Image(ASSETS_PATH + "about.png");
      imageView.setImage(image);
      imageView.setY(7);
      imageView.setFitWidth(15);
      imageView.setFitWidth(15);
      for (String optionView : OPTIONS_SCROLL_ICONS) {
         setScrollImgView(optionView);
      }
   }

   private void setScrollImgView(String imgViewSelector) {
      ScrollPane scrollPane = (ScrollPane) root.lookup("#option-scroll");
      ImageView imtView = (ImageView) scrollPane.getContent().lookup("#" + imgViewSelector);
      imtView.setImage(new Image(ASSETS_PATH + imgViewSelector + ".png"));
      if (!imgViewSelector.equals("temperature")) {
         imtView.setY(7);
      }
      imtView.setFitWidth(15);
      imtView.setFitWidth(15);
   }
}