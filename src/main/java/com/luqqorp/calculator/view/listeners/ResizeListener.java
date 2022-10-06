package com.luqqorp.calculator.view.listeners;

import com.luqqorp.calculator.view.CalculatorGraphic;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * This class is listener for stage resize
 */
public class ResizeListener implements EventHandler<MouseEvent> {
   /**
    * Adjusted Stage
    */
   private Stage stage;
   /**
    * Default cursor event
    */
   private Cursor cursorEvent = Cursor.DEFAULT;
   /**
    * Initial start horizontal point
    */
   private double startX = 0;
   /**
    * Initial start vertical point
    */
   private double startY = 0;
   /**
    * Initial start vertical walk
    */
   private double limitWX = 0;
   /**
    * Initial start vertical walk
    */
   private double limitWY = 0;

   /**
    * Construct listener and initializes stage
    *
    * @param stage Adjusted Stage
    */
   public ResizeListener(Stage stage) {
      this.stage = stage;
   }

   /**
    * Handles the resize processes and mouse cursor
    *
    * @param mouseEvent MouseEvent pressing, moving, dragging, releasing
    */
   @Override
   public void handle(MouseEvent mouseEvent) {
      if (!stage.isMaximized()) {
         EventType<? extends MouseEvent> mouseEventType = mouseEvent.getEventType();
         Scene scene = stage.getScene();
         double mouseEventX = mouseEvent.getSceneX();
         double mouseEventY = mouseEvent.getSceneY();
         double sceneWidth = scene.getWidth();
         double sceneHeight = scene.getHeight();
         if (MouseEvent.MOUSE_MOVED.equals(mouseEventType)) {
            cursorHandle(scene, mouseEventX, mouseEventY, sceneWidth, sceneHeight);
         } else if (MouseEvent.MOUSE_PRESSED.equals(mouseEventType)) {
            stage.getScene().getRoot().requestFocus();
            setLimits(mouseEvent, mouseEventX, mouseEventY);
         } else if (MouseEvent.MOUSE_RELEASED.equals(mouseEventType)) {
            cursorEvent = Cursor.DEFAULT;
         } else if (MouseEvent.MOUSE_DRAGGED.equals(mouseEventType) && !Cursor.DEFAULT.equals(cursorEvent)) {
            northWestNorthEastResize(mouseEvent, mouseEventY);
            northWestWestSouthWestResize(mouseEvent, mouseEventX);
         }
      }
   }

   /**
    * Set started point and limit points of scree for resizing
    *
    * @param mouseEvent  MouseEvent when mouse pressed
    * @param mouseEventX horizontal point before mouse pressed
    * @param mouseEventY vertical point before mouse pressed
    */
   private void setLimits(MouseEvent mouseEvent, double mouseEventX, double mouseEventY) {
       startX = stage.getWidth() - mouseEventX;
       startY = stage.getHeight() - mouseEventY;
       limitWX = mouseEvent.getScreenX() + stage.getWidth() - CalculatorGraphic.MIN_WIDTH;
       limitWY = mouseEvent.getScreenY() + stage.getHeight() - CalculatorGraphic.MIN_HEIGHT;
   }

   /**
    * Handles cursor. Switches cursor value if mouse points
    * is on borders of scene
    *
    * @param scene       Adjusted Scene
    * @param mouseEventX vertical point mouse moved
    * @param mouseEventY horizontal point mouse moved
    * @param sceneWidth  current scene width
    * @param sceneHeight current scene height
    */
   private void cursorHandle(Scene scene, double mouseEventX, double mouseEventY, double sceneWidth, double sceneHeight) {
      int border = 3;
      if (mouseEventX < border && mouseEventY < border) {
         cursorEvent = Cursor.NW_RESIZE;
      } else if (mouseEventX < border && mouseEventY > sceneHeight - border) {
         cursorEvent = Cursor.SW_RESIZE;
      } else if (mouseEventX > sceneWidth - border && mouseEventY < border) {
         cursorEvent = Cursor.NE_RESIZE;
      } else if (mouseEventX > sceneWidth - border && mouseEventY > sceneHeight - border) {
         cursorEvent = Cursor.SE_RESIZE;
      } else if (mouseEventX < border) {
         cursorEvent = Cursor.W_RESIZE;
      } else if (mouseEventX > sceneWidth - border) {
         cursorEvent = Cursor.E_RESIZE;
      } else if (mouseEventY < border) {
         cursorEvent = Cursor.N_RESIZE;
      } else if (mouseEventY > sceneHeight - border) {
         cursorEvent = Cursor.S_RESIZE;
      } else {
         cursorEvent = Cursor.DEFAULT;
      }
      scene.setCursor(cursorEvent);
   }

