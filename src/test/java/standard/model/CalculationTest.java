package standard.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import standard.model.data.Operator;
import standard.model.exceptions.CannotDivideByZeroException;
import standard.model.exceptions.InvalidInputException;
import standard.model.exceptions.OverflowException;
import standard.model.exceptions.ResultIsUndefinedException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static standard.utils.NodesInformation.*;

@Slf4j
class CalculationTest {
   private static Calculation calculator = new Calculation();
   private static Map<String, Operation> commands = new HashMap<>();

   @BeforeAll
   static void fillMap() {
      commands.put("+", () -> calculator.createOperation(Operator.ADD));
      commands.put("-", () -> calculator.createOperation(Operator.SUBTRACT));
      commands.put("×", () -> calculator.createOperation(Operator.MULTIPLY));
      commands.put("÷", () -> calculator.createOperation(Operator.DIVIDE));
      commands.put("±", () -> calculator.createOperation(Operator.NEGATE));
      commands.put("√", () -> calculator.createOperation(Operator.SQRT));
      commands.put("sqr", () -> calculator.createOperation(Operator.SQUARE));
      commands.put("1/x", () -> calculator.createOperation(Operator.ONE_BY_X));
      commands.put("%", () -> calculator.createOperation(Operator.PERCENT));
      commands.put("ce", () -> calculator.clearEnter());
      commands.put("c", () -> calculator.clearAll());
      commands.put("m-", () -> calculator.memorySubstract());
      commands.put("m+", () -> calculator.memoryAdd());
      commands.put("mr", () -> calculator.setCurrentDecimalFromMemory());
      commands.put("mc", () -> calculator.memoryClear());
      commands.put("=", () -> calculator.getResultDecimal());
   }

   @Test
   void testAdd() throws Exception {
      testCalculation("= = = = = = = + = = + = + = + = +", "0", 1, "0");
      testCalculation("1 + + + + + + + + + =", "2", 0, "0");
      testCalculation("1 + = + = =", "6", 0, "0");
      testCalculation("5 + ", "5", 1, "0");
      testCalculation("5 + =", "10", 0, "0");
      testCalculation("5 + = =", "15", 0, "0");
      testCalculation("5 + +", "5", 1, "0");
      testCalculation("5 + 3 - 3 =", "5", 0, "0");
      testCalculation("5 + 3 × 3 =", "24", 0, "0");
      testCalculation("5 + 3 ÷ 3 =", "2.6666666666666667", 0, "0");
      testCalculation("5 + 3 √ 3 =", "8", 0, "0");
      testCalculation("5 + 3 sqr 3 =", "8", 0, "0");
      testCalculation("5 + 3 1/x 3 =", "8", 0, "0");
      testCalculation("5 + 3 % 3 =", "8", 0, "0");
      testCalculation("5 + 3 ce 3 =", "8", 0, "0");
      testCalculation("71 + = =", "213", 0, "0");
      testCalculation("32 + =", "64", 0, "0");
      testCalculation("61 +", "61", 1, "0");
      testCalculation("77 +", "77", 1, "0");
      testCalculation("68 +", "68", 1, "0");
      testCalculation("18 +", "18", 1, "0");
      testCalculation("71 + 67 -", "138", 2, "0");
      testCalculation("34 + 41 +", "75", 2, "0");
      testCalculation("77 + 43 ÷", "120", 2, "0");
      testCalculation("73 + 34 ×", "107", 2, "0");
      testCalculation("95 + 25 √", "5", 2, "0");
      testCalculation("71 + 67 sqr", "4,489", 2, "0");
      testCalculation("34 + 41 1/x", "0.024390243902439", 2, "0");
      testCalculation("77 + 43 %", "33.11", 2, "0");
      testCalculation("62 + 10 ce =", "62", 0, "0");
      testCalculation("73 + 34 ± =", "39", 0, "0");
      testCalculation("62 + 10 + = =", "216", 0, "0");
      testCalculation("1 + 2 + 3 + 45 + 56 +", "107", 5, "0");
      testCalculation("35817 + 70937 + 23578 + 32784 +", "163,116", 4, "0");
      testCalculation("15833 + 96962 + 81736 + 75261 +", "269,792", 4, "0");
      testCalculation("50413 + 92300 + 9811 + 4584 +", "157,108", 4, "0");
      testCalculation("59253 + 18232 + 82376 + 36433 +", "196,294", 4, "0");
      testCalculation("32187 + 84813 + 99628 + 13178 +", "229,806", 4, "0");
      testCalculation("24090 + 52877 + 71904 + 50473 +", "199,344", 4, "0");
   }

   @Test
   void testSubstract() throws Exception {
      testCalculation("5 -", "5", 1, "0");
      testCalculation("5 - -", "5", 1, "0");
      testCalculation("5 - =", "0", 0, "0");
      testCalculation("5 - = =", "-5", 0, "0");
      testCalculation("34 - =", "0", 0, "0");
      testCalculation("5 - 3 - 3 =", "-1", 0, "0");
      testCalculation("5 - 3 × 3 =", "6", 0, "0");
      testCalculation("5 - 3 ÷ 3 =", "0.6666666666666667", 0, "0");
      testCalculation("5 - 3 √ 3 =", "2", 0, "0");
      testCalculation("5 - 3 sqr 3 =", "2", 0, "0");
      testCalculation("5 - 3 1/x 3 =", "2", 0, "0");
      testCalculation("5 - 3 % 3 =", "2", 0, "0");
      testCalculation("5 - 3 ce 3 =", "2", 0, "0");
      testCalculation("5 - 3 = =", "-1", 0, "0");
      testCalculation("3 - 7 =", "-4", 0, "0");
      testCalculation("14 - 9 =", "5", 0, "0");
      testCalculation("76 - 47 +", "29", 2, "0");
      testCalculation("78 - 45 -", "33", 2, "0");
      testCalculation("48 - 87 ×", "-39", 2, "0");
      testCalculation("72 - 14 ÷", "58", 2, "0");
      testCalculation("35 - 80 √", "8.9442719099991588", 2, "0");
      testCalculation("35 - 80 sqr", "6,400", 2, "0");
      testCalculation("35 - 80 1/x", "0.0125", 2, "0");
      testCalculation("35 - 80 ce =", "35", 0, "0");
      testCalculation("58 - 47 ± =", "105", 0, "0");
      testCalculation("3 - 7 -", "-4", 2, "0");
      testCalculation("14 - 9 ×", "5", 2, "0");
      testCalculation("76 - 47 -", "29", 2, "0");
      testCalculation("78 - 45 +", "33", 2, "0");
      testCalculation("72 - 14 ÷", "58", 2, "0");
      testCalculation("48 - 87 = =", "-126", 0, "0");
      testCalculation("35 - 80 = = =", "-205", 0, "0");
      testCalculation("58 - 47 - =", "0", 0, "0");
      testCalculation("615 - 810 - 537 - 710 -", "-1,442", 4, "0");
      testCalculation("668 - 70 - 990 - 294 -", "-686", 4, "0");
      testCalculation("826 - 998 - 878 - 488 -", "-1,538", 4, "0");
      testCalculation("340 - 70 - 701 - 92 -", "-523", 4, "0");
      testCalculation("292 - 977 - 941 - 751 -", "-2,377", 4, "0");
   }

