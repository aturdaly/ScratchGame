package org.homeassignment.records;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record Result(String[][] matrix,
                     BigDecimal reward,
                     @JsonProperty("applied_winning_combinations")
                     @JsonInclude(JsonInclude.Include.NON_NULL)
                     Map<String, List<String>> appliedWinningCombinations,
                     @JsonProperty("applied_bonus_symbol")
                     @JsonInclude(JsonInclude.Include.NON_NULL)
                     String appliedBonusSymbol) {
}
