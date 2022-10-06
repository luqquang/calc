package standard.view;

import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import standard.utils.NodesInformation;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static java.awt.event.KeyEvent.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.api.FxToolkit.setupStage;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static standard.utils.NodesInformation.buttonCommands;

class CalculatorGraphicTest extends ApplicationTest {
   private static CalculatorGraphic calculatorGraphic = new CalculatorGraphic();
   private Stage primaryStage;
   private NodesInformation nodesInformation;
   private static List<Button> mainButtons;
   private static int BORDER = 2;
   private static Robot robot;

   @BeforeAll
   static void setupRobot() {
      try {
         robot = new Robot();
      } catch (AWTException e) {
         e.printStackTrace();
      }
   }

   @BeforeEach
   void load() throws Exception {
      FxToolkit.registerPrimaryStage();
      setupStage((stage -> {
         try {
            calculatorGraphic.calcStage(stage);
            primaryStage = stage;
            nodesInformation = new NodesInformation(stage.getScene());
            mainButtons = nodesInformation.getMainButtons();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }));
      FxToolkit.showStage();
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testWindowsDrag() {
      testDrag(100, 20, 9, 8);
      testDrag(165, 20, 74, 8);
      testDrag(230, 20, 139, 8);
      testDrag(295, 20, 204, 8);
      testDrag(360, 20, 269, 8);
      testDrag(425, 20, 334, 8);
      testDrag(490, 20, 399, 8);
      testDrag(555, 20, 464, 8);
      testDrag(620, 20, 529, 8);
      testDrag(685, 20, 594, 8);
      testDrag(750, 20, 659, 8);
      testDrag(815, 20, 724, 8);
      testDrag(880, 20, 789, 8);
      testDrag(945, 20, 854, 8);
      testDrag(1010, 20, 919, 8);
      testDrag(1075, 20, 984, 8);
      testDrag(1140, 20, 1049, 8);
      testDrag(1205, 20, 1114, 8);
      testDrag(1270, 20, 1179, 8);
      testDrag(1335, 20, 1244, 8);
      testDrag(1345, 20, 1254, 8);

      testDrag(1345, 60, 1254, 48);
      testDrag(1345, 100, 1254, 88);
      testDrag(1345, 140, 1254, 128);
      testDrag(1345, 180, 1254, 168);
      testDrag(1345, 220, 1254, 208);
      testDrag(1345, 260, 1254, 248);
      testDrag(1345, 300, 1254, 288);
      testDrag(1345, 340, 1254, 328);
      testDrag(1345, 380, 1254, 368);
      testDrag(1345, 420, 1254, 408);
      testDrag(1345, 460, 1254, 448);
      testDrag(1345, 500, 1254, 488);

      testDrag(1345, 520, 1254, 508);
      testDrag(1280, 520, 1189, 508);
      testDrag(1215, 520, 1124, 508);
      testDrag(1150, 520, 1059, 508);
      testDrag(1085, 520, 994, 508);
      testDrag(1020, 520, 929, 508);
      testDrag(955, 520, 864, 508);
      testDrag(890, 520, 799, 508);
      testDrag(825, 520, 734, 508);
      testDrag(760, 520, 669, 508);
      testDrag(695, 520, 604, 508);
      testDrag(630, 520, 539, 508);
      testDrag(565, 520, 474, 508);
      testDrag(500, 520, 409, 508);
      testDrag(435, 520, 344, 508);
      testDrag(370, 520, 279, 508);
      testDrag(305, 520, 214, 508);
      testDrag(240, 520, 149, 508);
      testDrag(175, 520, 84, 508);
      testDrag(110, 520, 19, 508);

      testDrag(110, 480, 19, 468);
      testDrag(110, 440, 19, 428);
      testDrag(110, 400, 19, 388);
      testDrag(110, 360, 19, 348);
      testDrag(110, 320, 19, 308);
      testDrag(110, 280, 19, 268);

      testDrag(175, 280, 84, 268);
      testDrag(240, 280, 149, 268);
      testDrag(305, 280, 214, 268);
      testDrag(370, 280, 279, 268);
      testDrag(435, 280, 344, 268);
      testDrag(500, 280, 409, 268);
      testDrag(565, 280, 474, 268);
      testDrag(630, 280, 539, 268);
      testDrag(695, 280, 604, 268);
      testDrag(760, 280, 669, 268);
      testDrag(825, 280, 734, 268);
      testDrag(890, 280, 799, 268);
      testDrag(955, 280, 864, 268);
      testDrag(1020, 280, 929, 268);
      testDrag(1085, 280, 994, 268);
      testDrag(1150, 280, 1059, 268);
      testDrag(1215, 280, 1124, 268);
      testDrag(1280, 280, 1189, 268);
      testDrag(1345, 280, 1254, 268);
      testDrag(1410, 280, 1319, 268);
      testDrag(730, 132, 639, 120);
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testResize() {
      testWestResize(30, 352, 81, 47);
      testWestResize(50, 372, 87, 47);
      testWestResize(70, 392, 92, 47);
      testWestResize(80, 402, 94, 47);
      testWestResize(90, 412, 96, 47);
      testWestResize(100, 422, 99, 47);
      testWestResize(120, 442, 104, 47);
      testWestResize(140, 462, 109, 47);
      testWestResize(160, 482, 114, 47);
      testWestResize(180, 502, 119, 47);
      testWestResize(200, 522, 124, 47);
      testWestResize(240, 562, 134, 47);
      testWestResize(260, 582, 139, 47);
      testWestResize(280, 602, 144, 47);
      testWestResize(300, 622, 149, 47);
      testWestResize(320, 642, 154, 47);
      testWestResize(355, 677, 163, 47);
      testWestResize(385, 707, 170, 47);
      testWestResize(420, 742, 179, 47);
      testWestResize(440, 762, 184, 47);
      testWestResize(460, 782, 189, 47);
      testWestResize(480, 802, 194, 47);
      testWestResize(500, 822, 199, 47);
      testWestResize(520, 842, 204, 47);
      testWestResize(540, 862, 209, 47);
      testWestResize(560, 882, 214, 47);
      testWestResize(580, 902, 219, 47);
      testWestResize(600, 922, 224, 47);
      testWestResize(620, 942, 229, 47);
      testWestResize(640, 962, 234, 47);

      testEastResize(95, 415, 97, 47);
      testEastResize(115, 435, 102, 47);
      testEastResize(135, 455, 107, 47);
      testEastResize(155, 475, 112, 47);
      testEastResize(175, 495, 117, 47);
      testEastResize(195, 515, 122, 47);
      testEastResize(215, 535, 127, 47);
      testEastResize(235, 555, 132, 47);
      testEastResize(255, 575, 137, 47);
      testEastResize(275, 595, 142, 47);
      testEastResize(295, 615, 147, 47);
      testEastResize(315, 635, 152, 47);
      testEastResize(335, 655, 157, 47);
      testEastResize(355, 675, 162, 47);
      testEastResize(375, 695, 167, 47);
      testEastResize(395, 715, 172, 47);
      testEastResize(415, 735, 177, 47);
      testEastResize(435, 755, 182, 47);
      testEastResize(455, 775, 187, 47);
      testEastResize(475, 795, 192, 47);
      testEastResize(495, 815, 197, 47);
      testEastResize(515, 835, 202, 47);
      testEastResize(535, 855, 207, 47);
      testEastResize(555, 875, 212, 47);
      testEastResize(575, 895, 217, 47);
      testEastResize(595, 915, 222, 47);
      testEastResize(615, 935, 227, 47);
      testEastResize(635, 955, 232, 47);

      testSouthResize(30, 531, 76, 50);
      testSouthResize(50, 551, 76, 53);
      testSouthResize(70, 571, 76, 55);
      testSouthResize(90, 591, 76, 57);
      testSouthResize(110, 611, 76, 59);
      testSouthResize(130, 631, 76, 61);
      testSouthResize(150, 651, 76, 63);
      testSouthResize(170, 671, 76, 65);
      testSouthResize(190, 691, 76, 67);
      testSouthResize(210, 711, 76, 69);
      testSouthResize(230, 731, 76, 71);

      testNorthResize(45, 544, 76, 52);
      testNorthResize(60, 559, 76, 53);
      testNorthResize(75, 574, 76, 55);
      testNorthResize(90, 589, 76, 56);
      testNorthResize(105, 604, 76, 58);
      testNorthResize(120, 619, 76, 59);

      testNorthEastResize(20, 25, 340, 524, 81, 50);
      testNorthEastResize(65, 65, 385, 564, 90, 54);
      testNorthEastResize(65, 70, 385, 569, 90, 54);
      testNorthEastResize(80, 85, 400, 584, 94, 56);
      testNorthEastResize(95, 100, 415, 599, 97, 57);
      testNorthEastResize(110, 115, 430, 614, 101, 58);
      testNorthEastResize(125, 130, 445, 621, 105, 59);


      testNorthWestResize(20, 25, 342, 525, 81, 49);
      testNorthWestResize(65, 65, 387, 565, 90, 53);
      testNorthWestResize(65, 70, 387, 570, 90, 54);
      testNorthWestResize(80, 85, 402, 585, 94, 55);
      testNorthWestResize(95, 100, 417, 600, 98, 57);
      testNorthWestResize(110, 115, 432, 615, 102, 58);
      testNorthWestResize(125, 130, 447, 621, 105, 59);

      testSouthEastResize(20, 25, 340, 526, 81, 50);
      testSouthEastResize(35, 40, 355, 541, 84, 51);
      testSouthEastResize(65, 65, 385, 566, 90, 54);
      testSouthEastResize(65, 70, 385, 571, 91, 54);
      testSouthEastResize(80, 85, 400, 586, 94, 56);
      testSouthEastResize(95, 100, 415, 601, 97, 57);
      testSouthEastResize(110, 115, 430, 616, 101, 59);
      testSouthEastResize(125, 130, 445, 631, 105, 60);
      testSouthEastResize(140, 145, 460, 646, 109, 61);
      testSouthEastResize(155, 160, 475, 661, 112, 64);
      testSouthEastResize(170, 175, 490, 676, 119, 65);
      testSouthEastResize(185, 190, 505, 691, 120, 67);
      testSouthEastResize(200, 205, 520, 706, 125, 69);


      testSouthWestResize(20, 25, 342, 526, 81, 50);
      testSouthEastResize(35, 40, 355, 541, 84, 51);
      testSouthWestResize(65, 65, 387, 566, 90, 54);
      testSouthWestResize(65, 70, 387, 571, 90, 54);
      testSouthWestResize(80, 85, 402, 586, 94, 56);
      testSouthWestResize(95, 100, 417, 601, 98, 57);
      testSouthWestResize(110, 115, 432, 616, 102, 59);
      testSouthWestResize(125, 130, 447, 631, 105, 60);
      testSouthWestResize(140, 160, 462, 661, 112, 64);
      testSouthWestResize(155, 160, 477, 661, 112, 64);
      testSouthWestResize(170, 175, 492, 676, 119, 65);
      testSouthWestResize(185, 190, 507, 691, 120, 67);
      testSouthWestResize(200, 205, 522, 706, 124, 68);

   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testAdd() {
      testExpression("=======+==+=+=+=+=+=+=+++=", "0", "", "0");
      testExpression("1+++++++++=", "2", "", "0");
      testExpression("1+=+==", "6", "", "0");
      testExpression("5+", "5", "5 + ", "0");
      testExpression("5+=", "10", "", "0");
      testExpression("5+==", "15", "", "0");
      testExpression("5++", "5", "5 + ", "0");
      testExpression("5+3-3=", "5", "", "0");
      testExpression("5+3×3=", "24", "", "0");
      testExpression("5+3÷3=", "2.666666666666667", "", "0");
      testExpression("5+3√3=", "8", "", "0");
      testExpression("5+3 sqr 3=", "8", "", "0");
      testExpression("5+3 1/x 3=", "8", "", "0");
      testExpression("5+3%3=", "8", "", "0");
      testExpression("5+3 ce 3=", "8", "", "0");
      testExpression("5+3±3=", "-28", "", "0");
      testExpression("71+==", "213", "", "0");
      testExpression("32+=", "64", "", "0");
      testExpression("61+", "61", "61 + ", "0");
      testExpression("77+", "77", "77 + ", "0");
      testExpression("68+", "68", "68 + ", "0");
      testExpression("18+", "18", "18 + ", "0");
      testExpression("71+67-", "138", "71 + 67 - ", "0");
      testExpression("34+41+", "75", "34 + 41 + ", "0");
      testExpression("77+43÷", "120", "77 + 43 ÷ ", "0");
      testExpression("73+34×", "107", "73 + 34 × ", "0");
      testExpression("95+25√", "5", "95 + √(25)", "0");
      testExpression("71+67 sqr", "4,489", "71 + sqr(67)", "0");
      testExpression("34+41 1/x", "0.024390243902439", "34 + 1/(41)", "0");
      testExpression("77+43%", "33.11", "77 + 33.11", "0");
      testExpression("62+10 ce =", "62", "", "0");
      testExpression("73+34±=", "39", "", "0");
      testExpression("62+10+==", "216", "", "0");
      testExpression("1+2+3+45+56+", "107", "1 + 2 + 3 + 45 + 56 + ", "0");
      testExpression("35817+70937+23578+32784+", "163,116", "35817 + 70937 + 23578 + 32784 + ", "0");
      testExpression("15833+96962+81736+75261+", "269,792", "15833 + 96962 + 81736 + 75261 + ", "0");
      testExpression("50413+92300+9811+4584+", "157,108", "50413 + 92300 + 9811 + 4584 + ", "0");
      testExpression("59253+18232+82376+36433+", "196,294", "59253 + 18232 + 82376 + 36433 + ", "0");
      testExpression("32187+84813+99628+13178+", "229,806", "32187 + 84813 + 99628 + 13178 + ", "0");
      testExpression("24090+52877+71904+50473+", "199,344", "24090 + 52877 + 71904 + 50473 + ", "0");
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testSubstract() {
      testExpression("5-", "5", "5 - ", "0");
      testExpression("5--", "5", "5 - ", "0");
      testExpression("5-=", "0", "", "0");
      testExpression("5-==", "-5", "", "0");
      testExpression("34-=", "0", "", "0");
      testExpression("5-3-3=", "-1", "", "0");
      testExpression("5-3×3=", "6", "", "0");
      testExpression("5-3÷3=", "0.6666666666666667", "", "0");
      testExpression("5-3√3=", "2", "", "0");
      testExpression("5-3 sqr 3=", "2", "", "0");
      testExpression("5-3 1/x 3=", "2", "", "0");
      testExpression("5-3%3=", "2", "", "0");
      testExpression("5-3 ce 3=", "2", "", "0");
      testExpression("5-3±3=", "38", "", "0");
      testExpression("5-3==", "-1", "", "0");
      testExpression("3-7=", "-4", "", "0");
      testExpression("14-9=", "5", "", "0");
      testExpression("76-47+", "29", "76 - 47 + ", "0");
      testExpression("78-45-", "33", "78 - 45 - ", "0");
      testExpression("48-87×", "-39", "48 - 87 × ", "0");
      testExpression("72-14÷", "58", "72 - 14 ÷ ", "0");
      testExpression("35-80√", "8.944271909999159", "35 - √(80)", "0");
      testExpression("35-80 sqr", "6,400", "35 - sqr(80)", "0");
      testExpression("35-80 1/x", "0.0125", "35 - 1/(80)", "0");
      testExpression("35-80 ce =", "35", "", "0");
      testExpression("58-47±=", "105", "", "0");
      testExpression("3-7-", "-4", "3 - 7 - ", "0");
      testExpression("14-9×", "5", "14 - 9 × ", "0");
      testExpression("76-47-", "29", "76 - 47 - ", "0");
      testExpression("78-45+", "33", "78 - 45 + ", "0");
      testExpression("72-14÷", "58", "72 - 14 ÷ ", "0");
      testExpression("48-87==", "-126", "", "0");
      testExpression("35-80===", "-205", "", "0");
      testExpression("58-47-=", "0", "", "0");
      testExpression("615-810-537-710-", "-1,442", "615 - 810 - 537 - 710 - ", "0");
      testExpression("668-70-990-294-", "-686", "668 - 70 - 990 - 294 - ", "0");
      testExpression("826-998-878-488-", "-1,538", "826 - 998 - 878 - 488 - ", "0");
      testExpression("340-70-701-92-", "-523", "340 - 70 - 701 - 92 - ", "0");
      testExpression("292-977-941-751-", "-2,377", "292 - 977 - 941 - 751 - ", "0");
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testMultiply() {
      testExpression("5×", "5", "5 × ", "0");
      testExpression("5××", "5", "5 × ", "0");
      testExpression("5×=", "25", "", "0");
      testExpression("5×==", "125", "", "0");
      testExpression("5×3+3=", "18", "", "0");
      testExpression("5×3-3=", "12", "", "0");
      testExpression("5×3×3=", "45", "", "0");
      testExpression("5×3÷3=", "5", "", "0");
      testExpression("5×3√3=", "15", "", "0");
      testExpression("5×3 sqr 3=", "15", "", "0");
      testExpression("5×3 1/x 3=", "15", "", "0");
      testExpression("5×3 % 3=", "15", "", "0");
      testExpression("5×3 ce 3=", "15", "", "0");
      testExpression("5×3±3=", "-165", "", "0");
      testExpression("5×3==", "45", "", "0");
      testExpression("10×95=", "950", "", "0");
      testExpression("32×19=", "608", "", "0");
      testExpression("59×23=", "1,357", "", "0");
      testExpression("11×65=", "715", "", "0");
      testExpression("10×16=", "160", "", "0");
      testExpression("59×50=", "2,950", "", "0");
      testExpression("46×97=", "4,462", "", "0");
      testExpression("20×22=", "440", "", "0");
      testExpression("10×95×", "950", "10 × 95 × ", "0");
      testExpression("32×19÷", "608", "32 × 19 ÷ ", "0");
      testExpression("59×23-", "1,357", "59 × 23 - ", "0");
      testExpression("11×65+", "715", "11 × 65 + ", "0");
      testExpression("10×16 sqr", "256", "10 × sqr(16)", "0");
      testExpression("59×50 1/x", "0.02", "59 × 1/(50)", "0");
      testExpression("46×97%=", "2,052.52", "", "0");
      testExpression("20×22 ce =", "0", "", "0");
      testExpression("20×22±=", "-440", "", "0");
      testExpression("496×550×802×294×", "64,322,966,400", "496 × 550 × 802 × 294 × ", "0");
      testExpression("222×150×440×133×", "1,948,716,000", "222 × 150 × 440 × 133 × ", "0");
      testExpression("244×896×689×408×", "61,457,829,888", "244 × 896 × 689 × 408 × ", "0");
      testExpression("33×436×752×273×", "2,953,798,848", "33 × 436 × 752 × 273 × ", "0");
      testExpression("557×146×57×28×", "129,789,912", "557 × 146 × 57 × 28 × ", "0");
      testExpression("12×2===========", "24,576", "", "0");
      testExpression("12×2===========-1-4-5×25=", "614,150", "", "0");
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testDivide() {
      testExpression("5÷", "5", "5 ÷ ", "0");
      testExpression("5÷÷", "5", "5 ÷ ", "0");
      testExpression("5÷=", "1", "", "0");
      testExpression("5÷==", "0.2", "", "0");
      testExpression("5÷3=", "1.666666666666667", "", "0");
      testExpression("5÷3+3=", "4.666666666666667", "", "0");
      testExpression("5÷3-3=", "-1.333333333333333", "", "0");
      testExpression("5÷3×3=", "5", "", "0");
      testExpression("5÷3÷3=", "0.5555555555555556", "", "0");
      testExpression("5÷3√3=", "1.666666666666667", "", "0");
      testExpression("5÷3 sqr 3=", "1.666666666666667", "", "0");
      testExpression("5÷3 1/x 3=", "1.666666666666667", "", "0");
      testExpression("5÷3%3=", "1.666666666666667", "", "0");
      testExpression("5÷3 ce 3=", "1.666666666666667", "", "0");
      testExpression("5÷3±3=", "-0.1515151515151515", "", "0");
      testExpression("5÷3==", "0.5555555555555556", "", "0");
      testExpression("100÷", "100", "100 ÷ ", "0");
      testExpression("56÷27=", "2.074074074074074", "", "0");
      testExpression("34÷81+", "0.4197530864197531", "34 ÷ 81 + ", "0");
      testExpression("-34÷81=", "-0.4197530864197531", "", "0");
      testExpression("79÷26-", "3.038461538461538", "79 ÷ 26 - ", "0");
      testExpression("58÷20÷", "2.9", "58 ÷ 20 ÷ ", "0");
      testExpression("15÷81×", "0.1851851851851852", "15 ÷ 81 × ", "0");
      testExpression("69÷34√", "5.8309518948453", "69 ÷ √(34)", "0");
      testExpression("22÷62 sqr", "3,844", "22 ÷ sqr(62)", "0");
      testExpression("22÷36 1/x", "0.0277777777777778", "22 ÷ 1/(36)", "0");
      testExpression("22÷36%=", "2.777777777777778", "", "0");
      testExpression("22÷36 ce =", "Cannot divide by zero", "22 ÷ ", "0");
      testExpression("22÷36±÷", "-0.6111111111111111", "22 ÷ -36 ÷ ", "0");
      testExpression("196÷789÷365÷850÷", "8.006952976513278e-7", "196 ÷ 789 ÷ 365 ÷ 850 ÷ ", "0");
      testExpression("903÷181÷665÷942÷", "7.96409858442778e-6", "903 ÷ 181 ÷ 665 ÷ 942 ÷ ", "0");
      testExpression("626÷161÷652÷18÷", "3.313052792914089e-4", "626 ÷ 161 ÷ 652 ÷ 18 ÷ ", "0");
      testExpression("77÷970÷410÷55÷", "3.52024138798089e-6", "77 ÷ 970 ÷ 410 ÷ 55 ÷ ", "0");
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testSqrt() {
      //SQRT
      testExpression("0√", "0", "√(0)", "0");
      testExpression("463√", "21.51743479135001", "√(463)", "0");
      testExpression("447√", "21.14237451186597", "√(447)", "0");
      testExpression("426√", "20.63976744055029", "√(426)", "0");
      testExpression("75√", "8.660254037844386", "√(75)", "0");
      testExpression("274√", "16.55294535724685", "√(274)", "0");
      testExpression("0√+√=", "0", "", "0");
      testExpression("0√+0√=", "0", "", "0");
      testExpression("362√+274√=", "35.5792429476873", "", "0");
      testExpression("272√-426√=", "-4.147344938079651", "", "0");
      testExpression("426√-272√=", "4.147344938079651", "", "0");
      testExpression("429√×463√=", "445.6758912034619", "", "0");
      testExpression("323√÷75√=", "2.075250988836451", "", "0");
      testExpression("361√×447√=", "401.7051157254535", "", "0");
      testExpression("427√√", "4.545764877308529", "√(√(427))", "0");
      testExpression("118√√", "3.295873251689181", "√(√(118))", "0");
      testExpression("345√√", "4.309776748395062", "√(√(345))", "0");
      testExpression("493√√", "4.71206996034381", "√(√(493))", "0");
      testExpression("295√√", "4.144341206671776", "√(√(295))", "0");
      testExpression("168√√ sqr 26√√=", "2.258100864353226", "", "0");
      testExpression("485√√ ce 380√√=", "4.415154435534269", "", "0");
      testExpression("214√√ ± 96√√=", "3.130169160146575", "", "0");
      testExpression("474√√ % 406√√=", "4.488812947719016", "", "0");
      testExpression("87√√ 1/x 267√√=", "4.042293240027026", "", "0");
   }


   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testSquare() {
      //SQUARE
      testExpression("0 sqr", "0", "sqr(0)", "0");
      testExpression("463 sqr", "214,369", "sqr(463)", "0");
      testExpression("447 sqr", "199,809", "sqr(447)", "0");
      testExpression("426 sqr", "181,476", "sqr(426)", "0");
      testExpression("75 sqr", "5,625", "sqr(75)", "0");
      testExpression("274 sqr", "75,076", "sqr(274)", "0");
      testExpression("0 sqr + sqr =", "0", "", "0");
      testExpression("0 sqr +0 sqr =", "0", "", "0");
      testExpression("362 sqr +274 sqr =", "206,120", "", "0");
      testExpression("272 sqr -426 sqr =", "-107,492", "", "0");
      testExpression("429 sqr ×463 sqr =", "39,452,685,129", "", "0");
      testExpression("323 sqr ÷75 sqr =", "18.54737777777778", "", "0");
      testExpression("361 sqr ×447 sqr =", "26,039,308,689", "", "0");
      testExpression("8 sqr sqr", "4,096", "sqr(sqr(8))", "0");
      testExpression("8 sqr sqr +8 sqr sqr =", "8,192", "", "0");
      testExpression("8 sqr sqr sqr +8 sqr sqr sqr =", "33,554,432", "", "0");
      testExpression("8 sqr +", "64", "sqr(8) + ", "0");
      testExpression("8 sqr sqr ×", "4,096", "sqr(sqr(8)) × ", "0");
      testExpression("5+ sqr sqr =", "630", "", "0");
      testExpression("2 sqr sqr +2 sqr =", "20", "", "0");
      testExpression("2 sqr sqr +2 sqr sqr =", "32", "", "0");
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testNegate() {
      //NEGATE
      testExpression("0±", "0", "", "0");
      testExpression("463±", "-463", "", "0");
      testExpression("463±±", "463", "", "0");
      testExpression("447±", "-447", "", "0");
      testExpression("447±±", "447", "", "0");
      testExpression("426±", "-426", "", "0");
      testExpression("426±±", "426", "", "0");
      testExpression("75±", "-75", "", "0");
      testExpression("75±±", "75", "", "0");
      testExpression("274±", "-274", "", "0");
      testExpression("274±±=", "274", "", "0");
      testExpression("0±+±=", "0", "", "0");
      testExpression("0±+0±=", "0", "", "0");
      testExpression("362±274±=", "362,274", "", "0");
      testExpression("272±+426±=", "-698", "", "0");
      testExpression("429±×463±=", "198,627", "", "0");
      testExpression("323±÷75±=", "4.306666666666667", "", "0");
      testExpression("361±×447±=", "161,367", "", "0");
      testExpression("300±±", "300", "", "0");
      testExpression("355±±", "355", "", "0");
      testExpression("428±±", "428", "", "0");
      testExpression("314±±", "314", "", "0");
      testExpression("134±±", "134", "", "0");
      testExpression("17±±×379±±=", "6,443", "", "0");
      testExpression("364±±×195±±+", "70,980", "364 × 195 + ", "0");
      testExpression("300±±×481±±-", "144,300", "300 × 481 - ", "0");
      testExpression("245±±×116±±×", "28,420", "245 × 116 × ", "0");
      testExpression("427±±×474±±=", "202,398", "", "0");
      testExpression("427±±×474 ce =", "0", "", "0");
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testFraction() {
      //FACTORIAL
      testExpression("463 1/x ", "0.0021598272138229", "1/(463)", "0");
      testExpression("447 1/x ", "0.0022371364653244", "1/(447)", "0");
      testExpression("426 1/x ", "0.0023474178403756", "1/(426)", "0");
      testExpression("75 1/x ", "0.0133333333333333", "1/(75)", "0");
      testExpression("274 1/x ", "0.0036496350364964", "1/(274)", "0");
      testExpression("362 1/x +274 1/x =", "0.0064120659757229", "", "0");
      testExpression("272 1/x -426 1/x =", "0.0013290527478597", "", "0");
      testExpression("429 1/x ×463 1/x +", "5.034562269983436e-6", "1/(429) × 1/(463) + ", "0");
      testExpression("323 1/x ÷75 1/x =", "0.2321981424148607", "", "0");
      testExpression("361 1/x ×447 1/x =", "6.197053920566163e-6", "", "0");
      testExpression("456 1/x  1/x", "456", "1/(1/(456))", "0");
      testExpression("333 1/x  1/x", "333", "1/(1/(333))", "0");
      testExpression("139 1/x  1/x", "139", "1/(1/(139))", "0");
      testExpression("35 1/x  1/x", "35", "1/(1/(35))", "0");
      testExpression("259 1/x  1/x", "259", "1/(1/(259))", "0");
      testExpression("148 1/x 1/x -244 1/x 1/x =", "-96", "", "0");
      testExpression("375 1/x 1/x -189 1/x 1/x =", "186", "", "0");
      testExpression("194 1/x 1/x -165 1/x 1/x =", "29", "", "0");
      testExpression("475 1/x 1/x -35 1/x 1/x =", "440", "", "0");
      testExpression("192 1/x 1/x -459 1/x 1/x =", "-267", "", "0");
      testExpression("777777777 1/x ===", "1.285714287e-9", "", "0");
      testExpression("7777777559 1/x ==", "1.285714321879593e-10", "", "0");
      testExpression("7777777559 1/x ==", "1.285714321879593e-10", "", "0");
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testPercent() {
      //PERCENT
      testExpression("0%", "0", "0", "0");
      testExpression("3%", "0", "0", "0");
      testExpression("4%", "0", "0", "0");
      testExpression("%", "0", "0", "0");
      testExpression("123%", "0", "0", "0");
      testExpression("0+3%", "0", "0 + 0", "0");
      testExpression("4+3%", "0.12", "4 + 0.12", "0");
      testExpression("4+3%%", "0.0048", "4 + 0.0048", "0");
      testExpression("4+3%+%", "0.169744", "4 + 0.12 + 0.169744", "0");
      testExpression("4+3%==%", "0.179776", "0.179776", "0");
      testExpression("4+3%==%%", "0.0076225024", "0.0076225024", "0");
      testExpression("9+1% ce", "0", "9 + ", "0");
      testExpression("9%+", "0", "0 + ", "0");
      testExpression("8%8+", "8", "8 + ", "0");
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testClearEnter() {
      //CLEAR ENTER
      testExpression("200 ce", "0", "", "0");
      testExpression("200+ ce", "0", "200 + ", "0");
      testExpression("200+ ce =", "200", "", "0");
      testExpression("200+100 ce =", "200", "", "0");
      testExpression("200+100= ce", "0", "", "0");
      testExpression("200+100=+ ce ", "0", "300 + ", "0");
      testExpression("200+100=+ ce =", "300", "", "0");
      testExpression("200+100=- ce ", "0", "300 - ", "0");
      testExpression("200+100=- ce =", "300", "", "0");
      testExpression("200+100=× ce ", "0", "300 × ", "0");
      testExpression("200+100=× ce =", "0", "", "0");
      testExpression("200+100=÷ ce ", "0", "300 ÷ ", "0");
      testExpression("200+100=% ce ", "0", "", "0");
      testExpression("200+100=% ce =", "100", "", "0");
      testExpression("200+100=± ce ", "0", "", "0");
      testExpression("200+100=± ce =", "100", "", "0");
      testExpression("200+100=√ ce ", "0", "", "0");
      testExpression("200+100=√ ce =", "100", "", "0");
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testPeriod() {
      //PERIOD
      testExpression("3 1/x +2=", "2.333333333333333", "", "0");
      testExpression("3 1/x +2=", "2.333333333333333", "", "0");
      testExpression("3 1/x +2=", "2.333333333333333", "", "0");
      testExpression("11÷3=", "3.666666666666667", "", "0");
      testExpression("5+5= 1/x =", "5.1", "", "0");
      testExpression("5+5= 1/x ==", "10.1", "", "0");
      testExpression("5±+5±= 1/x =", "-5.1", "", "0");
      testExpression("5±+5±= 1/x ==", "-10.1", "", "0");
      testExpression("50+50= 1/x =", "50.01", "", "0");
      testExpression("50+50= 1/x ==", "100.01", "", "0");
      testExpression("500+500= 1/x =", "500.001", "", "0");
      testExpression("500+500= 1/x ==", "1,000.001", "", "0");
      testExpression("2.000000000000001+8=", "10", "", "0");

   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testDivideByZero() {
      //Divide by zero
      testExpression("0 1/x", "Cannot divide by zero", "1/(0)", "0");
      testExpression("100 ÷ 0 =", "Cannot divide by zero", "100 ÷ ", "0");
      testExpression("200 ÷ 0 =", "Cannot divide by zero", "200 ÷ ", "0");
      testExpression("700 ÷ 0 =", "Cannot divide by zero", "700 ÷ ", "0");
      testExpression("500 ÷ 0 =", "Cannot divide by zero", "500 ÷ ", "0");
      testExpression("500 ÷ 700 ce =", "Cannot divide by zero", "500 ÷ ", "0");
      testExpression("200 + 100 = ÷ ce =", "Cannot divide by zero", "300 ÷ ", "0");

      //Zero divide by zero
      testExpression("0 ÷ 100 =", "0", "", "0");
      testExpression("0 ÷ 200 =", "0", "", "0");
      testExpression("0 ÷ 700 =", "0", "", "0");
      testExpression("0 ÷ 500 =", "0", "", "0");
      testExpression("0 ÷ 0 =", "Result is undefined", "0 ÷ ", "0");
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testInvalidInput() {
      //Square root of negative number
      testExpression("100 - = = √", "Invalid input", "√(-100)", "0");
      testExpression("200 - = = √", "Invalid input", "√(-200)", "0");
      testExpression("700 - = = √", "Invalid input", "√(-700)", "0");
      testExpression("500 - = = √", "Invalid input", "√(-500)", "0");
      testExpression("1000 - 2000 = √", "Invalid input", "√(-1000)", "0");
      testExpression("200 - 210 = √", "Invalid input", "√(-10)", "0");
      testExpression("700 - 2300 = √", "Invalid input", "√(-1600)", "0");
      testExpression("999 ± = √", "Invalid input", "√(-999)", "0");
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testMemory() {
      //ADD
      testExpression("500 m+ mr", "500", "", "500");
      testExpression("500 m+ + 100 mr", "500", "500 + ", "500");
      testExpression("500 m+ + 100 + mr", "500", "500 + 100 + ", "500");
      testExpression("500 m+ + 100 = mr", "500", "", "500");
      testExpression("500 m+ + 100 + mr =", "1,100", "", "500");
      testExpression("500 m+ m+ m+ mr", "1,500", "", "1,500");
      testExpression("500 m+ m+ m+ + 100 mr", "1,500", "500 + ", "1,500");
      testExpression("500 m+ m+ m+ + 100 + mr", "1,500", "500 + 100 + ", "1,500");
      testExpression("500 m+ m+ m+ + 100 = mr", "1,500", "", "1,500");
      testExpression("500 m+ m+ m+ + 100 + mr = ", "2,100", "", "1,500");
      //SUBSTRACT
      testExpression("500 m- mr", "-500", "", "-500");
      testExpression("500 m- + 100 mr ", "-500", "500 + ", "-500");
      testExpression("500 m- + 100 + mr ", "-500", "500 + 100 + ", "-500");
      testExpression("500 m- + 100 = mr ", "-500", "", "-500");
      testExpression("500 m- + 100 + mr =", "100", "", "-500");
      testExpression("500 m- m- m- mr", "-1,500", "", "-1,500");
      testExpression("500 m- m- m- + 100 mr", "-1,500", "500 + ", "-1,500");
      testExpression("500 m- m- m- + 100 = mr", "-1,500", "", "-1,500");
      testExpression("500 m- m- m- + 100 + mr", "-1,500", "500 + 100 + ", "-1,500");
      testExpression("500 m- m- m- + 100 + mr =", "-900", "", "-1,500");
   }

   private void testExpression(String commandsSequence, String expectedUnit, String expectedHistory, String expectedMemory) {
      for (String c : commandsSequence.split(" ")) {
         if (buttonCommands.get(c) == null) {
            for (char o : c.toCharArray()) {
               clickOnNode(buttonCommands.get(String.valueOf(o)));
            }
         } else {
            clickOnNode(buttonCommands.get(c));
         }
      }
      verifyThat("#mainLabel", hasText(expectedUnit));
      verifyThat( "#history-label", hasText(expectedHistory));
      verifyThat( "#memoryLabel", hasText(expectedMemory));
      clickOnNode("#btn-c");
      clickOnNode("#m-minus");
      clickOnNode("#mc");
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testHotkeys() {
      testNumHotkey("0", VK_0);
      testNumHotkey("0.", VK_DECIMAL);
      testNumHotkey("1", VK_1);
      testNumHotkey("2", VK_2);
      testNumHotkey("3", VK_3);
      testNumHotkey("4", VK_4);
      testNumHotkey("5", VK_5);
      testNumHotkey("6", VK_6);
      testNumHotkey("7", VK_7);
      testNumHotkey("8", VK_8);
      testNumHotkey("9", VK_9);
      testNumHotkey("0", VK_NUMPAD0);
      testNumHotkey("0.", VK_PERIOD);
      testNumHotkey("1", VK_NUMPAD1);
      testNumHotkey("2", VK_NUMPAD2);
      testNumHotkey("3", VK_NUMPAD3);
      testNumHotkey("4", VK_NUMPAD4);
      testNumHotkey("5", VK_NUMPAD5);
      testNumHotkey("6", VK_NUMPAD6);
      testNumHotkey("7", VK_NUMPAD7);
      testNumHotkey("8", VK_NUMPAD8);
      testNumHotkey("9", VK_NUMPAD9);
      testOperationHotkey("", VK_EQUALS);
      testOperationHotkey("", VK_ENTER);
      testOperationHotkey("6 - ", VK_MINUS);
      testOperationHotkey("6 ÷ ", VK_DIVIDE);
      testOperationHotkey("sqr(6)", VK_Q);
      testOperationHotkey("1/(6)", VK_R);
      testOperationHotkey("negate(6)", VK_F9);
      testOperationHotkey("6 × ", VK_SHIFT, VK_8);
      testOperationHotkey("√(6)", VK_SHIFT, VK_2);
      testOperationHotkey("0.36", VK_SHIFT, VK_5);
      testOperationHotkey("6 + ", VK_SHIFT, VK_EQUALS);
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testMemoryPopUpWindow() {
      clickOnNode("#btn-8");
      clickOnNode("#m-plus");
      clickOnNode("#m");
      verifyThat("#memoryLabel", hasText("8"));
      assertEquals(1.0, (double) lookup("#memoryLabel").query().opacityProperty().getValue());
      clickOnFx("#m-plus");
      clickOnFx("#main-memory-plus");
      verifyThat("#memoryLabel", hasText("24"));
      assertEquals(1.0, (double) lookup("#memoryLabel").query().opacityProperty().getValue());
      clickOnFx("#memory-pane");
      assertEquals(1.0, (double) lookup("#memoryLabel").query().opacityProperty().getValue());
      verifyThat("#mainLabel", hasText("24"));
      clickOnFx("#main-memory-substract");
      clickOnFx("#main-memory-substract");
      verifyThat("#memoryLabel", hasText("-24"));
      clickOnFx("#mmc");
      verifyThat("#mc", Node::isDisabled);
      verifyThat("#mr", Node::isDisabled);
      verifyThat("#m", Node::isDisabled);
      assertEquals(0, (double) lookup("#memory-pane").query().opacityProperty().getValue());
      verifyThat("#memory-empty", hasText("There's nothing saved in memory"));
      clickOnFx("#title_bar");
      WaitForAsyncUtils.waitForFxEvents();
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testHistoryPopUpWindow() {
      clickOnFx("#clock_btn");
      verifyThat("#mHistory", hasText("There's no history yet"));
      clickOnFx("#title_bar");
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testOptionalPopUpWindow() {
      clickOnFx("#opt_btn");
      verifyThat("#opt", hasText("Calculator"));
      clickOnFx("#title_bar");
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testWindowButtons() throws TimeoutException {
      clickOnNode("#close_btn");
      assertFalse(primaryStage.isShowing());
      FxToolkit.showStage();
      clickOnNode("#maximize_btn");
      assertTrue(primaryStage.isMaximized());
      clickOn("#maximize_btn");
      assertFalse(primaryStage.isMaximized());
      clickOnNode("#iconify_btn");
      assertTrue(primaryStage.isIconified());
      Platform.runLater(() -> primaryStage.setIconified(false));
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testHistoryScroll() {
      clickOnNode("#btn-2");
      for (int i = 0; i < 15; i++) {
         clickOnNode("#btn-square");
      }
      ScrollPane scrollPane = lookup("#history-scroll").query();
      Label historyLabel = lookup("#history-label").query();
      Bounds historyBounds = historyLabel.localToScreen(historyLabel.getBoundsInLocal());
      int border = 3;
      int arrowY = 10;
      robot.mouseMove((int) (primaryStage.getX() + border), (int) historyBounds.getMinY() + arrowY);
      hold();
      assertEquals(scrollPane.getHvalue(), 0);
      robot.mouseMove((int) (primaryStage.getX() + primaryStage.getWidth() - border), (int) historyBounds.getMinY() + arrowY);
      hold();
      assertEquals(scrollPane.getHvalue(), 1);
   }

   @Test
   @EnabledOnOs(OS.WINDOWS)
   void testOptionalScroll() {
      ScrollPane scrollPane = lookup("#option-scroll").query();
      int border = 3;
      int arrowY = 10;
      clickOnNode("#opt_btn");
      robot.delay(500);
      Bounds optionScrollBounds = scrollPane.localToScreen(scrollPane.getBoundsInLocal());
      int toggleX = (int) (optionScrollBounds.getMaxX() - border);
      robot.mouseMove(toggleX, (int) optionScrollBounds.getMinY() + arrowY);
      robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
      robot.mouseMove(toggleX, (int) optionScrollBounds.getMaxY());
      WaitForAsyncUtils.waitForFxEvents();
      assertEquals(scrollPane.getVvalue(), 1);
      robot.mouseMove(toggleX, (int) optionScrollBounds.getMinY());
      robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
      WaitForAsyncUtils.waitForFxEvents();
      assertEquals(scrollPane.getVvalue(), 0);
   }

   private void hold() {
      robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
      robot.delay(3500);
      robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
   }

   private void testMemoryButton(String btnSelector) {
      Button button = lookup("#m").query();
      verifyThat("#m", Node::isVisible);
      assertTrue(button.isDisabled());
      clickOnNode(btnSelector);
      verifyThat(btnSelector, Node::isVisible);
      assertTrue(!button.isDisabled());
      clickOnNode("#mc");
   }

   private void clickOnNode(String btnSelector) {
      Button button = lookup(btnSelector).query();
      Bounds screenBounds = button.localToScreen(button.getBoundsInLocal());
      int x = (int) (screenBounds.getMinX() + (button.getWidth() / 2));
      int y = (int) (screenBounds.getMinY() + (button.getWidth() / 2));
      robot.mouseMove(x, y);
      robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
      robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
      WaitForAsyncUtils.waitForFxEvents();
   }

   private void testMainButtonsResize(double expectedWidth, double expectedHeight) {
      mainButtons.forEach(button -> {
         assertTrue(Math.abs(expectedWidth - button.getWidth()) < 5, button.getId() + " " + button.getWidth() + " " + button.getHeight());
         assertTrue(Math.abs(expectedHeight - button.getHeight()) < 2, button.getId() + " " + button.getWidth() + " " + button.getHeight());
      });
   }

   private void drag(int moveX, int moveY, int endX, int endY) {
      int middleX = (int) (primaryStage.getWidth() / 2 + primaryStage.getX());
      int middleY = (int) (primaryStage.getHeight() / 2 + primaryStage.getY());
      robot.mouseMove(moveX, moveY);
      robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
      robot.mouseMove(endX, endY);
      robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
      robot.mouseMove(middleX, middleY);
      WaitForAsyncUtils.waitForFxEvents();
   }

   private void testNorthEastResize(int resizeX, int resizeY, int expectedWidth, int expectedHeight, double expWidthMB, double expHeightMB) {
      int startWidth = (int) (primaryStage.getX() + BORDER);
      int startHeight = (int) (primaryStage.getY() + BORDER);
      int endX = startWidth - resizeX;
      int endY = startHeight - resizeY;
      testResizeNodes(expectedWidth, expectedHeight, expWidthMB, expHeightMB, startWidth, startHeight, endX, endY);
   }


   private void testNorthWestResize(int resizeX, int resizeY, int expectedWidth, int expectedHeight, double expWidthMB, double expHeightMB) {
      int startWidth = (int) ((primaryStage.getX() + primaryStage.getWidth()) - 1);
      int startHeight = (int) (primaryStage.getY() + 1);
      int endX = startWidth + resizeX;
      int endY = startHeight - resizeY;
      testResizeNodes(expectedWidth, expectedHeight, expWidthMB, expHeightMB, startWidth, startHeight, endX, endY);
   }

   private void testSouthWestResize(int resizeX, int resizeY, int expectedWidth, int expectedHeight, double expWidthMB, double expHeightMB) {
      int startWidth = (int) ((primaryStage.getX() + primaryStage.getWidth()) - BORDER);
      int startHeight = (int) ((primaryStage.getY() + primaryStage.getHeight()) - BORDER);
      int endX = startWidth + resizeX;
      int endY = startHeight + resizeY;
      testResizeNodes(expectedWidth, expectedHeight, expWidthMB, expHeightMB, startWidth, startHeight, endX, endY);
   }

   private void testSouthEastResize(int resizeX, int resizeY, int expectedWidth, int expectedHeight, double expWidthMB, double expHeightMB) {
      int startWidth = (int) (primaryStage.getX() + BORDER);
      int startHeight = (int) ((primaryStage.getY() + primaryStage.getHeight()) - BORDER);
      int endX = startWidth - resizeX;
      int endY = startHeight + resizeY;
      testResizeNodes(expectedWidth, expectedHeight, expWidthMB, expHeightMB, startWidth, startHeight, endX, endY);
   }

   private void testResizeNodes(int expectedWidth, int expectedHeight, double expWidthMB, double expHeightMB, int startWidth, int startHeight, int endX, int endY) {
      drag(startWidth, startHeight, endX, endY);
      assertEquals(expectedWidth, primaryStage.getWidth());
      assertEquals(expectedHeight, primaryStage.getHeight());
      testMainButtonsResize(expWidthMB, expHeightMB);
      drag(endX, endY, startWidth, startHeight);
   }

   private void testWestResize(int resizeX, int expectedWidth, double expWidthMB, double expHeightMB) {
      int startWidth = (int) ((primaryStage.getX() + primaryStage.getWidth()) - BORDER);
      int startHeight = (int) ((primaryStage.getY() + primaryStage.getHeight()) / 2);
      drag(startWidth, startHeight, startWidth + resizeX, startHeight);
      assertEquals(expectedWidth, (int) primaryStage.getWidth());
      testMainButtonsResize(expWidthMB, expHeightMB);
      drag(startWidth + resizeX, startHeight, startWidth, startHeight);
   }

   private void testEastResize(int resizeX, int expectedWidth, double expWidthMB, double expHeightMB) {
      int startWidth = (int) (primaryStage.getX() + BORDER);
      int startHeight = (int) ((primaryStage.getY() + primaryStage.getMinHeight()) / BORDER);
      drag(startWidth, startHeight, startWidth - resizeX, startHeight);
      assertEquals(expectedWidth, primaryStage.getWidth());
      testMainButtonsResize(expWidthMB, expHeightMB);
      drag(startWidth - resizeX, startHeight, startWidth - BORDER, startHeight);
   }

   private void testSouthResize(int resizeY, int expectedHeight, double expWidthMB, double expHeightMB) {
      int startWidth = (int) (primaryStage.getX() + (primaryStage.getMinWidth() / BORDER));
      int startHeight = (int) ((primaryStage.getY() + primaryStage.getHeight()) - BORDER);
      drag(startWidth, startHeight, startWidth, startHeight + resizeY);
      assertEquals(expectedHeight, primaryStage.getHeight());
      testMainButtonsResize(expWidthMB, expHeightMB);
      drag(startWidth, startHeight + resizeY, startWidth, startHeight);
   }

   private void testNorthResize(int resizeY, int expectedHeight, double expWidthMB, double expHeightMB) {
      int startWidth = (int) (primaryStage.getX() + (primaryStage.getMinWidth() / BORDER));
      int startHeight = (int) (primaryStage.getY() + BORDER);
      drag(startWidth, startHeight, startWidth, startHeight - resizeY);
      assertEquals(expectedHeight, primaryStage.getHeight());
      testMainButtonsResize(expWidthMB, expHeightMB);
      drag(startWidth, startHeight - resizeY, startWidth, startHeight);
   }


   private void testDrag(int moveX, int moveY, int expectedX, int expectedY) {
      Pane titleBar = lookup("#title_bar").query();
      int startX = (int) (primaryStage.getX() + (titleBar.getWidth() / BORDER));
      int startY = (int) (primaryStage.getY() + (titleBar.getHeight() / BORDER));
      drag(startX, startY, moveX, moveY);
      assertEquals(expectedX, primaryStage.getX());
      assertEquals(expectedY, primaryStage.getY());
   }

   private void clickOnFx(String selector) {
      clickOn(selector);
      WaitForAsyncUtils.waitForFxEvents();
   }

   private void testOperationHotkey(String expected, int... hotkeys) {
      press(VK_2);
      press(VK_SHIFT, VK_EQUALS);
      press(VK_4);
      press(VK_EQUALS);
      press(hotkeys);
      WaitForAsyncUtils.waitForFxEvents();
      Label historyLabel = lookup("#history-label").query();
      String actual = historyLabel.getText();
      assertEquals(expected, actual);
      press(VK_ESCAPE);
   }

   private void testNumHotkey(String expected, int hotkey) {
      press(hotkey);
      WaitForAsyncUtils.waitForFxEvents();
      Label mainLabel = lookup("#mainLabel").query();
      assertEquals(mainLabel.getText(), expected);
      press(VK_ESCAPE);
   }

   private void press(int... hotkeys) {
      for (int hotkey : hotkeys) {
         robot.keyPress(hotkey);
      }
      for (int hotkey : hotkeys) {
         robot.keyRelease(hotkey);
      }
   }
}