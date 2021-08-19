package standard.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;
import standard.controller.utils.DecimalViewFormer;
import standard.controller.utils.HistoryViewFormer;
import standard.model.Calculation;
import standard.model.data.History;
import standard.model.data.Operator;

import java.math.BigDecimal;

/**
 * Calculator controller manages commands for model, handles exceptions,
 * creates view text for numbers
 */
@Slf4j
public class CalculatorController {

   /**
    * Calculator model
    */
   private Calculation calculation = new Calculation();
   /**
    * Formatter for creating text by unit value and creating new unit by its text
    */
   private DecimalViewFormer decimalViewFormer = new DecimalViewFormer();
   /**
    * Formatter for creating text history
    */
   private HistoryViewFormer historyViewFormer = new HistoryViewFormer();

   @FXML
   private Label mainLabel;

   @FXML
   private Label historyLabel;

   @FXML
   private Label memoryLabel;

   @FXML
   private Label memoryMessageLabel;

   @FXML
   private AnchorPane memoryBox;

   @FXML
   private Button buttonPercent, buttonSqrt, buttonSquare, button1x, buttonDivide,
           buttonMultiply, buttonMinus, buttonPlus, buttonDot, buttonNegate,
           memoryAddBtn, memorySubstactBtn, memorySBtn;

   @FXML
   private Button memoryBtn, memoryRBtn, memoryClearBtn;

   @FXML
   public void button0OnAction(ActionEvent event) {
      addSymbol(0);
   }

   @FXML
   public void button1OnAction(ActionEvent event) {
      addSymbol(1);
   }

   @FXML
   public void button2OnAction(ActionEvent event) {
      addSymbol(2);
   }

   @FXML
   public void button3OnAction(ActionEvent event) {
      addSymbol(3);
   }

   @FXML
   public void button4OnAction(ActionEvent event) {
      addSymbol(4);
   }

   @FXML
   public void button5OnAction(ActionEvent event) {
      addSymbol(5);
   }

   @FXML
   public void button6OnAction(ActionEvent event) {
      addSymbol(6);
   }

   @FXML
   public void button7OnAction(ActionEvent event) {
      addSymbol(7);
   }

   @FXML
   public void button8OnAction(ActionEvent event) {
      addSymbol(8);
   }

   @FXML
   public void button9OnAction(ActionEvent event) {
      addSymbol(9);
   }

   @FXML
   public void buttonPlus(ActionEvent event) {
      createOperation(Operator.ADD);
   }

   @FXML
   public void buttonMinus(ActionEvent event) {
      createOperation(Operator.SUBTRACT);
   }

   @FXML
   public void buttonMultiply(ActionEvent event) {
      createOperation(Operator.MULTIPLY);
   }

   @FXML
   public void buttonDivide(ActionEvent event) {
      createOperation(Operator.DIVIDE);
   }

   @FXML
   public void buttonPercent(ActionEvent event) {
      createOperation(Operator.PERCENT);
   }

   @FXML
   public void buttonSqrt(ActionEvent event) {
      createOperation(Operator.SQRT);
   }

   @FXML
   public void buttonNegate(ActionEvent event) {
      createOperation(Operator.NEGATE);
   }

   @FXML
   public void buttonSquare(ActionEvent event) {
      createOperation(Operator.SQUARE);
   }

   @FXML
   public void button1x(ActionEvent event) {
      createOperation(Operator.ONE_BY_X);
   }


   /**
    * Adds a dot to current unit's view if it doesn't has one
    * and set it main-label text
    *
    * @param event ActionEvent of pressed button
    */
   @FXML
   public void buttonDot(ActionEvent event) {
      addSymbol(10);
//      BigDecimal currentDecimal = calculation.getCurrentDecimal();
//      if(calculation.isCurrentValNew()) {
//         calculation.editCurrentDecimal(ZERO);
//         decimalViewFormer.formResultDecimalText(ZERO);
//      }else {
//         currentDecimal = decimalViewFormer.addDot(currentDecimal);
//         calculation.editCurrentDecimal(currentDecimal);
//      }
//      mainLabel.setText(decimalViewFormer.formatInputNumber(currentDecimal));
//      setDecimalAndHistoryText();
   }

   private void addSymbol(int code) {
      unlock();
      BigDecimal decimal = calculation.getCurrentDecimal();
      boolean currentValNew = calculation.isCurrentValNew();
      decimal = decimalViewFormer.parseDecimal(decimal, code, currentValNew);
      calculation.editCurrentDecimal(decimal);
      setDecimalAndHistoryText();
   }

   private void setDecimalAndHistoryText() {
      mainLabel.setText(decimalViewFormer.formatInputNumber(calculation.getCurrentDecimal()));
      historyLabel.setText(historyViewFormer.getHistoryText(calculation.getHistory()));
   }

   /**
    * Unlocks buttons and removes last digit from number
    * and set it main-label text
    *
    * @param event ActionEvent of pressed button
    */
   @FXML
   public void buttonBackspace(ActionEvent event) {
      unlock();
      if (!calculation.isCurrentValNew()) {
         calculation.editCurrentDecimal(decimalViewFormer.tryRemoveLast());
      }
      mainLabel.setText(decimalViewFormer.getCurrentDecimalText());
   }

   /**
    * Unlocks buttons, refreshes calculation, history, memory and views
    * and set it main-label text
    *
    * @param event ActionEvent of pressed button
    */
   @FXML
   public void buttonC(ActionEvent event) {
      unlock();
      calculation.clearAll();
      decimalViewFormer = new DecimalViewFormer();
      historyViewFormer = new HistoryViewFormer();
      historyLabel.setText("");
      mainLabel.setText("0");
   }