   @Test
   void testMultiply() throws Exception {
      testCalculation("5 ×", "5", 1, "0");
      testCalculation("5 × ×", "5", 1, "0");
      testCalculation("5 × =", "25", 0, "0");
      testCalculation("5 × = =", "125", 0, "0");
      testCalculation("5 × 3 + 3 =", "18", 0, "0");
      testCalculation("5 × 3 - 3 =", "12", 0, "0");
      testCalculation("5 × 3 × 3 =", "45", 0, "0");
      testCalculation("5 × 3 ÷ 3 =", "5", 0, "0");
      testCalculation("5 × 3 √ 3 =", "15", 0, "0");
      testCalculation("5 × 3 sqr 3 =", "15", 0, "0");
      testCalculation("5 × 3 1/x 3 =", "15", 0, "0");
      testCalculation("5 × 3 % 3 =", "15", 0, "0");
      testCalculation("5 × 3 ce 3 =", "15", 0, "0");
      testCalculation("5 × 3 = =", "45", 0, "0");
      testCalculation("10 × 95 =", "950", 0, "0");
      testCalculation("32 × 19 =", "608", 0, "0");
      testCalculation("59 × 23 =", "1,357", 0, "0");
      testCalculation("11 × 65 =", "715", 0, "0");
      testCalculation("10 × 16 =", "160", 0, "0");
      testCalculation("59 × 50 =", "2,950", 0, "0");
      testCalculation("46 × 97 =", "4,462", 0, "0");
      testCalculation("20 × 22 =", "440", 0, "0");
      testCalculation("10 × 95 ×", "950", 2, "0");
      testCalculation("32 × 19 ÷", "608", 2, "0");
      testCalculation("59 × 23 -", "1,357", 2, "0");
      testCalculation("11 × 65 +", "715", 2, "0");
      testCalculation("10 × 16 sqr", "256", 2, "0");
      testCalculation("59 × 50 1/x", "0.02", 2, "0");
      testCalculation("46 × 97 % =", "2,052.52", 0, "0");
      testCalculation("20 × 22 ce =", "0", 0, "0");
      testCalculation("20 × 22 ± =", "-440", 0, "0");
      testCalculation("496 × 550 × 802 × 294 ×", "64,322,966,400", 4, "0");
      testCalculation("222 × 150 × 440 × 133 ×", "1,948,716,000", 4, "0");
      testCalculation("244 × 896 × 689 × 408 ×", "61,457,829,888", 4, "0");
      testCalculation("33 × 436 × 752 × 273 ×", "2,953,798,848", 4, "0");
      testCalculation("557 × 146 × 57 × 28 ×", "129,789,912", 4, "0");
      testCalculation("12 × 2 = = = = = = = = = = = ", "24,576", 0, "0");
      testCalculation("12 × 2 = = = = = = = = = = = - 1 - 4 - 5 × 25 =", "614,150", 0, "0");
      testCalculation("0.0111111111111111 × 0.1 =", "0.0011111111111111", 0, "0");
   }

   @Test
   void testDivide() throws Exception {
      testCalculation("5 ÷", "5", 1, "0");
      testCalculation("5 ÷ ÷", "5", 1, "0");
      testCalculation("5 ÷ =", "1", 0, "0");
      testCalculation("5 ÷ = = ", "0.2", 0, "0");
      testCalculation("5 ÷ 3 = ", "1.6666666666666667", 0, "0");
      testCalculation("5 ÷ 3 + 3 =", "4.6666666666666667", 0, "0");
      testCalculation("5 ÷ 3 - 3 =", "-1.3333333333333333", 0, "0");
      testCalculation("5 ÷ 3 × 3 =", "5", 0, "0");
      testCalculation("5 ÷ 3 ÷ 3 =", "0.5555555555555556", 0, "0");
      testCalculation("5 ÷ 3 √ 3 =", "1.6666666666666667", 0, "0");
      testCalculation("5 ÷ 3 sqr 3 =", "1.6666666666666667", 0, "0");
      testCalculation("5 ÷ 3 1/x 3 =", "1.6666666666666667", 0, "0");
      testCalculation("5 ÷ 3 % 3 =", "1.6666666666666667", 0, "0");
      testCalculation("5 ÷ 3 ce 3 =", "1.6666666666666667", 0, "0");
      testCalculation("5 ÷ 3 = =", "0.5555555555555556", 0, "0");
      testCalculation("100 ÷", "100", 1, "0");
      testCalculation("56 ÷ 27 =", "2.0740740740740741", 0, "0");
      testCalculation("34 ÷ 81 +", "0.4197530864197531", 2, "0");
      testCalculation("-34 ÷ 81 =", "-0.4197530864197531", 0, "0");
      testCalculation("79 ÷ 26 -", "3.0384615384615385", 2, "0");
      testCalculation("58 ÷ 20 ÷", "2.9", 2, "0");
      testCalculation("15 ÷ 81 ×", "0.1851851851851852", 2, "0");
      testCalculation("69 ÷ 34 √", "5.8309518948453005", 2, "0");
      testCalculation("22 ÷ 62 sqr", "3,844", 2, "0");
      testCalculation("22 ÷ 36 1/x", "0.0277777777777778", 2, "0");
      testCalculation("22 ÷ 36 % =", "2.7777777777777778", 0, "0");
      testCalculation("22 ÷ 36 ± ÷", "-0.6111111111111111", 2, "0");
      testCalculation("196 ÷ 789 ÷ 365 ÷ 850 ÷", "8.006952976513278e-7", 4, "0");
      testCalculation("903 ÷ 181 ÷ 665 ÷ 942 ÷", "7.96409858442778e-6", 4, "0");
      testCalculation("626 ÷ 161 ÷ 652 ÷ 18 ÷", "3.313052792914089e-4", 4, "0");
      testCalculation("77 ÷ 970 ÷ 410 ÷ 55 ÷", "3.52024138798089e-6", 4, "0");
   }

