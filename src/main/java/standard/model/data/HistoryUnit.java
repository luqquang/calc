package standard.model.data;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a single unit in history of expression.
 * It contains BigDecimal value, binary operation, which user requests
 * for this value and list of non-binary operators which this value
 * has been subjected.
 */
@Data
@RequiredArgsConstructor
public class HistoryUnit {
   /**
    * The initial value of operations
    */
   @NonNull
   private BigDecimal value;

   /**
    * Requested binary operation
    */
   private Operator binary;

   /**
    * List of performed non-binary operations
    */
   private List<Operator> nonBinaries = new ArrayList<>();


}