   /**
    * Calls north and south resize for
    * north-west and north-east resize
    *
    * @param mouseEvent  mouse events used for points to set limits
    * @param mouseEventY mouse vertical point
    */
   private void northWestNorthEastResize(MouseEvent mouseEvent, double mouseEventY) {
      if (!Cursor.W_RESIZE.equals(cursorEvent) && !Cursor.E_RESIZE.equals(cursorEvent)) {
         if (Cursor.NW_RESIZE.equals(cursorEvent) || Cursor.N_RESIZE.equals(cursorEvent) || Cursor.NE_RESIZE.equals(cursorEvent)) {
            northHeightResize(mouseEvent, mouseEventY);
         } else {
            southHeightResize(mouseEventY);
         }
      }
   }

   /**
    * Calls west and east resize for
    * north-west and south-west resize
    *
    * @param mouseEvent  mouse events used for points to set limits
    * @param mouseEventX mouse horizontal point
    */
   private void northWestWestSouthWestResize(MouseEvent mouseEvent, double mouseEventX) {
      if (!Cursor.N_RESIZE.equals(cursorEvent) && !Cursor.S_RESIZE.equals(cursorEvent)) {
         if (Cursor.NW_RESIZE.equals(cursorEvent) || Cursor.W_RESIZE.equals(cursorEvent) || Cursor.SW_RESIZE.equals(cursorEvent)) {
            westWidthResize(mouseEvent, mouseEventX);
         } else {
            eastResize(mouseEventX);
         }
      }
   }

   /**
    * East resize. Sets stage width depending
    * on mouse horizontal dragging
    *
    * @param mouseEventX mouse horizontal point
    */
   private void eastResize(double mouseEventX) {
       if (stage.getWidth() > CalculatorGraphic.MIN_WIDTH || mouseEventX + startX - stage.getWidth() > 0) {
           double value = mouseEventX + startX;
           if (value < CalculatorGraphic.MIN_WIDTH) {
               value = CalculatorGraphic.MIN_WIDTH;
           }
           stage.setWidth(value);
       }
   }

   /**
    * East resize. Sets stage width depending
    * on mouse horizontal dragging, considers
    * walk resize not to cross the limit
    *
    * @param mouseEvent  mouse events used for points to set limits
    * @param mouseEventX mouse horizontal point
    */
   private void westWidthResize(MouseEvent mouseEvent, double mouseEventX) {
       if (stage.getWidth() > CalculatorGraphic.MIN_WIDTH || mouseEventX < 0) {
           double value = stage.getX() - mouseEvent.getScreenX() + stage.getWidth();
           if (value < CalculatorGraphic.MIN_WIDTH) {
               value = CalculatorGraphic.MIN_WIDTH;
           }
           stage.setWidth(value);
           double screenX = mouseEvent.getScreenX();
           if (screenX > limitWX) {
               screenX = limitWX;
           }
           stage.setX(screenX);
       }
   }

   /**
    * East resize. Sets stage width depending
    * on mouse vertical dragging
    *
    * @param mouseEventY mouse vertical point
    */
   private void southHeightResize(double mouseEventY) {
       if (stage.getHeight() > CalculatorGraphic.MIN_HEIGHT || mouseEventY + startY - stage.getHeight() > 0) {
           double value = mouseEventY + startY;
           if (value < CalculatorGraphic.MIN_HEIGHT) {
               value = CalculatorGraphic.MIN_HEIGHT;
           }
           stage.setHeight(value);
       }
   }

   /**
    * East resize. Sets stage width depending
    * on mouse vertical dragging, considers
    * walk resize not to cross the limit
    *
    * @param mouseEvent  mouse events used for points to set limits
    * @param mouseEventY mouse vertical point
    */
   private void northHeightResize(MouseEvent mouseEvent, double mouseEventY) {
       if (stage.getHeight() > CalculatorGraphic.MIN_HEIGHT || mouseEventY < 0) {
           double value = stage.getY() - mouseEvent.getScreenY() + stage.getHeight();
           if (value < CalculatorGraphic.MIN_HEIGHT) {
               value = CalculatorGraphic.MIN_HEIGHT;
           }
           stage.setHeight(value);
           double screenY = mouseEvent.getScreenY();
           if (screenY > limitWY) {
               screenY = limitWY;
           }
           stage.setY(screenY);
       }
   }
}