   @Test
   void testSqrt() throws Exception {
      //SQRT
      testCalculation("0 √", "0", 1, "0");
      testCalculation("463 √", "21.5174347913500135", 1, "0");
      testCalculation("447 √", "21.1423745118659744", 1, "0");
      testCalculation("426 √", "20.6397674405502932", 1, "0");
      testCalculation("75 √", "8.6602540378443865", 1, "0");
      testCalculation("274 √", "16.5529453572468486", 1, "0");
      testCalculation("0 √ + √ =", "0", 0, "0");
      testCalculation("0 √ + 0 √ =", "0", 0, "0");
      testCalculation("362 √ + 274 √ =", "35.5792429476872967", 0, "0");
      testCalculation("272 √ - 426 √ =", "-4.147344938079651", 0, "0");
      testCalculation("426 √ - 272 √ =", "4.147344938079651", 0, "0");
      testCalculation("429 √ × 463 √ =", "445.6758912034618908", 0, "0");
      testCalculation("323 √ ÷ 75 √ =", "2.0752509888364508", 0, "0");
      testCalculation("361 √ × 447 √ =", "401.7051157254535139", 0, "0");
      testCalculation("427 √ √", "4.5457648773085289", 1, "0");
      testCalculation("118 √ √", "3.295873251689181", 1, "0");
      testCalculation("345 √ √", "4.3097767483950616", 1, "0");
      testCalculation("493 √ √", "4.7120699603438103", 1, "0");
      testCalculation("295 √ √", "4.1443412066717755", 1, "0");
      testCalculation("168 √ √ sqr 26 √ √ =", "2.2581008643532257", 0, "0");
      testCalculation("485 √ √ ce 380 √ √ =", "4.4151544355342688", 0, "0");
      testCalculation("214 √ √ ± 96 √ √ =", "3.1301691601465746", 0, "0");
      testCalculation("474 √ √ % 406 √ √ =", "4.4888129477190163", 0, "0");
      testCalculation("87 √ √ 1/x 267 √ √ =", "4.042293240027026", 0, "0");
   }


   @Test
   void testSquare() throws Exception {
      //SQUARE
      testCalculation("0 sqr", "0", 1, "0");
      testCalculation("463 sqr", "214,369", 1, "0");
      testCalculation("447 sqr", "199,809", 1, "0");
      testCalculation("426 sqr", "181,476", 1, "0");
      testCalculation("75 sqr", "5,625", 1, "0");
      testCalculation("274 sqr", "75,076", 1, "0");
      testCalculation("0 sqr + sqr =", "0", 0, "0");
      testCalculation("0 sqr + 0 sqr =", "0", 0, "0");
      testCalculation("362 sqr + 274 sqr =", "206,120", 0, "0");
      testCalculation("272 sqr - 426 sqr =", "-107,492", 0, "0");
      testCalculation("429 sqr × 463 sqr =", "39,452,685,129", 0, "0");
      testCalculation("323 sqr ÷ 75 sqr =", "18.5473777777777778", 0, "0");
      testCalculation("361 sqr × 447 sqr =", "26,039,308,689", 0, "0");
      testCalculation("8 sqr sqr", "4,096", 1, "0");
      testCalculation("8 sqr sqr + 8 sqr sqr =", "8,192", 0, "0");
      testCalculation("8 sqr sqr sqr + 8 sqr sqr sqr =", "33,554,432", 0, "0");
      testCalculation("8 sqr +", "64", 1, "0");
      testCalculation("8 sqr sqr ×", "4,096", 1, "0");
      testCalculation("5 + sqr sqr =", "630", 0, "0");
      testCalculation("2 sqr sqr + 2 sqr =", "20", 0, "0");
      testCalculation("2 sqr sqr + 2 sqr sqr =", "32", 0, "0");
      testCalculation("2 sqr sqr + 2 sqr sqr +", "32", 2, "0");
      testCalculation("2 sqr sqr + 2 sqr sqr = =", "48", 0, "0");
      testCalculation("2 sqr sqr + 2 sqr sqr sqr =", "272", 0, "0");
      testCalculation("2 sqr + 3 sqr +", "13", 2, "0");
      testCalculation("3 sqr + 3 sqr +", "18", 2, "0");
      testCalculation("3 sqr sqr + 3 sqr +", "90", 2, "0");
      testCalculation("2 sqr sqr + 3 sqr sqr +", "97", 2, "0");
      testCalculation("2 sqr sqr + 2 sqr sqr sqr +", "272", 2, "0");
      testCalculation("2 sqr sqr + 2 sqr sqr sqr = =", "528", 0, "0");
      testCalculation("2 sqr sqr + 2 sqr sqr sqr = = = sqr", "614,656", 1, "0");
      testCalculation("2 sqr sqr × 2 sqr sqr sqr = =", "1,048,576", 0, "0");
      testCalculation("2 sqr sqr sqr + 2 sqr sqr sqr + 2 sqr sqr sqr =", "768", 0, "0");
      testCalculation("2 sqr sqr sqr × 2 sqr sqr sqr × 2 sqr sqr sqr =", "16,777,216", 0, "0");
      testCalculation("2 sqr sqr sqr sqr × 2 sqr sqr sqr × 2 sqr sqr sqr =", "4,294,967,296", 0, "0");
      testCalculation("244 sqr sqr ", "3,544,535,296", 1, "0");
      testCalculation("151 sqr sqr ", "519,885,601", 1, "0");
      testCalculation("220 sqr sqr ", "2,342,560,000", 1, "0");
      testCalculation("488 sqr sqr ", "56,712,564,736", 1, "0");
      testCalculation("136 sqr sqr ÷ 39 sqr sqr =", "147.8758334446393921", 0, "0");
      testCalculation("104 sqr sqr ÷ 295 sqr sqr =", "0.0154470407871813", 0, "0");
      testCalculation("291 sqr sqr ÷ 331 sqr sqr =", "0.5973927956251664", 0, "0");
      testCalculation("157 sqr sqr ÷ 461 sqr sqr =", "0.0134522493285492", 0, "0");
      testCalculation("422 sqr sqr ÷ 284 sqr sqr =", "4.8750204704088643", 0, "0");
      testCalculation("8888 sqr = + sqr =", "6,240,454,042,940,480", 0, "0");
   }