   /**
    * Unlocks buttons, refreshes calculation
    * and set it main-label text
    *
    * @param event ActionEvent of pressed button
    */
   @FXML
   public void buttonCE(ActionEvent event) {
      unlock();
      calculation.clearEnter();
      decimalViewFormer = new DecimalViewFormer();
      setDecimalAndHistoryText();
   }

   /**
    * Requests result from calculation process, refreshes history,
    * unlock buttons, formats this to string and set it to main-label text
    *
    * @param event ActionEvent of pressed button
    */
   @FXML
   public void buttonEquals(ActionEvent event) {
      unlock();
      try {
         decimalViewFormer.formResultDecimalText(calculation.getResultDecimal());
         setDecimalAndHistoryText();
      } catch (Exception e) {
         exceptionHandle(e);
      }
   }

   /**
    * Subtracts the current number from the memory number,
    * saves it in memory, formats this to string and
    * set it to main-label text
    *
    * @param event ActionEvent of pressed button
    */
   @FXML
   void memoryMinus(MouseEvent event) {
      lockButtons(false, new Button[]{memoryBtn, memoryRBtn, memoryClearBtn});
      memoryLabel.setText(decimalViewFormer.formOperateDecimalText(calculation.memorySubstract()));
   }

   /**
    * Adds the current number from the memory number,
    * saves it in memory, formats this to string and
    * set it to main-label text
    *
    * @param event ActionEvent of pressed button
    */
   @FXML
   void memoryPlus(MouseEvent event) {
      lockButtons(false, new Button[]{memoryBtn, memoryRBtn, memoryClearBtn});
      memoryLabel.setText(decimalViewFormer.formOperateDecimalText(calculation.memoryAdd()));
   }

   /**
    * Sets memory unit value to current value
    * formats this to string and set it to main-label text
    *
    * @param event ActionEvent of pressed button
    */
   @FXML
   public void pickFromMemory(MouseEvent event) {
      mainLabel.setText(decimalViewFormer.formOperateDecimalText(calculation.setCurrentDecimalFromMemory()));
   }

   /**
    * Clears memory unit, forms text this into memory-label
    *
    * @param event ActionEvent of pressed button
    */
   @FXML
   public void memoryClear(MouseEvent event) {
      lockButtons(true, new Button[]{memoryBtn, memoryRBtn, memoryClearBtn});
      memoryBox.setOpacity(0);
      memoryLabel.setText(decimalViewFormer.formOperateDecimalText(calculation.memoryClear()));
   }

   /**
    * Requests operation, and its history to calculation,
    * handles response into view format and set it to main-label text
    * and history label text
    *
    * @param operator Adjusted operation
    */
   private void createOperation(Operator operator) {
      try {
         decimalViewFormer.formResultDecimalText(calculation.createOperation(operator));
         setDecimalAndHistoryText();
      } catch (Exception e) {
         exceptionHandle(e);
      }
   }

   /**
    * Adds new digit to current unit text and creates a new unit value
    * from it, requests refresh current unit in calculation,
    * handles response into view format and set it to main-label text
    *
    * @param code Adjusted digit
    */


   /**
    * Lock, memory buttons and operation buttons, except equals,
    * clear, backspace and clear-enter buttons.
    */
   private void lockForException() {
      Button[] lockedButtons = new Button[]{buttonPercent, buttonSqrt, buttonSquare, button1x, buttonDivide,
              buttonMultiply, buttonMinus, buttonPlus, buttonDot, buttonNegate, memoryAddBtn, memorySubstactBtn, memorySBtn};
      Button[] memoryLockedButtons = new Button[]{memoryBtn, memoryRBtn, memoryClearBtn};
      lockButtons(true, lockedButtons);
      lockButtons(true, memoryLockedButtons);
   }

   /**
    * Unlocks if percent button is disabled, memory buttons and operation buttons,
    * except memory, memory-store, memory-recall buttons
    */
   private void unlock() {
      Button[] lockedButtons = new Button[]{buttonPercent, buttonSqrt, buttonSquare, button1x, buttonDivide,
              buttonMultiply, buttonMinus, buttonPlus, buttonDot, buttonNegate, memoryAddBtn, memorySubstactBtn, memorySBtn};
      if (buttonPercent.isDisabled()) {
         lockButtons(false, lockedButtons);
      }
   }

   /**
    * Sets disabled value to all of the buttons in array
    *
    * @param disabled      adjusted boolean value
    * @param lockedButtons adjusted button's array
    */
   private void lockButtons(boolean disabled, Button[] lockedButtons) {
      for (Button b : lockedButtons) {
         b.setDisable(disabled);
      }
      if (disabled) {
         memoryMessageLabel.setOpacity(1);
      } else {
         memoryMessageLabel.setOpacity(0);
      }
   }

   /**
    * Handles exception and display exception message
    * on the main-label, also saves history of expression
    * that leads to exception, refreshes model and memory
    *
    * @param e thrown exception
    */
   private void exceptionHandle(Exception e) {
      String message = e.getMessage();
      mainLabel.setText(message.split(":")[0]);
      lockForException();
      History exceptionHistory = calculation.getHistory();
      calculation.clearAll();
      calculation.memoryClear();
      historyLabel.setText(historyViewFormer.getHistoryText(exceptionHistory));
      log.info(message);
   }
}
