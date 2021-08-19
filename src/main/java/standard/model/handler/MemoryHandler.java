package standard.model.handler;


import java.math.BigDecimal;

/**
 * This class calculates and handles a memory logic
 */
public class MemoryHandler {
   private BigDecimal memoryValue = new BigDecimal("0");

   /**
    * Method calls ADD operation with current unit for memory unit
    *
    * @return Decimal current unit default value;
    */
   public BigDecimal add(BigDecimal currentDecimal) {
      memoryValue = memoryValue.add(currentDecimal);
      return memoryValue;
   }

   /**
    * Method calls SUBSTRACT operation with current unit for memory unit
    *
    * @return Decimal current unit default value;
    */
   public BigDecimal substract(BigDecimal currentDecimal) {
      memoryValue = memoryValue.subtract(currentDecimal);
      return memoryValue;
   }

   /**
    * @return Decimal new unit with memory unit value;
    */
   public BigDecimal getMemoryDecimal() {
      return memoryValue;
   }
}