   @Test
   void testNegate() throws Exception {
      //NEGATE
      testCalculation("0 ±", "0", 0, "0");
      testCalculation("463 ±", "-463", 0, "0");
      testCalculation("463 ± ±", "463", 0, "0");
      testCalculation("447 ±", "-447", 0, "0");
      testCalculation("447 ± ±", "447", 0, "0");
      testCalculation("426 ±", "-426", 0, "0");
      testCalculation("426 ± ±", "426", 0, "0");
      testCalculation("75 ±", "-75", 0, "0");
      testCalculation("75 ± ±", "75", 0, "0");
      testCalculation("274 ±", "-274", 0, "0");
      testCalculation("274 ± ± =", "274", 0, "0");
      testCalculation("0 ± + ± =", "0", 0, "0");
      testCalculation("0 ± + 0 ± =", "0", 0, "0");
      testCalculation("362 ± 274 ± =", "-274", 0, "0");
      testCalculation("272 ± + 426 ± =", "-698", 0, "0");
      testCalculation("429 ± × 463 ± =", "198,627", 0, "0");
      testCalculation("361 ± × 447 ± =", "161,367", 0, "0");
      testCalculation("300 ± ±", "300", 0, "0");
      testCalculation("355 ± ±", "355", 0, "0");
      testCalculation("428 ± ±", "428", 0, "0");
      testCalculation("314 ± ±", "314", 0, "0");
      testCalculation("134 ± ±", "134", 0, "0");
      testCalculation("17 ± ± × 379 ± ± =", "6,443", 0, "0");
      testCalculation("364 ± ± × 195 ± ± +", "70,980", 2, "0");
      testCalculation("300 ± ± × 481 ± ± -", "144,300", 2, "0");
      testCalculation("245 ± ± × 116 ± ± ×", "28,420", 2, "0");
      testCalculation("427 ± ± × 474 ± ± =", "202,398", 0, "0");
      testCalculation("427 ± ± × 474 ce =", "0", 0, "0");
   }

   @Test
   void testFraction() throws Exception {
      //FACTORIAL
      testCalculation("463 1/x ", "0.0021598272138229", 1, "0");
      testCalculation("447 1/x ", "0.0022371364653244", 1, "0");
      testCalculation("426 1/x ", "0.0023474178403756", 1, "0");
      testCalculation("75 1/x ", "0.0133333333333333", 1, "0");
      testCalculation("274 1/x ", "0.0036496350364964", 1, "0");
      testCalculation("362 1/x + 274 1/x =", "0.0064120659757229", 0, "0");
      testCalculation("272 1/x - 426 1/x =", "0.0013290527478597", 0, "0");
      testCalculation("429 1/x × 463 1/x +", "5.034562269983436e-6", 2, "0");
      testCalculation("323 1/x ÷ 75 1/x =", "0.2321981424148607", 0, "0");
      testCalculation("361 1/x × 447 1/x =", "6.197053920566163e-6", 0, "0");
      testCalculation("456 1/x 1/x", "456", 1, "0");
      testCalculation("333 1/x 1/x", "333", 1, "0");
      testCalculation("139 1/x 1/x", "139", 1, "0");
      testCalculation("35 1/x 1/x", "35", 1, "0");
      testCalculation("259 1/x 1/x", "259", 1, "0");
      testCalculation("148 1/x 1/x - 244 1/x 1/x =", "-96", 0, "0");
      testCalculation("375 1/x 1/x - 189 1/x 1/x =", "186", 0, "0");
      testCalculation("194 1/x 1/x - 165 1/x 1/x =", "29", 0, "0");
      testCalculation("475 1/x 1/x - 35 1/x 1/x =", "440", 0, "0");
      testCalculation("192 1/x 1/x - 459 1/x 1/x =", "-267", 0, "0");
      testCalculation("777777777 1/x = = =", "1.285714287e-9", 0, "0");
      testCalculation("7777777559 1/x = =", "1.285714321879593e-10", 0, "0");
      testCalculation("7777777559 1/x = =", "1.285714321879593e-10", 0, "0");
   }

