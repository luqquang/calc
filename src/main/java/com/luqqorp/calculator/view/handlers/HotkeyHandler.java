package com.luqqorp.calculator.view.handlers;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class handles keyboard inputs
 * for calling buttons depending on its
 * hotkeys
 */
public class HotkeyHandler {
   /**
    * Map consist of KeyCode value for
    * key on keyboard and its button selector
    */
   private Map<KeyCode, String> hotkeys = new HashMap<>();
   /**
    * Map consist of KeyCodeCombination value for
    * keys on keyboard and its button selector
    */
   private Map<KeyCodeCombination, String> hotCombinations = new HashMap<>();
   /**
    * Adjusted Scene
    */
   private Scene scene;
   /**
    * Parent root for requesting focus
    */
   private Parent root;
   /**
    * Button of entered key or key-combo
    */
   private Button button;
   /**
    * Background of entered key or key-combo
    * button pressed
    */
   private final Background backgroundPressed = new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY));
   /**
    * Default background of entered key or key-combo
    * button pressed
    */
   private Background background;
   /**
    * Shows if hotkey is used
    */
   private boolean hotkeyUsed = false;

   /**
    * Constructs a HotkeyHandler, adjusts scene,
    * root and initializes hotkeys and hotCombinations map
    *
    * @param scene Adjusted Scene
    */
   public HotkeyHandler(Scene scene) {
      this.scene = scene;
      this.root = scene.getRoot();
      hotkeys.put(KeyCode.DIGIT0, "#btn-0");
      hotkeys.put(KeyCode.DIGIT1, "#btn-1");
      hotkeys.put(KeyCode.DIGIT2, "#btn-2");
      hotkeys.put(KeyCode.DIGIT3, "#btn-3");
      hotkeys.put(KeyCode.DIGIT4, "#btn-4");
      hotkeys.put(KeyCode.DIGIT5, "#btn-5");
      hotkeys.put(KeyCode.DIGIT6, "#btn-6");
      hotkeys.put(KeyCode.DIGIT7, "#btn-7");
      hotkeys.put(KeyCode.DIGIT8, "#btn-8");
      hotkeys.put(KeyCode.DIGIT9, "#btn-9");
      hotkeys.put(KeyCode.NUMPAD0, "#btn-0");
      hotkeys.put(KeyCode.NUMPAD1, "#btn-1");
      hotkeys.put(KeyCode.NUMPAD2, "#btn-2");
      hotkeys.put(KeyCode.NUMPAD3, "#btn-3");
      hotkeys.put(KeyCode.NUMPAD4, "#btn-4");
      hotkeys.put(KeyCode.NUMPAD5, "#btn-5");
      hotkeys.put(KeyCode.NUMPAD6, "#btn-6");
      hotkeys.put(KeyCode.NUMPAD7, "#btn-7");
      hotkeys.put(KeyCode.NUMPAD8, "#btn-8");
      hotkeys.put(KeyCode.NUMPAD9, "#btn-9");
      hotkeys.put(KeyCode.PERIOD, "#btn-dot");
      hotkeys.put(KeyCode.DECIMAL, "#btn-dot");
      hotkeys.put(KeyCode.ADD, "#btn-plus");
      hotkeys.put(KeyCode.SUBTRACT, "#btn-minus");
      hotkeys.put(KeyCode.MINUS, "#btn-minus");
      hotkeys.put(KeyCode.MULTIPLY, "#btn-multiply");
      hotkeys.put(KeyCode.DIVIDE, "#btn-divide");
      hotkeys.put(KeyCode.ESCAPE, "#btn-c");
      hotkeys.put(KeyCode.DELETE, "#btn-ce");
      hotkeys.put(KeyCode.BACK_SPACE, "#btn-undo");
      hotkeys.put(KeyCode.F9, "#btn-negate");
      hotkeys.put(KeyCode.Q, "#btn-square");
      hotkeys.put(KeyCode.R, "#btn-1x");
      hotkeys.put(KeyCode.EQUALS, "#btn-equals");
      hotkeys.put(KeyCode.ENTER, "#btn-equals");
      hotCombinations.put(new KeyCodeCombination(KeyCode.EQUALS, KeyCombination.SHIFT_DOWN), "#btn-plus");
      hotCombinations.put(new KeyCodeCombination(KeyCode.DIGIT2, KeyCombination.SHIFT_DOWN), "#btn-sqrt");
      hotCombinations.put(new KeyCodeCombination(KeyCode.DIGIT8, KeyCombination.SHIFT_DOWN), "#btn-multiply");
      hotCombinations.put(new KeyCodeCombination(KeyCode.DIGIT5, KeyCombination.SHIFT_DOWN), "#btn-percent");
   }

   /**
    * Verifies pressed key or key-combo
    * and fires its buttons, changes fires buttons
    * background and changes back when it released
    */
   public void addKeyListener() {
      scene.setOnKeyPressed(event -> {
         Set<KeyCodeCombination> combinationSet = hotCombinations.keySet();
         combinationSet.forEach(combo -> {
            if (combo.match(event)) {
               button = (Button) scene.lookup(hotCombinations.get(combo));
               clickButton();
               hotkeyUsed = true;
            }
         });

         if (!hotkeyUsed) {
            Set<KeyCode> keyCodeSet = hotkeys.keySet();
            keyCodeSet.forEach(code -> {
               if (event.getCode().equals(code)) {
                  button = (Button) scene.lookup(hotkeys.get(code));
                  clickButton();
               }
            });
         }
         hotkeyUsed = false;
         root.requestFocus();
      });
      scene.setOnKeyReleased(event -> {
         if (button != null) {
            button.setBackground(background);
         }
      });
   }

   /**
    * Method writes default button background
    * before it fires
    */
   private void clickButton() {
      if (!button.getBackground().equals(backgroundPressed)) {
         background = button.getBackground();
      }
      button.fire();
      button.setBackground(backgroundPressed);
   }
}