   @Test
   void testPercent() throws Exception {
      //PERCENT
      testCalculation("0 %", "0", 1, "0");
      testCalculation("3 %", "0", 1, "0");
      testCalculation("4 %", "0", 1, "0");
      testCalculation("%", "0", 1, "0");
      testCalculation("123 %", "0", 1, "0");
      testCalculation("0 + 3 %", "0", 2, "0");
      testCalculation("4 + 3 %", "0.12", 2, "0");
      testCalculation("4 + 3 % %", "0.0048", 2, "0");
      testCalculation("4 + 3 % + %", "0.169744", 3, "0");
      testCalculation("4 + 3 % = = %", "0.179776", 1, "0");
      testCalculation("4 + 3 % = = % %", "0.0076225024", 1, "0");
      testCalculation("9 + 1 % ce", "0", 1, "0");
      testCalculation("9 % + ", "0", 1, "0");
      testCalculation("8 % 8 +", "8", 1, "0");
      testCalculation("88 sqr sqr + √ %", "4,644,040,867.84", 2, "0");
      testCalculation("88 sqr sqr + √ =", "59,977,280", 0, "0");
      testCalculation("20 + 20 % = % =", "9.76", 0, "0");
      testCalculation("12 + 3 sqr = +", "21", 1, "0");
      testCalculation("12 sqr = +", "144", 1, "0");
      testCalculation("8888 % = +", "0", 1, "0");
      testCalculation("8888 % = % % % = + 8 + 8 +", "16", 3, "0");
      testCalculation("200 + 0 = %", "400", 1, "0");
      testCalculation("200 + 0 = % %", "800", 1, "0");
      testCalculation("200 + 0 = % % =", "800", 0, "0");
      testCalculation("200 + 10 = % % =", "936.1", 0, "0");
      testCalculation("200 + 0 = % % % ", "1,600", 1, "0");
      testCalculation("200 + 8 % = - = 8 %", "0", 1, "0");
      testCalculation("0 + 3 + 0 - 3 + %", "0", 5, "0");
      testCalculation("0 + 3 + % + % % + %", "0.0956634194736324", 5, "0");
      testCalculation("0 + 3 + % + % % + % %", "0.0029588220797723", 5, "0");
      testCalculation("0 + 3 + % + % % + % % %", "9.151489825388154e-5", 5, "0");
      testCalculation("0 + 3 + % + % % + % % % %", "2.830510377650995e-6", 5, "0");
      testCalculation("0 + 3 + % + % % + % % % % %", "8.754628099747861e-8", 5, "0");
      testCalculation("12 × 2 = = = = = = = = = = = - 1 - 4 - 5 × 25 = %", "3,771,802,225", 1, "0");
   }

   @Test
   void testClearEnter() throws Exception {
      //CLEAR ENTER
      testCalculation("200 ce", "0", 0, "0");
      testCalculation("200 + ce", "0", 1, "0");
      testCalculation("200 + ce =", "200", 0, "0");
      testCalculation("200 + 100 ce =", "200", 0, "0");
      testCalculation("200 + 100 = ce", "0", 0, "0");
      testCalculation("200 + 100 = + ce ", "0", 1, "0");
      testCalculation("200 + 100 = + ce =", "300", 0, "0");
      testCalculation("200 + 100 = - ce ", "0", 1, "0");
      testCalculation("200 + 100 = - ce =", "300", 0, "0");
      testCalculation("200 + 100 = × ce ", "0", 1, "0");
      testCalculation("200 + 100 = × ce =", "0", 0, "0");
      testCalculation("200 + 100 = ÷ ce ", "0", 1, "0");
      testCalculation("200 + 100 = % ce ", "0", 0, "0");
      testCalculation("200 + 100 = % ce =", "100", 0, "0");
      testCalculation("200 + 100 = ± ce ", "0", 0, "0");
      testCalculation("200 + 100 = ± ce =", "100", 0, "0");
      testCalculation("200 + 100 = √ ce ", "0", 0, "0");
      testCalculation("200 + 100 = √ ce =", "100", 0, "0");
   }

   @Test
   void testExponent() throws Exception {
      //EXPONENT
      testCalculation("0.0000000000000001 + 1", "1", 1, "0");
      testCalculation("0.0000000000000001 1/x =", "1e16", 0, "0");
      testCalculation("0.0000000000000001 × 0.1 = = = = = = =", "1e-23", 0, "0");
      testCalculation("0.0000000000000001 + =", "0.0000000000000002", 0, "0");
      testCalculation("0.0000000000000001 + = =", "0.0000000000000003", 0, "0");
      testCalculation("0.0000000000000001 + = = =", "0.0000000000000004", 0, "0");
      testCalculation("0.0000000000000001 + = = = =", "0.0000000000000005", 0, "0");
      testCalculation("0.0000000000000001 - =", "0", 0, "0");
      testCalculation("0.0000000000000001 - = =", "-0.0000000000000001", 0, "0");
      testCalculation("0.0000000000000001 - = = =", "-0.0000000000000002", 0, "0");
      testCalculation("0.0000000000000001 - = = = =", "-0.0000000000000003", 0, "0");
      testCalculation("0.0000000000000001 - = = = = =", "-0.0000000000000004", 0, "0");
      testCalculation("0.0000000000000001 - = = = = = =", "-0.0000000000000005", 0, "0");
      testCalculation("2.000000000000001 + 1 =", "3.000000000000001", 0, "0");
      testCalculation("2.000000000000001 + 2 =", "4.000000000000001", 0, "0");
      testCalculation("2.000000000000001 + 3 =", "5.000000000000001", 0, "0");
      testCalculation("2.000000000000001 + 4 =", "6.000000000000001", 0, "0");
      testCalculation("0.0111111111111111 × 0.1 =", "0.0011111111111111", 0, "0");
      testCalculation("0.000000000000001 ± × 2 =", "-0.000000000000002", 0, "0");
      testCalculation("0.000000000000001 ± × 2 = + =", "-0.000000000000004", 0, "0");
      testCalculation("0.000000000000001 ± × 2 = + = =", "-0.000000000000006", 0, "0");
      testCalculation("0.000000000000001 ± × 2 = + = = =", "-0.000000000000008", 0, "0");
      testCalculation("0.000000000000001 ± × 2 = + = = = = =", "-0.000000000000012", 0, "0");
      testCalculation("0.000000000000001 ± × 2 = + = = = = = sqr", "1.44e-28", 1, "0");
      testCalculation("0.0000000000000001 sqr sqr sqr sqr sqr sqr sqr sqr sqr 0.00000000000001 sqr sqr sqr sqr sqr sqr sqr × 0.000000000000001 ± =", "-1e-1807", 0, "0");
      testCalculation("0.0000000000000001 sqr sqr sqr sqr sqr sqr sqr sqr sqr × 0.00000000000001 sqr sqr sqr sqr sqr sqr sqr × 0.000000000000001 ± =", "-1e-9999", 0, "0");
      testCalculation("9999999999999999 + 2 =", "1e16", 0, "0");
      testCalculation("9999999999999999 × 2 =", "2e16", 0, "0");
      testCalculation("9999999999999999 × 8 =", "7.999999999999999e16", 0, "0");
      testCalculation("9999999999999999 × 8 = = ", "6.399999999999999e17", 0, "0");
      testCalculation("9999999999999999 × 8 = = =", "5.119999999999999e18", 0, "0");
      testCalculation("9999999999999999 × 8 = = = =", "4.096e19", 0, "0");
      testCalculation("9999999999999999 + 5 =", "1e16", 0, "0");
      testCalculation("9999999999999999 + 6 =", "1e16", 0, "0");
      testCalculation("9999999999999999 1/x + 1", "1", 1, "0");
      testCalculation("9999999999999999 1/x", "0.0000000000000001", 1, "0");
      testCalculation("9999999999999999 1/x", "0.0000000000000001", 1, "0");
      testCalculation("9999999999999999 1/x + =", "0.0000000000000002", 0, "0");
      testCalculation("9999999999999999 1/x + = =", "0.0000000000000003", 0, "0");
      testCalculation("9999999999999999 1/x + = = =", "0.0000000000000004", 0, "0");
      testCalculation("9999999999999999 ± 1/x + = = =", "-0.0000000000000004", 0, "0");
      testCalculation("9999999999999999 1/x + = = = =", "5.000000000000001e-16", 0, "0");
      testCalculation("9999999999999999 sqr sqr sqr sqr sqr sqr sqr + 9999999999999999 sqr sqr sqr sqr sqr sqr sqr sqr sqr =", "9.999999999999488e8191", 0, "0");
   }

   @Test
   void testPeriod() throws Exception {
      //PERIOD
      testCalculation("3 1/x + 2 =", "2.3333333333333333", 0, "0");
      testCalculation("11 ÷ 3 =", "3.6666666666666667", 0, "0");
      testCalculation("5 + 5 = 1/x =", "5.1", 0, "0");
      testCalculation("5 + 5 = 1/x = =", "10.1", 0, "0");
      testCalculation("5 ± + 5 ± = 1/x =", "-5.1", 0, "0");
      testCalculation("5 ± + 5 ± = 1/x = =", "-10.1", 0, "0");
      testCalculation("50 + 50 = 1/x =", "50.01", 0, "0");
      testCalculation("50 + 50 = 1/x = =", "100.01", 0, "0");
      testCalculation("500 + 500 = 1/x =", "500.001", 0, "0");
      testCalculation("500 + 500 = 1/x = =", "1,000.001", 0, "0");
      testCalculation("2.000000000000001 + 8 =", "10.000000000000001", 0, "0");
      testCalculation("0.0000000000000001 + 1 =", "1.0000000000000001", 0, "0");
      testCalculation("0.0000000000000001 + 1 = =", "2.0000000000000001", 0, "0");
      testCalculation("0.0000000000000001 + 1 = = =", "3.0000000000000001", 0, "0");
      testCalculation("0.0000000000000001 + 1 = = = =", "4.0000000000000001", 0, "0");
      testCalculation("0.0000000000000003 × 0.1 = = =", "3e-19", 0, "0");
      testCalculation("1 ÷ 3 ± × 3 + 1 =", "0", 0, "0");
      testCalculation("1 ÷ 3 × 3 - 1 =", "0", 0, "0");
      testCalculation("1 ÷ 7 × 7 - 1 =", "0", 0, "0");
      testCalculation("2.000000000000001 + 8 =", "10.000000000000001", 0, "0");
      testCalculation("8 + 8 sqr 3 =", "11", 0, "0");
      testCalculation("8 + 8 sqr 3 = sqr 2 + 3 =", "5", 0, "0");
      testCalculation("8 sqr 8 + 3 =", "11", 0, "0");
      testCalculation("8 sqr 8 + 3 sqr =", "17", 0, "0");
      testCalculation("9 + 1 sqr ce =", "9", 0, "0");
      testCalculation("0.0000000000000001 + 3 = = = =", "12.0000000000000001", 0, "0");
      testCalculation("0.0000000000000001 + 50 = = = =", "200.0000000000000001", 0, "0");
      testCalculation("0.0000000000000001 sqr sqr sqr sqr sqr sqr m+ √ √ √ sqr sqr sqr m- mr", "0", 1, "0");
      testCalculation("1 ÷ 7 =", "0.1428571428571429", 0, "0");
      testCalculation("1 ÷ 7 × 1000000000000000 =", "142,857,142,857,142.8571428571428571", 0, "0");
      testCalculation("1 ÷ 7 × 1000000000000000 × 7 - 1000000000000000 =", "0", 0, "0");

   }

   @Test
   void testOverflow() throws Exception {
      //limits
      //expression start number 10,000,000,000
      testException("10000000000 sqr × = = = = × = = = = × = × = = = = - 1 = × =", new OverflowException(), 1, "0");

      //1.11111.. in period
      testCalculation("10 ÷ 9 =", "1.1111111111111111", 0, "0");
      //lowest positive number 1e-9999 e.g. 0.999........9999 (9999 fraction digits)
      testCalculation(LOWEST_POSITIVE_NUMBER_EXPRESSION, "1e-9999", 1, "0");
      //reaching positive fraction overflow 9.e-10000 e.g. 0.0........0001 (10000 fraction digits)
      testException("10 ÷ 9 = m+ c " + LOWEST_POSITIVE_NUMBER_EXPRESSION + " ÷ mr =", new OverflowException(), 1, "1.1111111111111111");    //Overflow

      //highest negative number -1e-9999 e.g. 0.999...9999 (9999 fraction digits)
      testCalculation(HIGHEST_NEGATIVE_NUMBER_EXPRESSION, "-1e-9999", 1, "0");
      //reaching negative fraction overflow -9.e-10000 e.g. 0.0...0001 (10000 fraction digits)
      testException("10 ÷ 9 = m+ c " + HIGHEST_NEGATIVE_NUMBER_EXPRESSION + " ÷ mr =", new OverflowException(), 1, "1.1111111111111111");   //Overflow

      //lowest number -9.999999999999999e9999 e.g. -999999999999999949...999999.9999999999999...9999 (10000 integer digits, 9999 fraction digits,0, "0");
      testCalculation(LOWEST_NUMBER_EXPRESSION, "-9.999999999999999e9999", 1, "1e9983");
      //reaching positive overflow -9.e-10000 e.g. 0.0...0001 (10000 fraction digits)
      testException(LOWEST_NUMBER_EXPRESSION + " mc m+ c " + HIGHEST_NEGATIVE_NUMBER_EXPRESSION + " + mr =", new OverflowException(), 1, "-9.999999999999999e9999");

      //highest positive number 9.999999999999999e9999 e.g. 999999999999999949...999999.9999999999999...9999 (10000 integer digits, 9999 fraction digits,0, "0");
      testCalculation(HIGHEST_NUMBER_EXPRESSION, "9.999999999999999e9999", 0, "1e9983");
      //reaching positive overflow 9.e-10000 e.g. 0.0...0001 (10000 fraction digits)
      testException(HIGHEST_NUMBER_EXPRESSION + " mc m+ c " + LOWEST_POSITIVE_NUMBER_EXPRESSION + " + mr =", new OverflowException(), 1, "9.999999999999999e9999");

      testException("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = × = × 10 = ÷ 9 × 9 = m+ c 10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = × = × 10 = + = = = = = = = = + mr = ", new OverflowException(), 1, "1e9999");
      testException("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = × = × 10 = m+ c 10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = × = × 10 = ÷ 9 × 9 + = = = = = = = = + mr =", new OverflowException(), 1, "1e9999");
      testException("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = × = × 10 = ÷ 9 × 9 × 10 =", new OverflowException(), 3, "0");
      testException("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = × = × 10 = ÷ 9 × 9 + = = = = = = = = + 1 = 1/x", new OverflowException(), 1, "0");

      testCalculation("10000000000 sqr × = = = = × = = = = × = ", "1e1000", 0, "0");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ", "1e5000", 0, "0");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = - 1 = ", "1e5000", 0, "0");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = × = × 10 = ", "1e9999", 0, "0");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = × = × 10 = × 9 =", "9e9999", 0, "0");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = × = × 10 = × 9 = m+ ÷ 10 = + mr =", "9.9e9999", 0, "9e9999");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = sqr ÷ 1000000000000000 = m+ × 1000000000000000 × 10 = 1/x × 9 = 1/x × 10 - mr × 9 + mr = = = = ±", "-9.999999999999999e9999", 1, "1e9983");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = sqr ÷ 1000000000000000 = m+", "1e9983", 0, "1e9983");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = sqr ÷ 1000000000000000 = m+ × 1000000000000000 × 10 =", "1e9999", 0, "1e9983");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = sqr ÷ 1000000000000000 = m+ × 1000000000000000 × 10 = 1/x ", "1e-9999", 1, "1e9983");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = sqr ÷ 1000000000000000 = m+ × 1000000000000000 × 10 = 1/x × 9 =", "9e-9999", 0, "1e9983");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = sqr ÷ 1000000000000000 = m+ × 1000000000000000 × 10 = 1/x × 9 = 1/x ", "1.111111111111111e9998", 1, "1e9983");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = sqr ÷ 1000000000000000 = m+ × 1000000000000000 × 10 = 1/x × 9 = 1/x × 10 -", "1.111111111111111e9999", 2, "1e9983");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = sqr ÷ 1000000000000000 = m+ × 1000000000000000 × 10 = 1/x × 9 = 1/x × 10 - mr ×", "1.111111111111111e9999", 3, "1e9983");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = sqr ÷ 1000000000000000 = m+ × 1000000000000000 × 10 = 1/x × 9 = 1/x × 10 = - mr ×", "1.111111111111111e9999", 2, "1e9983");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = sqr ÷ 1000000000000000 = m+ × 1000000000000000 × 10 = 1/x × 9 = 1/x × 10 - mr × 9 + mr = = = =", "9.999999999999999e9999", 0, "1e9983");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = × = × 10 = m+ × 2 ÷ 9 × 9 = + mr = = = = = = =", "9e9999", 0, "1e9999");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = × = × 10 = 1/x", "1e-9999", 1, "0");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = × = × 10 = ÷ 9 × 9 =", "1e9999", 0, "0");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = × = × 10 = m+ ÷ 9 × 9 = + mr = = = = = = = = ", "9e9999", 0, "1e9999");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = × = × 10 = m+ ÷ 9 × 9 = + mr = = = = = = = = + 1 =", "9e9999", 0, "1e9999");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = × = × 10 = ÷ 9 × 10 =", "1.111111111111111e9999", 0, "0");
      testCalculation("10000000000 sqr × = = = = × = = = = × = × = = = = ÷ 10 = × = × 10 = ÷ 9 × 9 + 1 =", "1e9999", 0, "0");
   }

   @Test
   void testDivideByZero() {
      //Divide by zero
      testException("0 1/x", new CannotDivideByZeroException(), 1, "0");
      testException("100 ÷ 0 =", new CannotDivideByZeroException(), 1, "0");
      testException("200 ÷ 0 =", new CannotDivideByZeroException(), 1, "0");
      testException("700 ÷ 0 =", new CannotDivideByZeroException(), 1, "0");
      testException("500 ÷ 0 =", new CannotDivideByZeroException(), 1, "0");
      testException("500 ÷ 700 ce =", new CannotDivideByZeroException(), 1, "0");
      testException("22 ÷ 36 ce =", new CannotDivideByZeroException(), 1, "0");

      //Zero divide by zero
      testException("0 ÷ 0 =", new ResultIsUndefinedException(), 1, "0");
      testException("0 ÷ 100 ce =", new ResultIsUndefinedException(), 1, "0");
      testException("0 ÷ 200 ce =", new ResultIsUndefinedException(), 1, "0");
      testException("0 ÷ 700 ce =", new ResultIsUndefinedException(), 1, "0");
      testException("0 ÷ 500 ce =", new ResultIsUndefinedException(), 1, "0");
      testException("0 ÷ 0 =", new ResultIsUndefinedException(), 1, "0");
   }

   @Test
   void testInvalidInput() {
      //Square root of negative number
      testException("100 - = = √", new InvalidInputException(), 1, "0");
      testException("200 - = = √", new InvalidInputException(), 1, "0");
      testException("700 - = = √", new InvalidInputException(), 1, "0");
      testException("500 - = = √", new InvalidInputException(), 1, "0");
      testException("1000 - 2000 = √", new InvalidInputException(), 1, "0");
      testException("200 - 210 = √", new InvalidInputException(), 1, "0");
      testException("700 - 2300 = √", new InvalidInputException(), 1, "0");
      testException("999 ± = √", new InvalidInputException(), 1, "0");
      testException("0.00001 ± = √", new InvalidInputException(), 1, "0");
      testException("800 × 213 = ± √", new InvalidInputException(), 1, "0");
      testException("500 ± √", new InvalidInputException(), 1, "0");

   }

   @Test
   void testMemory() throws Exception {
      //ADD
      testCalculation("500 m+ mr", "500", 0, "500");
      testCalculation("500 m+ + 100 mr", "500", 1, "500");
      testCalculation("500 m+ + 100 + mr", "500", 2, "500");
      testCalculation("500 m+ + 100 = mr", "500", 0, "500");
      testCalculation("500 m+ + 100 + mr =", "1,100", 0, "500");
      testCalculation("500 m+ m+ m+ mr", "1,500", 0, "1,500");
      testCalculation("500 m+ m+ m+ + 100 mr", "1,500", 1, "1,500");
      testCalculation("500 m+ m+ m+ + 100 + mr", "1,500", 2, "1,500");
      testCalculation("500 m+ m+ m+ + 100 = mr", "1,500", 0, "1,500");
      testCalculation("500 m+ m+ m+ + 100 + mr = ", "2,100", 0, "1,500");
      //SUBSTRACT
      testCalculation("500 m- mr", "-500", 0, "-500");
      testCalculation("500 m- + 100 mr ", "-500", 1, "-500");
      testCalculation("500 m- + 100 + mr ", "-500", 2, "-500");
      testCalculation("500 m- + 100 = mr ", "-500", 0, "-500");
      testCalculation("500 m- + 100 + mr =", "100", 0, "-500");
      testCalculation("500 m- m- m- mr", "-1,500", 0, "-1,500");
      testCalculation("500 m- m- m- + 100 mr", "-1,500", 1, "-1,500");
      testCalculation("500 m- m- m- + 100 = mr", "-1,500", 0, "-1,500");
      testCalculation("500 m- m- m- + 100 + mr", "-1,500", 2, "-1,500");
      testCalculation("500 m- m- m- + 100 + mr =", "-900", 0, "-1,500");
   }

   private void testCalculation(String commandsSequence, String expectedDecimal, int expectedHistorySize, String expectedMemory) throws Exception {
      BigDecimal actualDecimal = operateDecimal(commandsSequence);
      String actualDecimalValueText = formatValue(expectedDecimal, actualDecimal);
      assertEquals(expectedDecimal, actualDecimalValueText);
      log.info(actualDecimal.toPlainString());
      testMemoryAndHistory(expectedHistorySize, expectedMemory);
   }

   private String formatValue(String expectedDecimal, BigDecimal actualDecimal) {
      String decimalText;
      DecimalFormat df = new DecimalFormat();
      if (expectedDecimal.contains("e")) {
         df.applyPattern("0.###############E0");
         decimalText = df.format(actualDecimal).toLowerCase();
      } else {
         df.applyPattern("#,###.################");
         decimalText = df.format(actualDecimal).toLowerCase();
      }
      return decimalText;
   }

   private void testMemoryAndHistory(int expectedHistorySize, String expectedMemory) {
      String actualMemoryValueText = formatValue(expectedMemory, calculator.setCurrentDecimalFromMemory());
      assertEquals(expectedMemory, actualMemoryValueText);
      assertEquals(expectedHistorySize, calculator.getHistory().getHistoryUnits().size());
      calculator.clearAll();
      calculator.memoryClear();
   }

   private void testException(String commandsSequence, Exception expectedEx, int expectedHistorySize, String expectedMemory) {
      assertThrows(expectedEx.getClass(), () -> operateDecimal(commandsSequence));
      testMemoryAndHistory(expectedHistorySize, expectedMemory);
   }

   private BigDecimal operateDecimal(String commandsSequence) throws Exception {
      BigDecimal actualDecimal = new BigDecimal("0");
      for (String c : commandsSequence.split(" ")) {
         if (commands.get(c) == null) {
            BigDecimal value = new BigDecimal(c);
            calculator.editCurrentDecimal(value);
            actualDecimal = calculator.getCurrentDecimal();
         } else {
            actualDecimal = commands.get(c).doOperation();
         }
      }
      return actualDecimal;
   }

}
